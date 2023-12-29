package com.zhangz.demo.spring.cloud.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.cache.config.service.TokenService;
import com.zhangz.demo.spring.cloud.product.constant.UserLevelEnum;
import com.zhangz.demo.spring.cloud.product.dao.UserInfoMapper;
import com.zhangz.demo.spring.cloud.product.dto.wx.WxAuthDTO;
import com.zhangz.demo.spring.cloud.product.dto.wx.WxLoginResDTO;
import com.zhangz.demo.spring.cloud.product.entity.UserInfo;
import com.zhangz.demo.spring.cloud.product.interceptor.TokenMana;
import com.zhangz.demo.spring.cloud.product.service.UserInfoService;
import com.zhangz.demo.spring.cloud.product.service.WxService;
import com.zhangz.demo.spring.cloud.product.vo.user.UserDetailVO;
import com.zhangz.demo.spring.cloud.product.vo.user.UserInfoVO;
import com.zhangz.demo.spring.cloud.product.vo.user.UserLevelVO;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.common.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.service.impl
 * 
 * @Project：spring-cloud
 * 
 * @name：UserInfoServiceImpl
 * 
 * @Date：2023/12/25 17:15
 * 
 * @Filename：UserInfoServiceImpl
 */

@Service
@Slf4j
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private TokenService tokenService;

    @Resource
    private WxService wxService;

    @Override
    public UserInfoVO loginByPhone(String phone, String password) throws BussinessException {
        UserInfo userInfo = userInfoMapper.selectByPhone(phone);

        if (null == userInfo) {
            throw new BussinessException("该手机号未注册");
        }

        if (!password.equals(userInfo.getPassword())) {
            throw new BussinessException("密码错误");
        }

        // 生成token
        String token = tokenService.cacheToken(userInfo.getUserId());
        UserInfoVO userInfoVO = BeanUtil.copyProperties(userInfo, UserInfoVO.class);
        userInfoVO.setToken(token);
        return userInfoVO;
    }

    @Override
    public UserInfoVO getCurrentLoginUserInfo() {
        String token = TokenMana.getToken();
        String userId = tokenService.getUserIdByTokenPhone(token);

        UserInfo userInfo = userInfoMapper.selectById(userId);
        UserInfoVO userInfoVO = BeanUtil.copyProperties(userInfo, UserInfoVO.class);

        userInfoVO.setToken(token);
        return userInfoVO;
    }

    @Override
    public boolean checkToken(String token) {
        return !tokenService.isExpired(token);

    }

    @Override
    public WxAuthDTO wxappAuthorize(String code) throws Exception {
        String token = TokenMana.getToken();
        boolean b = tokenService.isExpired(token);
        String openid = null;
        WxLoginResDTO wxLoginResDTO = null;
        if (!b) {
            UserInfoVO currentLoginUserInfo = getCurrentLoginUserInfo();
            return WxAuthDTO.builder().token(currentLoginUserInfo.getToken()).uid(currentLoginUserInfo.getUserId()).build();
        }

        // 失效
        wxLoginResDTO = wxService.refreshSession(code);
        openid = wxLoginResDTO.getOpenid();
        UserInfo userInfo = userInfoMapper.selectByWxOpenId(openid);
        if (null == userInfo) {
            userInfo = new UserInfo();
            String uid = UUIDUtils.getUUID32();
            userInfo.setUserId(uid);
            userInfo.setUserName("微信用户");
            userInfo.setPhone("000000");
            userInfo.setCreateTime(DateUtil.formatDateTime(new Date()));
            userInfo.setStatus(1);
            userInfo.setPassword("111111");
            userInfo.setOpenid(openid);
            userInfoMapper.insert(userInfo);
        }
        tokenService.cacheToken(userInfo.getUserId(),wxLoginResDTO.getSession_key());
        return WxAuthDTO.builder().token(wxLoginResDTO.getSession_key()).uid(userInfo.getUserId()).build();

    }

    @Override
    public UserDetailVO detail() {
        UserInfoVO userInfo = getCurrentLoginUserInfo();
        UserLevelVO userLevel = new UserLevelVO();
        int level = userInfo.getLevel();
        userLevel.setName(UserLevelEnum.getDescByState(level));
        return  UserDetailVO.builder().base(userInfo).userLevel(userLevel).build();
     }

    @Override
    public void modifyNice(String nick) {
        UserInfoVO userInfo = getCurrentLoginUserInfo();
        userInfoMapper.modifyNice(userInfo.getUserId(),nick);
    }
}
