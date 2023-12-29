package com.zhangz.demo.spring.cloud.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangz.demo.spring.cloud.product.entity.GoodCategory;
import com.zhangz.demo.spring.cloud.product.entity.GoodInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodInfoMapper extends BaseMapper<GoodInfo> {

    List<GoodInfo> listByCategory(@Param("from") int from,@Param("pageSize")  int pageSize,@Param("categoryId") String categoryId);
    
}
