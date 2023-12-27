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
 * @name：DefaultShopConfig
 * 
 * @Date：2023/12/25 14:45
 * 
 * @Filename：DefaultShopConfig
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "product.shop.default")
public class DefaultShopConfig implements Serializable {

    private String shopId;
    private String shopName;
}
