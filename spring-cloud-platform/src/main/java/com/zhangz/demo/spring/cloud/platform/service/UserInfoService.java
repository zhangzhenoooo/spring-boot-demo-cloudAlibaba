package com.zhangz.demo.spring.cloud.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.platform.dto.UserInfoDTO;
import com.zhangz.demo.spring.cloud.platform.entity.UserInfo;

/*
 *@Author：zhangz
 *@Package：com.zhangz.demo.spring.cloud.product.service
 *@Project：spring-cloud
 *@name：GoodInfoService
 *@Date：2023/12/12  17:41
 *@Filename：GoodInfoService
 */
public interface UserInfoService extends IService<UserInfo> {


    CommonPage listByNamePhone(String userName, String phone,Integer page, Integer pageSize);

    void updateUserInfo(UserInfoDTO userInfoDTO) throws BussinessException;

    void updateStatus(String ids) throws BussinessException;

    void changeStatus(String userId, String status) throws BussinessException;

}
