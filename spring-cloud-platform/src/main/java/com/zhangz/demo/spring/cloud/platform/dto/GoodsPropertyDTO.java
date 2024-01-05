/**
 * Copyright 2024 json.cn
 */
package com.zhangz.demo.spring.cloud.platform.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Auto-generated: 2024-01-04 15:26:1
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
public class GoodsPropertyDTO implements Serializable {
    private Map<String, String> properties;
    private String name;
    private String originalPrice;
    private String minPrice;
    private int pingtuanPrice;
    private String weight;
    private int score;
    private String stores;
    private int fxType;
    private String earningsPercent;
    private String earningsPercent2;
    private String code;
    private String type;

}