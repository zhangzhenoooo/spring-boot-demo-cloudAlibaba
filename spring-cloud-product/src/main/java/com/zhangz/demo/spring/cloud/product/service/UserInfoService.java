package com.zhangz.demo.spring.cloud.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangz.demo.spring.cloud.product.dto.wx.WxAuthDTO;
import com.zhangz.demo.spring.cloud.product.entity.UserInfo;
import com.zhangz.demo.spring.cloud.product.vo.user.UserDetailVO;
import com.zhangz.demo.spring.cloud.product.vo.user.UserInfoVO;
import com.zhangz.spring.cloud.common.exception.BussinessException;

public interface UserInfoService extends IService<UserInfo> {

    UserInfoVO loginByPhone(String phone, String password) throws BussinessException;

    UserInfoVO getCurrentLoginUserInfo();

    boolean checkToken(String token);

    WxAuthDTO wxappAuthorize(String code) throws Exception;

    UserDetailVO detail();

    void modifyNice(String nick);
    
}
