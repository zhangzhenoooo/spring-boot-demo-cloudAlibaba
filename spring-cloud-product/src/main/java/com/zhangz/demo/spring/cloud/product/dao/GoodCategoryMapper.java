package com.zhangz.demo.spring.cloud.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangz.demo.spring.cloud.product.entity.GoodCategory;
import com.zhangz.spring.cloud.common.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodCategoryMapper extends BaseMapper<GoodCategory> {

}
