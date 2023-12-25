package com.zhangz.demo.spring.cloud.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangz.demo.spring.cloud.product.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    OrderInfo getNotOrdered(@Param("userId") long userId);

    OrderInfo getOrderInfoByStatus(@Param("userId") long userId,@Param("orderStatus")int orderStatus);

    List<OrderInfo> listOrderInfoByStatus(@Param("userId") long userId,@Param("orderStatus") Integer status);

    int getDayCountUserOrder(@Param("userId")  long userId, @Param("date") String date);
}
