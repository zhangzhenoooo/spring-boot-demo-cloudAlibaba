package com.zhangz.demo.spring.cloud.order.service;

import com.zhangz.demo.spring.cloud.common.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrderById(String productName);

    Order createOrder(String pid,int number) throws Exception;
    
}
