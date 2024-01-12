package com.zhangz.demo.spring.cloud.platform.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TenantConfigDTO implements Serializable {
    private String dateUpdate;
    private String key;
    private String remark;
    private String value;
}