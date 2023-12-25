package com.zhangz.demo.spring.cloud.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangz.demo.spring.cloud.product.entity.OrderGood;
import com.zhangz.demo.spring.cloud.product.entity.OrderInfo;

import java.util.List;

/*
 *@Author：zhangz
 *@Package：com.zhangz.demo.spring.cloud.product.service
 *@Project：spring-cloud
 *@name：OrderService
 *@Date：2023/12/21  15:55
 *@Filename：OrderService
 */
public interface OrderGoodService extends IService<OrderGood> {
    List<OrderGood> queryByOrderIdAndStatus(String orderId,Integer status);

    void emptyByOrderId(String id);

    void changeStatusToOrdered(String orderId);
    
}
