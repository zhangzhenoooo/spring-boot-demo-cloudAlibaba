/**
 * Copyright 2024 json.cn
 */
package com.zhangz.demo.spring.cloud.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Auto-generated: 2024-01-11 10:40:37
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("banner")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Banner {

    @TableId("id")
    private long id;
    @TableField("business_id")
    private int businessId;
    @TableField("date_add")
    private String dateAdd;
    @TableField("link_type")
    private int linkType;
    @TableField("paixu")
    private int paixu;
    @TableField("pic_url")
    private String picUrl;
    @TableField("shop_id")
    private int shopId;
    @TableField("status")
    private int status;
    @TableField("title")
    private String title;
    @TableField("type")
    private String type;
    @TableField("user_id")
    private int userId;
}