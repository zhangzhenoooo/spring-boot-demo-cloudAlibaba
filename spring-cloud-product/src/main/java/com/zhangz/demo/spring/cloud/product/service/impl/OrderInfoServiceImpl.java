package com.zhangz.demo.spring.cloud.product.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.zhangz.demo.spring.cloud.product.constant.CyTableStatusEnum;
import com.zhangz.demo.spring.cloud.product.constant.OrderStatusEnum;
import com.zhangz.demo.spring.cloud.product.dao.OrderInfoMapper;
import com.zhangz.demo.spring.cloud.product.entity.GoodInfo;
import com.zhangz.demo.spring.cloud.product.entity.OrderGood;
import com.zhangz.demo.spring.cloud.product.entity.OrderInfo;
import com.zhangz.demo.spring.cloud.product.service.GoodInfoService;
import com.zhangz.demo.spring.cloud.product.service.OrderGoodService;
import com.zhangz.demo.spring.cloud.product.service.OrderInfoService;
import com.zhangz.demo.spring.cloud.product.vo.OrderInfoVO;
import com.zhangz.demo.spring.cloud.product.vo.OrderedGoodsVO;
import com.zhangz.demo.spring.cloud.product.vo.OrderedVO;
import com.zhangz.spring.cloud.common.exception.BussinessException;
import com.zhangz.spring.cloud.common.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.service.impl
 * 
 * @Project：spring-cloud
 * 
 * @name：OrderServiceImpl
 * 
 * @Date：2023/12/21 15:55
 * 
 * @Filename：OrderServiceImpl
 */
@Service
@Slf4j
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private OrderGoodService orderGoodService;

    @Resource
    private GoodInfoService goodInfoService;

    @Override
    public OrderInfo createOrder() {
        OrderInfo orderInfo = getNotOrdered();

        if (null == orderInfo) {
            orderInfo = new OrderInfo();
            orderInfo.setId(UUIDUtils.getUUID32());
            orderInfo.setOrderStatus(0);
            orderInfo.setCreateTime(DateUtil.formatTime(new Date()));
            orderInfo.setUserId(123456);
            orderInfo.setAmount(new BigDecimal("0"));
            orderInfo.setAmountReal(new BigDecimal("0"));
            orderInfo.setGoodsNumber(0);
            this.save(orderInfo);
        }
        return orderInfo;
    }

    @Override
    public OrderInfo getNotOrdered() {
        int userId = 123456;
        return orderInfoMapper.getNotOrdered(userId);
    }

    @Override
    public OrderInfo getOrderStileInCart() throws BussinessException {
        int userId = 123456;
        OrderInfo orderInfo = orderInfoMapper.getOrderInfoByStatus(userId, 0);
        if (null == orderInfo) {
            throw new BussinessException("订单不存在");
        }
        return orderInfo;
    }

    @Override
    public OrderedVO listByUserIdAndStatus(String token, int status) throws BussinessException {
        OrderedVO orderedVO = new OrderedVO();

        int userId = 123456;
        OrderInfo orderInfo = orderInfoMapper.getOrderInfoByStatus(userId, status);
        if (null == orderInfo) {
            throw new BussinessException("订单不存在");
        }
        OrderInfoVO orderInfoVO = new OrderInfoVO();
        orderInfoVO.setId(orderInfo.getId());
        orderInfoVO.setAmount(orderInfo.getAmount());
        orderInfoVO.setAmountReal(orderInfo.getAmount());
        orderInfoVO.setGoodsNumber(orderInfo.getGoodsNumber());
        orderInfoVO.setTableCode("141341");
        orderedVO.setOrderList(Lists.newArrayList(orderInfoVO));

        List<OrderedGoodsVO> orderedGoodsVOList = new ArrayList<>();
        List<OrderGood> orderGoods = orderGoodService.queryByOrderId(orderInfo.getId());
        if (CollectionUtil.isNotEmpty(orderGoods)) {
            for (OrderGood orderGood : orderGoods) {
                OrderedGoodsVO goodsVO = new OrderedGoodsVO();
                goodsVO.setCyTableStatus(CyTableStatusEnum.COOKING.getState());
                goodsVO.setAmount(orderGood.getPrice());
                goodsVO.setNumber(orderGood.getNumber());
                goodsVO.setProperty("fasfasf");

                GoodInfo goodInfo = goodInfoService.getById(orderGood.getGoodId());
                if (null != goodInfo) {
                    goodsVO.setGoodsName(goodInfo.getName());
                    goodsVO.setPic(goodInfo.getPic());
                }

                orderedGoodsVOList.add(goodsVO);
            }
        }

        Map<String, List<OrderedGoodsVO>> goodsMap = new HashMap<>();
        goodsMap.put(orderInfoVO.getId(), orderedGoodsVOList);
        orderedVO.setGoodsMap(goodsMap);
        return orderedVO;
    }

    @Override
    public void pay(String token, BigDecimal money, String remark, String orderId) throws BussinessException {
        log.info("订单支付，token：{}，money:{},remark:{},orderId:{}", token, money, remark, orderId);

        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
        if (null == orderInfo) {
            throw new BussinessException("订单【" + orderInfo + "】不存在");
        }
        orderInfo.setOrderStatus(OrderStatusEnum.PAYED.getState());
        orderInfo.setPayTime(DateUtil.formatTime(new Date()));
        orderInfoMapper.updateById(orderInfo);
        log.info("订单【{}】支付成功", orderId);
    }
}
