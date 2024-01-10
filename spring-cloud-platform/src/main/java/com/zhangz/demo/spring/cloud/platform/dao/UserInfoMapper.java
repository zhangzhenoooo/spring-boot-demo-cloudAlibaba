package com.zhangz.demo.spring.cloud.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangz.demo.spring.cloud.platform.entity.GoodInfo;
import com.zhangz.demo.spring.cloud.platform.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {


    List<UserInfo> listByNamePhone(@Param("userName") String userName,@Param("phone") String phone, Page p);

    void updateStatus(@Param("userIds") List<String> userIds);

}
