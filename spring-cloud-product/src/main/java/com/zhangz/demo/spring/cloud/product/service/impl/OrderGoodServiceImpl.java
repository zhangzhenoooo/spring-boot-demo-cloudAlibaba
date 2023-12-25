package com.zhangz.demo.spring.cloud.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.product.dao.OrderGoodMapper;
import com.zhangz.demo.spring.cloud.product.entity.OrderGood;
import com.zhangz.demo.spring.cloud.product.service.OrderGoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
public class OrderGoodServiceImpl extends ServiceImpl<OrderGoodMapper, OrderGood> implements OrderGoodService {

    @Resource
    private OrderGoodMapper orderGoodMapper;

    @Override
    public List<OrderGood> queryByOrderIdAndStatus(String orderId,Integer status) {
        return orderGoodMapper.queryByOrderIdAndStatus(orderId,status);
    }

    @Override
    public void emptyByOrderId(String orderId) {
        orderGoodMapper.deleteByOrderId(orderId);
    }

    @Override
    public void changeStatusToOrdered(String orderId) {
        orderGoodMapper.changeStatusToOrdered(orderId);
    }
}
