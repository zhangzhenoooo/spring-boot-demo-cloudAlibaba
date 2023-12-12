package com.zhangz.demo.spring.cloud.product.entity;

import lombok.Data;

import java.io.Serializable;

/*
 *@Author：zhangz
 *@Package：com.zhangz.demo.spring.cloud.product.entity
 *@Project：spring-cloud
 *@name：LogisticsDetail
 *@Date：2023/12/12  15:32
 *@Filename：LogisticsDetail
 */
@Data
public class LogisticsDetail implements Serializable {
    private int addAmount;
    private int addNumber;
    private int firstAmount;
    private int firstNumber;
    private int type;
    private int userId;
}
