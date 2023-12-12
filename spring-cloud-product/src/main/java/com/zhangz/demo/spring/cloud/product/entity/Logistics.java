package com.zhangz.demo.spring.cloud.product.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.entity
 * 
 * @Project：spring-cloud
 * 
 * @name：Logistics
 * 
 * @Date：2023/12/12 15:31
 * 
 * @Filename：Logistics
 */
@Data
public class Logistics implements Serializable {
    private int feeType;
    private String feeTypeStr;
    private boolean isFree;
    private List<LogisticsDetail> details;

}
