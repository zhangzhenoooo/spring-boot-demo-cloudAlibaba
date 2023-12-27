package com.zhangz.demo.spring.cloud.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangz.demo.spring.cloud.product.entity.UserInfo;
import com.zhangz.spring.cloud.common.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    UserInfo selectByPhone(@Param("phone") String phone);

    UserInfo selectByWxOpenId(@Param("openid") String openid);

    void modifyNice(@Param("userId")String userId,@Param("nick") String nick);
}
