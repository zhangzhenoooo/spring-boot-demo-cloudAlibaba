/**
  * Copyright 2023 json.cn 
  */
package com.zhangz.demo.spring.cloud.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Auto-generated: 2023-12-12 15:43:39
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
public class UserGoodSelect implements Serializable {

    private int fxType;
    private long goodsId;
    private long id;
    private BigDecimal originalPrice;
    private BigDecimal pingtuanPrice;
    private BigDecimal price;
    private String propertyChildIds;
    private String propertyChildNames;
    private int score;
    private long stores;
    private int userId;

}