package com.zhangz.demo.spring.cloud.product.dto.wx;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WxAuthDTO implements Serializable {
    private String token;
    private String uid;
 }
