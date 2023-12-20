/**
 * Copyright 2023 json.cn
 */
package com.zhangz.demo.spring.cloud.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Auto-generated: 2023-12-08 16:56:6
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("notice")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Notice implements Serializable {

    @TableId("id")
    private int id;
    
    @TableField("content")
    private String content;

    @TableField("date_add")
    private String dateAdd;


    @TableField("is_remind")
    private boolean isRemind;

    @TableField("is_show")
    private boolean isShow;

    @TableField("remind_uid")
    private int remindUid;

    @TableField("title")
    private String title;

    @TableField("user_id")
    private int userId;

}