/**
 * Copyright 2023 json.cn
 */
package com.zhangz.demo.spring.cloud.product.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodProperty implements Serializable {
    private String dateAdd;
    private long id;
    private String name;
    private int paixu;
    private int propertyId;
    private int userId;

}