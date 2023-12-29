package com.zhangz.demo.spring.cloud.product.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Pair;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.product.config.DefaultShopConfig;
import com.zhangz.demo.spring.cloud.product.constant.CyTableStatusEnum;
import com.zhangz.demo.spring.cloud.product.constant.OrderStatusEnum;
import com.zhangz.demo.spring.cloud.product.dao.OrderInfoMapper;
import com.zhangz.demo.spring.cloud.product.entity.GoodInfo;
import com.zhangz.demo.spring.cloud.product.entity.OrderGood;
import com.zhangz.demo.spring.cloud.product.entity.OrderInfo;
import com.zhangz.demo.spring.cloud.product.service.GoodInfoService;
import com.zhangz.demo.spring.cloud.product.service.OrderGoodService;
import com.zhangz.demo.spring.cloud.product.service.OrderInfoService;
import com.zhangz.demo.spring.cloud.product.service.UserInfoService;
import com.zhangz.demo.spring.cloud.product.vo.OrderInfoVO;
import com.zhangz.demo.spring.cloud.product.vo.OrderedGoodsVO;
import com.zhangz.demo.spring.cloud.product.vo.OrderedVO;
import com.zhangz.demo.spring.cloud.product.vo.user.UserInfoVO;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
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

    @Resource
    private DefaultShopConfig defaultShopConfig;


    @Resource
    private UserInfoService userInfoService;

    @Override
    public OrderInfo createOrder() {
        OrderInfo orderInfo = getNotOrdered();
        UserInfoVO currentLoginUserInfo = userInfoService.getCurrentLoginUserInfo();
        String userId = currentLoginUserInfo.getUserId();
        if (null == orderInfo) {
            orderInfo = new OrderInfo();
            orderInfo.setId(createOrderId(userId));
            orderInfo.setOrderStatus(0);
            orderInfo.setCreateTime(DateUtil.formatDateTime(new Date()));
            orderInfo.setUserId(userId);
            orderInfo.setAmount(new BigDecimal("0"));
            orderInfo.setAmountReal(new BigDecimal("0"));
            orderInfo.setGoodsNumber(0);
            this.save(orderInfo);
        }
        return orderInfo;
    }

    private String createOrderId(String userId) {
        int number = getDayCountUserOrder(userId) + 1;
        return DateUtil.format(new Date(), "yyyyMMdd") + "_" + userId + "_" + number;
    }

    private int getDayCountUserOrder(String userId) {
        return orderInfoMapper.getDayCountUserOrder(userId, DateUtil.formatDate(new Date()));
    }

    @Override
    public OrderInfo getNotOrdered() {
        UserInfoVO currentLoginUserInfo = userInfoService.getCurrentLoginUserInfo();
        String userId = currentLoginUserInfo.getUserId();;
        return orderInfoMapper.getNotOrdered(userId);
    }

    @Override
    public OrderInfo getOrderStileInCart() throws BussinessException {
        UserInfoVO currentLoginUserInfo = userInfoService.getCurrentLoginUserInfo();
        String userId = currentLoginUserInfo.getUserId();
        OrderInfo orderInfo = orderInfoMapper.getOrderInfoByStatus(userId, 0);
        if (null == orderInfo) {
            throw new BussinessException("订单不存在");
        }
        return orderInfo;
    }

    @Override
    public OrderedVO listByUserIdAndStatus(String token, Integer status) throws BussinessException {
        OrderedVO orderedVO = new OrderedVO();
        UserInfoVO currentLoginUserInfo = userInfoService.getCurrentLoginUserInfo();
        String userId = currentLoginUserInfo.getUserId();

        List<OrderInfo> orderInfos = orderInfoMapper.listOrderInfoByStatus(userId, status);
        if (null != status && CollectionUtil.isEmpty(orderInfos)) {
            throw new BussinessException("订单不存在");
        }

        Map<String, List<OrderedGoodsVO>> goodsMap = new HashMap<>();
        List<OrderInfoVO> orderList = new ArrayList<>();
        for (OrderInfo orderInfo : orderInfos) {
            Pair<OrderInfoVO, List<OrderedGoodsVO>> pair = getOrderInfos(orderInfo);
            OrderInfoVO key = pair.getKey();

            if (null == key) {
                continue;
            }
            List<OrderedGoodsVO> value = pair.getValue();
            goodsMap.put(key.getId(), value);
            orderList.add(key);
        }
        orderedVO.setOrderList(orderList);
        orderedVO.setGoodsMap(goodsMap);
        return orderedVO;
    }

    private Pair<OrderInfoVO, List<OrderedGoodsVO>> getOrderInfos(OrderInfo orderInfo) {
        OrderInfoVO orderInfoVO = new OrderInfoVO();
        orderInfoVO.setId(orderInfo.getId());
        orderInfoVO.setOrderNumber(orderInfo.getId());
        orderInfoVO.setDateAdd(orderInfo.getOrderedTime());
        orderInfoVO.setAmount(orderInfo.getAmount());
        orderInfoVO.setAmountReal(orderInfo.getAmount());
        orderInfoVO.setGoodsNumber(orderInfo.getGoodsNumber());
        orderInfoVO.setTableCode("141341");
        orderInfoVO.setShopNameZt(defaultShopConfig.getShopName());
        orderInfoVO.setStatusStr(OrderStatusEnum.getDescByState(orderInfo.getOrderStatus()));
        List<OrderedGoodsVO> orderedGoodsVOList = new ArrayList<>();
        List<OrderGood> orderGoods = orderGoodService.queryByOrderIdAndStatus(orderInfo.getId(), CyTableStatusEnum.CHECKED.getState());
        if (CollectionUtil.isEmpty(orderGoods)) {
            return Pair.of(null, null);
        }

        for (OrderGood orderGood : orderGoods) {
            OrderedGoodsVO goodsVO = new OrderedGoodsVO();
            goodsVO.setCyTableStatus(CyTableStatusEnum.COOKING.getState());
            goodsVO.setAmount(orderGood.getPrice());
            goodsVO.setNumber(orderGood.getNumber());
            goodsVO.setProperty("fasfasf");
            goodsVO.setKey(orderGood.getId());
            GoodInfo goodInfo = goodInfoService.getById(orderGood.getGoodId());
            if (null != goodInfo) {
                goodsVO.setGoodsName(goodInfo.getName());
                goodsVO.setPic(goodInfo.getPic());
            }

            orderedGoodsVOList.add(goodsVO);
        }

        return Pair.of(orderInfoVO, orderedGoodsVOList);
    }

    @Override
    public void pay(String token, BigDecimal money, String remark, String orderId) throws BussinessException {
        log.info("订单支付，token：{}，money:{},remark:{},orderId:{}", token, money, remark, orderId);

        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
        if (null == orderInfo) {
            throw new BussinessException("订单【" + orderInfo + "】不存在");
        }
        orderInfo.setOrderStatus(OrderStatusEnum.PAYED.getState());
        orderInfo.setPayTime(DateUtil.formatDateTime(new Date()));
        orderInfoMapper.updateById(orderInfo);
        log.info("订单【{}】支付成功", orderId);
    }
}
