package com.zhangz.demo.spring.cloud.product.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhangz.demo.spring.cloud.product.dto.shoppingcart.ShoppingCartInfoDTO;
import com.zhangz.demo.spring.cloud.product.dto.shoppingcart.ShoppingGoods;
import com.zhangz.demo.spring.cloud.product.dto.shoppingcart.SkuItem;
import com.zhangz.demo.spring.cloud.product.dto.shoppingcart.SkuNamePair;
import com.zhangz.demo.spring.cloud.product.entity.*;
import com.zhangz.demo.spring.cloud.product.service.*;
import com.zhangz.spring.cloud.common.exception.BussinessException;
import com.zhangz.spring.cloud.common.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.util.Asserts;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    public void add(long goodsId, int number, List<SkuItem> skuItems) {
        // 订单
        OrderInfo orderInfo = orderInfoService.getNotOrdered();
        Assert.notNull(orderInfo, "订单不存在");
        // 订单明细
        OrderGood orderGood = new OrderGood();
        orderGood.setOrderId(orderInfo.getId());
        orderGood.setId(UUIDUtils.getUUID32());
        orderGood.setGoodId(goodsId);
        orderGood.setNumber(number);
        orderGood.setSku(JSON.toJSONString(skuItems));
        orderGood.setPrice(10 * number);
        orderGood.setCreateTime(DateUtil.formatTime(new Date()));
        orderGoodService.save(orderGood);
    }

    @Override
    public ShoppingCartInfoDTO info() {
        ShoppingCartInfoDTO dto = new ShoppingCartInfoDTO();
        int number = 0;
        int price = 0;
        OrderInfo orderInfo = orderInfoService.getNotOrdered();
        
        if (null == orderInfo){
            orderInfo = orderInfoService.createOrder();
        }
        
        List<OrderGood> orderGoods = orderGoodService.queryByOrderId(orderInfo.getId());
        if (CollectionUtils.isEmpty(orderGoods)) {
            dto.setNumber(number);
            dto.setPrice(price);
            return dto;
        }
        List<ShoppingGoods> items = new ArrayList<>();
        for (OrderGood orderGood : orderGoods) {
            number += orderGood.getNumber();
            price += orderGood.getPrice();

            GoodInfo goodInfo = goodInfoService.getById(orderGood.getGoodId());
            if (null == goodInfo) {
                continue;
            }

            ShoppingGoods goods = new ShoppingGoods();
            goods.setPrice(orderGood.getPrice());
            goods.setPrice(orderGood.getPrice());
            goods.setPic(goodInfo.getPic());
            goods.setName(goodInfo.getName());

            List<SkuNamePair> skuNamePairs = new ArrayList<>();
            List<SkuItem> skuItems = JSONArray.parseArray(orderGood.getSku(), SkuItem.class);
            if (CollectionUtils.isEmpty(skuItems)){
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

        dto.setPrice(price);
        dto.setNumber(number);
        dto.setItems(items);

        return dto;
    }

    @Override
    public void empty() throws BussinessException {
        OrderInfo orderInfo = orderInfoService.getNotOrdered();
        if (null == orderInfo){
            throw  new BussinessException("已下单,请刷新后操作");
        }
        orderGoodService.emptyByOrderId(orderInfo.getId());
    }

    @Override
    public void addOrder(String propertyChildIds) throws BussinessException {
        OrderInfo orderInfo = orderInfoService.getOrderStileInCart();
        orderInfo.setOrderStatus(OrderStatusEnum.ORDERED.getState());
        orderInfo.setOrderedTime(DateUtil.formatTime(new Date()));
    }
}
