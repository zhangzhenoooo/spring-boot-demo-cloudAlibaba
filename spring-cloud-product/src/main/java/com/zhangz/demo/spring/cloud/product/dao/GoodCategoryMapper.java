package com.zhangz.demo.spring.cloud.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangz.demo.spring.cloud.product.entity.GoodCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodCategoryMapper extends BaseMapper<GoodCategory> {

}
