package com.zhangz.demo.spring.cloud.product.service;

import com.zhangz.demo.spring.cloud.product.dto.wx.WxLoginResDTO;

import java.util.Map;

/*
 *@Author：zhangz
 *@Package：com.zhangz.demo.spring.cloud.product.service
 *@Project：spring-cloud
 *@name：WxService
 *@Date：2023/12/26  15:15
 *@Filename：WxService
 */
public interface WxService {
      WxLoginResDTO refreshSession(String js_code) throws Exception;

}
