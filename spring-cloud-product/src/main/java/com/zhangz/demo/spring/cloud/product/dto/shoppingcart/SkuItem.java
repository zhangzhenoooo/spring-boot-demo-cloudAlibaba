package com.zhangz.demo.spring.cloud.product.dto.shoppingcart;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkuItem implements Serializable {
    private Long  optionId;
    private Long  optionValueId;
}