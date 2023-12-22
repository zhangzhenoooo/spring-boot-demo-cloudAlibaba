package com.zhangz.demo.spring.cloud.product.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.product.dao.OrderInfoMapper;
import com.zhangz.demo.spring.cloud.product.entity.OrderInfo;
import com.zhangz.demo.spring.cloud.product.service.OrderInfoService;
import com.zhangz.spring.cloud.common.exception.BussinessException;
import com.zhangz.spring.cloud.common.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
    

    @Override
    public OrderInfo createOrder() {
        OrderInfo orderInfo = getNotOrdered();
        
        if (null == orderInfo){
            orderInfo = new OrderInfo();
            orderInfo.setId(UUIDUtils.getUUID32());
            orderInfo.setOrderStatus(0);
            orderInfo.setCreateTime(DateUtil.formatTime(new Date()));
            orderInfo.setUserId(123456);
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
        OrderInfo orderInfo =  orderInfoMapper.getOrderInfoByStatus(userId,0);
        if (null == orderInfo){
            throw new BussinessException("订单不存在");
        }
        return orderInfo;
    }
}
