package com.zhangz.demo.spring.cloud.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangz.demo.spring.cloud.product.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    OrderInfo getNotOrdered(@Param("userId") int userId);

    OrderInfo getOrderInfoByStatus(@Param("userId") int userId,@Param("orderStatus")int orderStatus);
}
