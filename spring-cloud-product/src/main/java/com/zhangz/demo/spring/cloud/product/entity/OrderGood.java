package com.zhangz.demo.spring.cloud.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.entity
 * 
 * @Project：spring-cloud
 * 
 * @name：OrderInfo
 * 
 * @Date：2023/12/21 15:33
 * 
 * @Filename：OrderInfo
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("order_good")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderGood implements Serializable {

    @TableId("id")
    private String id;

    @TableField("order_id")
    private String orderId;

    @TableField("good_id")
    private long goodId;

    @TableField("number")
    private int number;
    /**
     * 规格 数组
     */
    @TableField("sku")
    private String sku;

    /**
     * 价格 价格 = number * goods unit price
     */
    @TableField("price")
    private BigDecimal price;

    @TableField("create_time")
    private String createTime;

    @TableField("remark")
    private String remark;
    /**
     * 菜品状态 0 :未下单，1:已经下单,2：制作中，3：已上菜
     */
    @TableField("status")
    private int status;
}
