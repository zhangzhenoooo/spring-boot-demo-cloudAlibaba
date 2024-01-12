package com.zhangz.demo.spring.cloud.product.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Pair;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Sets;
import com.zhangz.demo.spring.cloud.product.constant.CyTableStatusEnum;
import com.zhangz.demo.spring.cloud.product.constant.OrderStatusEnum;
import com.zhangz.demo.spring.cloud.product.dto.shoppingcart.ShoppingCartInfoDTO;
import com.zhangz.demo.spring.cloud.product.dto.shoppingcart.ShoppingGoods;
import com.zhangz.demo.spring.cloud.product.dto.shoppingcart.SkuItem;
import com.zhangz.demo.spring.cloud.product.dto.shoppingcart.SkuNamePair;
import com.zhangz.demo.spring.cloud.product.entity.GoodInfo;
import com.zhangz.demo.spring.cloud.product.entity.GoodProperty;
import com.zhangz.demo.spring.cloud.product.entity.OrderGood;
import com.zhangz.demo.spring.cloud.product.entity.OrderInfo;
import com.zhangz.demo.spring.cloud.product.service.*;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.common.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.service.impl
 * 
 * @Project：spring-cloud
 * 
 * @name：ShoppingCartServiceImpl
 * 
 * @Date：2023/12/21 15:14
 * 
 * @Filename：ShoppingCartServiceImpl
 */
