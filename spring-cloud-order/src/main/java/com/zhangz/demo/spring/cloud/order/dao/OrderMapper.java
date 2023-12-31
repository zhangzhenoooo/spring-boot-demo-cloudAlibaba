package com.zhangz.demo.spring.cloud.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangz.spring.cloud.common.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    
    Order queryById(@Param("oid") String oid);
}
