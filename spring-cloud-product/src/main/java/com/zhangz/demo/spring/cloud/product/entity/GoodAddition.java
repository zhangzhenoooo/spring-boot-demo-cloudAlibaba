/**
 * Copyright 2023 json.cn
 */
package com.zhangz.demo.spring.cloud.product.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2023-12-12 16:19:51
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
public class GoodAddition implements Serializable {

    private int categoryId;
    private int id;
    private List<GoodAdditionItem> items;
    private String name;
    private boolean required;
    private int type;

}