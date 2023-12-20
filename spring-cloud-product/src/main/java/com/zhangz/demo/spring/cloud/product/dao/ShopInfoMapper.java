package com.zhangz.demo.spring.cloud.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangz.demo.spring.cloud.product.entity.Notice;
import com.zhangz.demo.spring.cloud.product.entity.ShopInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopInfoMapper extends BaseMapper<ShopInfo> {

}
