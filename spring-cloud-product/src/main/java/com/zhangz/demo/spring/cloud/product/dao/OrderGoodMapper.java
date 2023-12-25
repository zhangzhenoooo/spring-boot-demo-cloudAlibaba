package com.zhangz.demo.spring.cloud.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangz.demo.spring.cloud.product.entity.OrderGood;
import com.zhangz.demo.spring.cloud.product.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderGoodMapper extends BaseMapper<OrderGood> {

    List<OrderGood> queryByOrderIdAndStatus(@Param("orderId") String orderId,@Param("status") Integer status);

    void deleteByOrderId(String orderId);

    void changeStatusToOrdered(@Param("orderId") String orderId);
}
