package com.zhangz.demo.spring.cloud.platform.dto;

import lombok.Data;

import java.io.Serializable;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.platform.dto
 * 
 * @Project：spring-cloud
 * 
 * @name：UserInfoDTO
 * 
 * @Date：2024/1/10 14:58
 * 
 * @Filename：UserInfoDTO
 */
@Data
public class UserInfoDTO implements Serializable {
    private String userId;
    private String userName;
    private String nick;
    private String avatarUrl;
}
