package com.zhangz.demo.spring.cloud.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangz.demo.spring.cloud.platform.entity.GoodsCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsCategoryMapper extends BaseMapper<GoodsCategory> {


    List<GoodsCategory> listByShopIdAndIdName(@Param("categoryName") String categoryName, @Param("shopid") Integer shopid, @Param("id") String id, Page page);

    void removeAllByShopId(@Param("shopid") Long shopid);
}
