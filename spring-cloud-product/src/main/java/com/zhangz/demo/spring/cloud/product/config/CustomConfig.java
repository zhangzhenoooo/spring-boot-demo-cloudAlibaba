package com.zhangz.demo.spring.cloud.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.config
 * 
 * @Project：spring-cloud
 * 
 * @name：CustomConfig
 * 
 * @Date：2024/1/12 14:13
 * 
 * @Filename：CustomConfig
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "custome")
public class CustomConfig implements Serializable {
    private String pubUrl;
}
