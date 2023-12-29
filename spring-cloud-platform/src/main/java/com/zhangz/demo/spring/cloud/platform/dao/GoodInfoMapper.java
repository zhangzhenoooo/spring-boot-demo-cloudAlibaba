package com.zhangz.demo.spring.cloud.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangz.demo.spring.cloud.platform.entity.GoodInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodInfoMapper extends BaseMapper<GoodInfo> {

    List<GoodInfo> listByNameCategoryName(@Param("goodsName")String goodsName, @Param("categoryId") String categoryId, Page p);
    
}
