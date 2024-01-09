/**
 * Copyright 2023 json.cn
 */
package com.zhangz.demo.spring.cloud.platform.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Auto-generated: 2023-12-08 17:17:38
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
public class GoodsInfoDTO implements Serializable {
    private Long id;
    private int shopId;
    private long categoryId;
    private String name;
    private String subname;
    private int paixu;
    private String recommendStatus;
    private String recommendStatusStr;
    private String hidden;
    private String afterSale;
    private String status;
    private String content;
    private BigDecimal originalPrice;
    private String unit;
    private BigDecimal minPrice;
    private int tax;
    private int minScore;
    private int gotScore;
    private int gotScoreType;
    private int minBuyNumber;
    private long stores;
    private Long storeAlertNum;
    private String pic;
    /**
     *  规格 jsonArray
     */
    private String properties;
     
}