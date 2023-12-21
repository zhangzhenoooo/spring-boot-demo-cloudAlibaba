package com.zhangz.demo.spring.cloud.product.dto.shoppingcart;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkuNamePair implements Serializable {
    private String  optionName;
    private String  optionValueName;
}