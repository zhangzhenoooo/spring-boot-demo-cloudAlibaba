package com.zhangz.demo.spring.cloud.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangz.demo.spring.cloud.platform.entity.GoodsProperty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.platform.dao
 * 
 * @Project：spring-cloud
 * 
 * @name：GoodPropertyMapper
 * 
 * @Date：2024/1/2 16:47
 * 
 * @Filename：GoodPropertyMapper
 */
@Mapper
public interface GoodsPropertyMapper extends BaseMapper<GoodsProperty> {
    List<GoodsProperty> listByPropertyId(@Param("propertyId") Integer propertyId, @Param("name") String name);

    int getMaxId();
}
