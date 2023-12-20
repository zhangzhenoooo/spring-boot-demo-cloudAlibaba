package com.zhangz.demo.spring.cloud.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangz.demo.spring.cloud.product.entity.GoodCategory;
import com.zhangz.demo.spring.cloud.product.entity.GoodInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodInfoMapper extends BaseMapper<GoodInfo> {

}
