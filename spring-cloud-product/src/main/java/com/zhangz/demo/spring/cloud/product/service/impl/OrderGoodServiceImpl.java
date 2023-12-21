package com.zhangz.demo.spring.cloud.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.product.dao.OrderGoodMapper;
import com.zhangz.demo.spring.cloud.product.dao.OrderInfoMapper;
import com.zhangz.demo.spring.cloud.product.entity.OrderGood;
import com.zhangz.demo.spring.cloud.product.entity.OrderInfo;
import com.zhangz.demo.spring.cloud.product.service.OrderGoodService;
import com.zhangz.demo.spring.cloud.product.service.OrderInfoService;
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
    public List<OrderGood> queryByOrderId(String orderId) {
        return orderGoodMapper.queryByOrderId(orderId);
    }

    @Override
    public void emptyByOrderId(String orderId) {
        orderGoodMapper.deleteByOrderId(orderId);
    }
}
