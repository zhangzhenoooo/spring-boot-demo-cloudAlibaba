/**
  * Copyright 2023 json.cn 
  */
package com.zhangz.demo.spring.cloud.product.dto;
import com.zhangz.demo.spring.cloud.product.entity.GoodProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Auto-generated: 2023-12-11 17:19:51
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
public class GoodPropertyDTO implements Serializable {

    private String dateAdd;
    private long id;
    private String name;
    private int paixu;
    private int propertyId;
    private int userId;
    private Set<GoodProperty> childsCurGoods;

}