package com.zhangz.demo.spring.cloud.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangz.demo.spring.cloud.platform.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

    List<Notice> pageList(@Param("title") String title, @Param("dataAddFrom") String dataAddFrom, @Param("dataAddTo") String dataAddTo, Page p);

    int getMaxId();
}
