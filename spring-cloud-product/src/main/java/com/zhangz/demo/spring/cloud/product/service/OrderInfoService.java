package com.zhangz.demo.spring.cloud.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangz.demo.spring.cloud.product.entity.GoodProperty;
import com.zhangz.demo.spring.cloud.product.entity.OrderInfo;
import com.zhangz.demo.spring.cloud.product.vo.OrderedVO;
import com.zhangz.spring.cloud.common.entity.Order;
import com.zhangz.spring.cloud.common.exception.BussinessException;

import java.math.BigDecimal;

/*
 *@Author：zhangz
 *@Package：com.zhangz.demo.spring.cloud.product.service
 *@Project：spring-cloud
 *@name：OrderService
 *@Date：2023/12/21  15:55
 *@Filename：OrderService
 */
public interface OrderInfoService extends IService<OrderInfo> {


    OrderInfo createOrder();

    /**
     * 查询未下单的订单
     * @return
     */
    OrderInfo getNotOrdered();

    /**
     * 查询未下单的订单
     * @return
     */
    OrderInfo getOrderStileInCart() throws BussinessException;


    OrderedVO listByUserIdAndStatus(String token, Integer status) throws BussinessException;

    void pay(String token, BigDecimal money, String remark, String orderId) throws BussinessException;
    
}
