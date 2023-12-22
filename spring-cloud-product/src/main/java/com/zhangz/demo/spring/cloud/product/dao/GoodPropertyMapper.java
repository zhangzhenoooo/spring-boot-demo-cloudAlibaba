package com.zhangz.demo.spring.cloud.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangz.demo.spring.cloud.product.entity.GoodProperty;
import com.zhangz.demo.spring.cloud.product.entity.ShopInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

@Mapper
public interface GoodPropertyMapper extends BaseMapper<GoodProperty> {

    Set<GoodProperty> queryByPropertyId(@Param("propertyId") long propertyId);
    
}
