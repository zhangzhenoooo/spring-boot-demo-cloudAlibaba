package com.zhangz.demo.spring.cloud.product.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.vo
 * 
 * @Project：spring-cloud
 * 
 * @name：OrderedGoodsVO
 * 
 * @Date：2023/12/22 11:42
 * 
 * @Filename：OrderedGoodsVO
 */
@Data
public class OrderedGoodsVO implements Serializable {
    private BigDecimal amount;
    private String goodsName;
    private String property;
    private String pic;
    private int number;
    /**
     * 0:待确认 1:已下厨 2:已上菜
     */
    private int cyTableStatus;
 
}