@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private OrderGoodService orderGoodService;
    @Resource
    private GoodInfoService goodInfoService;
    @Resource
    private GoodPropertyService goodPropertyService;

    @Override

    @Transactional(rollbackFor = Exception.class)
    public void add(long goodsId, int number, List<SkuItem> skuItems) {
        // 订单
        OrderInfo orderInfo = orderInfoService.getNotOrdered();
        if (null == orderInfo) {
            orderInfo = orderInfoService.createOrder();
        }
        // 订单明细
        OrderGood orderGood = new OrderGood();
        orderGood.setOrderId(orderInfo.getId());
        orderGood.setId(UUIDUtils.getUUID32());
        orderGood.setGoodId(goodsId);
        orderGood.setNumber(number);
        orderGood.setSku(JSON.toJSONString(skuItems));
        orderGood.setPrice(new BigDecimal("10").multiply(new BigDecimal(String.valueOf(number))));
        orderGood.setCreateTime(DateUtil.formatTime(new Date()));
        orderGoodService.save(orderGood);

        BigDecimal amount = orderInfo.getAmount().add(orderGood.getPrice());
        // 加菜后把订单状态修改为未下单状态
        orderInfo.setOrderStatus(OrderStatusEnum.IN_CART.getState());
        orderInfo.setAmount(amount);
        int goodsNumber = orderInfo.getGoodsNumber() + number;
        orderInfo.setGoodsNumber(goodsNumber);
        orderInfoService.updateById(orderInfo);
    }

    @Override
    public ShoppingCartInfoDTO info() {
        ShoppingCartInfoDTO dto = new ShoppingCartInfoDTO();

        OrderInfo orderInfo = orderInfoService.getNotOrdered();
        if (null == orderInfo) {
            return null;
        }

        dto.setOrderStatus(orderInfo.getOrderStatus());
        int number = 0;
        BigDecimal amount = new BigDecimal("0");
        // 没有下单的 或者加菜
        List<OrderGood> orderGoods = orderGoodService.queryByOrderIdAndStatus(orderInfo.getId(), Sets.newHashSet(CyTableStatusEnum.NEED_CHECK.getState()));
        // 获取goods详细信息
        Pair<Pair<Integer, BigDecimal>, List<ShoppingGoods>> goodsDetailPairNo = getGoodsDetail(orderGoods, number, amount);
        Pair<Integer, BigDecimal> keyNo = goodsDetailPairNo.getKey();
        number = keyNo.getKey();
        amount = keyNo.getValue();
        List<ShoppingGoods> items = goodsDetailPairNo.getValue();

        List<OrderGood> orderedGoods = orderGoodService.queryByOrderIdAndStatus(orderInfo.getId(), Sets.newHashSet(CyTableStatusEnum.CHECKED.getState()));
        Pair<Pair<Integer, BigDecimal>, List<ShoppingGoods>> goodsDetailPair = getGoodsDetail(orderedGoods, number, amount);
        Pair<Integer, BigDecimal> key = goodsDetailPair.getKey();
        number = key.getKey();
        amount = key.getValue();
        List<ShoppingGoods> orderedGoodsItems = goodsDetailPair.getValue();

        dto.setPrice(amount);
        dto.setNumber(number);
        dto.setItems(items);
        dto.setCheckedItems(orderedGoodsItems);

        return dto;
    }

    private Pair<Pair<Integer, BigDecimal>, List<ShoppingGoods>> getGoodsDetail(List<OrderGood> orderGoods, int number, BigDecimal price) {

        if (CollectionUtils.isEmpty(orderGoods)) {
            return Pair.of(Pair.of(number, price), null);
        }

        List<ShoppingGoods> items = new ArrayList<>();
        for (OrderGood orderGood : orderGoods) {
            number += orderGood.getNumber();
            price = price.add(orderGood.getPrice());

            GoodInfo goodInfo = goodInfoService.getById(orderGood.getGoodId());
            if (null == goodInfo) {
                continue;
            }

            ShoppingGoods goods = new ShoppingGoods();
            goods.setPrice(orderGood.getPrice());
            goods.setNumber(orderGood.getNumber());
            goods.setKey(orderGood.getId());
            goods.setPic(goodInfo.getPic());
            goods.setName(goodInfo.getName());
            goods.setMinBuyNumber(goodInfo.getMinBuyNumber());
            List<SkuNamePair> skuNamePairs = new ArrayList<>();
            List<SkuItem> skuItems = JSONArray.parseArray(orderGood.getSku(), SkuItem.class);
            if (CollectionUtils.isEmpty(skuItems)) {
                items.add(goods);
                continue;
            }

            for (SkuItem skuItem : skuItems) {
                SkuNamePair skuNamePair = new SkuNamePair();
                GoodProperty goodPropertyById = goodPropertyService.getById(skuItem.getOptionId());
                skuNamePair.setOptionName(goodPropertyById.getName());
                GoodProperty goodPropertyByValueId = goodPropertyService.getById(skuItem.getOptionValueId());
                skuNamePair.setOptionValueName(goodPropertyByValueId.getName());
                skuNamePairs.add(skuNamePair);
            }
            goods.setSku(skuNamePairs);
            items.add(goods);
        }
        return Pair.of(Pair.of(number, price), items);
    }

    @Override
    public void empty() throws BussinessException {
        OrderInfo orderInfo = orderInfoService.getNotOrdered();
        if (null == orderInfo) {
            throw new BussinessException("已下单,请刷新后操作");
        }
        orderGoodService.emptyByOrderId(orderInfo.getId());
    }

    @Override
    public void addOrder(String propertyChildIds) throws BussinessException {
        /**
         *  修改订单状态 为已下单
         */
        OrderInfo orderInfo = orderInfoService.getOrderStileInCart();
        orderInfo.setOrderStatus(OrderStatusEnum.ORDERED.getState());
        orderInfo.setOrderedTime(DateUtil.formatDateTime(new Date()));
        BigDecimal amount = orderGoodService.getAmount(orderInfo.getId());
        orderInfo.setAmount(amount);
        orderInfo.setAmountReal(amount);
        orderInfoService.updateById(orderInfo);
        /**
         * 修改菜品状态为已下单
         */
        orderGoodService.changeStatusToOrdered(orderInfo.getId());
    }

    @Override
    public void modifyNumber(String token, String key, int number) throws BussinessException {
        OrderGood orderGood = orderGoodService.getById(key);
        if (null == orderGood) {
            throw new BussinessException("商品不存在");
        }

        if (0 != orderGood.getStatus()) {
            throw new BussinessException("商品已下单不能修改数量，请联系服务员");
        }

        orderGood.setNumber(number);
        orderGoodService.updateById(orderGood);
    }

    @Override
    public void remove(String token, String key) throws BussinessException {
        OrderGood orderGood = orderGoodService.getById(key);
        if (null == orderGood) {
            throw new BussinessException("商品不存在");
        }

        if (0 != orderGood.getStatus()) {
            throw new BussinessException("商品已下单不能修改数量，请联系服务员");
        }

        orderGoodService.removeById(key);

    }
}
