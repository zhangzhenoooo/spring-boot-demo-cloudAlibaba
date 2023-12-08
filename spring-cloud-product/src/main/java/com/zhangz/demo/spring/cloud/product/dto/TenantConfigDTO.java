package com.zhangz.demo.spring.cloud.product.dto;

import lombok.Data;

import java.io.Serializable;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.dto
 * 
 * @Project：spring-cloud
 * 
 * @name：TenantConfigDTO
 * 
 * @Date：2023/12/8 16:21
 * 
 * @Filename：TenantConfigDTO
 */
@Data
public class TenantConfigDTO implements Serializable {
    private String dateUpdate ;
    private String key ;
    private String remark ;
    private String value ;
}
