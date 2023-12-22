package com.zhangz.demo.spring.cloud.product.vo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 *@Author：zhangz
 *@Package：com.zhangz.demo.spring.cloud.product.vo
 *@Project：spring-cloud
 *@name：OrderInfoVO
 *@Date：2023/12/22  13:59
 *@Filename：OrderInfoVO
 */

@Data
public class OrderInfoVO implements Serializable {
    
    private String id;
    /**
     * 桌号
     */
    private String tableCode;
    /**
     * 菜品数量
     */
    private int goodsNumber;
    /**
     * 消费金额
     */
    private BigDecimal amount;
    /**
     * 应付金额
     */
    private BigDecimal amountReal;
 
}
