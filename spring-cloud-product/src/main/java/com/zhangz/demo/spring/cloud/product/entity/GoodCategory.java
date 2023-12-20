
package com.zhangz.demo.spring.cloud.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * generated: 2023-12-08 16:44:15
 *
 * @author zhangz
 * @desc 商品分类
 */
@Data

@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("good_category")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GoodCategory implements Serializable {
    @TableId("id")
    private long id;
    @TableField("is_use")
    private boolean isUse;
    @TableField("level")
    private int level;
    @TableField("name")
    private String name;
    @TableField("paixu")
    private int paixu;
    @TableField("pid")
    private int pid;
    @TableField("shop_id")
    private int shopId;
    @TableField("user_id")
    private int userId;

}