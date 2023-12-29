package com.zhangz.demo.spring.cloud.product.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.zhangz.demo.spring.cloud.cache.config.service.TokenService;
import com.zhangz.demo.spring.cloud.product.dto.wx.WxLoginResDTO;
import com.zhangz.demo.spring.cloud.product.service.WxService;
import com.zhangz.demo.spring.cloud.common.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.service.impl
 * 
 * @Project：spring-cloud
 * 
 * @name：WxServiceImpl
 * 
 * @Date：2023/12/26 15:15
 * 
 * @Filename：WxServiceImpl
 */

@Service
@Slf4j
public class WxServiceImpl implements WxService {

    @Resource
    private TokenService tokenService;

    @Override
    public WxLoginResDTO refreshSession(String js_code) throws Exception {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={}&secret={}&js_code={}&grant_type=authorization_code";
        url = StrUtil.format(url, "wxfa21b014a9eaf909", "f35f4322745571532e1a23604b780a9f", js_code);

        String s = HttpUtil.get(url);
        if (StringUtils.isEmpty(s)) {
            throw new SystemException("微信登录失败,获取信息失败");
        }
        JSONObject jsonObject = JSONObject.parseObject(s);
        String errcode = jsonObject.getString("errcode");
        if (StringUtils.isNotEmpty(errcode)) {
            String errmsg = jsonObject.getString("errmsg");
            log.error("微信获取openid异常,|errcode:{},message:{}", errcode, errmsg);
            throw new SystemException("系统异常", "微信异常");
        }
        WxLoginResDTO wxLoginResDTO = JSONObject.parseObject(s, WxLoginResDTO.class);
        return wxLoginResDTO;
    }
}
