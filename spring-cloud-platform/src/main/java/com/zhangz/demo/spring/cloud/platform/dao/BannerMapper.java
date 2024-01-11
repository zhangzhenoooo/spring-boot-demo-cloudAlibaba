package com.zhangz.demo.spring.cloud.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangz.demo.spring.cloud.platform.entity.Banner;
import com.zhangz.demo.spring.cloud.platform.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.platform.dao
 * 
 * @Project：spring-cloud
 * 
 * @name：BannerMapper
 * 
 * @Date：2024/1/11 10:44
 * 
 * @Filename：BannerMapper
 */
@Mapper
public interface BannerMapper extends BaseMapper<Banner> {
    int getMaxId();

    List<Banner> pageList(Page p);
}
