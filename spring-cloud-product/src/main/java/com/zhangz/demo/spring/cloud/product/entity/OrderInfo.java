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
@TableName("order_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderInfo implements Serializable {

    @TableId("id")
    private String id;

    @TableField("user_id")
    private long userId;

    @TableField("create_time")
    private String createTime;

    @TableField("remark")
    private String remark;
    
    /**
     * 订单状态 0 ： 未下单 1： 已下单 2 ： 已付款
     */
    @TableField("order_status")
    private int orderStatus;

    /**
     * 下单时间
     */
    @TableField("ordered_time")
    private String orderedTime;

    /**
     * 支付时间
     */
    @TableField("pay_time")
    private String payTime;

    /**
     * 总金额
     */
    @TableField("amount")
    private BigDecimal amount;

    /**
     * 实际付款金额
     */
    @TableField("amount_real")
    private BigDecimal amountReal;
    /**
     * 菜品数量
     */
    @TableField("goods_number")
    private int goodsNumber;
    
}
