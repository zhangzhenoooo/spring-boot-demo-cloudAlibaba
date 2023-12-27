package com.zhangz.demo.spring.cloud.product.dto.wx;

import lombok.Data;

import java.io.Serializable;

/*
 *@Author：zhangz
 *@Package：com.zhangz.demo.spring.cloud.product.dto.wx
 *@Project：spring-cloud
 *@name：WxLoginResDTO
 *@Date：2023/12/26  15:29
 *@Filename：WxLoginResDTO
 */
@Data
public class WxLoginResDTO implements Serializable {
    private String session_key;
    private String openid;
 }
