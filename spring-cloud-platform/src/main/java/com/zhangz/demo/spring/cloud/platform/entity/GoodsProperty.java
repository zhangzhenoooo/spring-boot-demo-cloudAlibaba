/**
 * Copyright 2023 json.cn
 */
package com.zhangz.demo.spring.cloud.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("good_property")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GoodsProperty implements Serializable {

    @TableId("id")
    private long id;
    
    @TableField("date_add")
    private String dateAdd;
    
    @TableField("name")
    private String name;

    @TableField("paixu")
    private int paixu;

    @TableField("property_id")
    private int propertyId;

    @TableField("user_id")
    private int userId;

}