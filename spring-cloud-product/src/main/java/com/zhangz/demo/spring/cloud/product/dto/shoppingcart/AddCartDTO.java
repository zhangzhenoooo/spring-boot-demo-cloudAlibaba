package com.zhangz.demo.spring.cloud.product.dto.shoppingcart;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.dto.shoppingcart
 * 
 * @Project：spring-cloud
 * 
 * @name：AddCartDTO
 * 
 * @Date：2023/12/19 11:31
 * 
 * @Filename：AddCartDTO
 */
@Data
public class AddCartDTO implements Serializable {
    private Long goodsId;
    private Integer number;
    private List<SkuItem> sku;
    private String addition;

}
