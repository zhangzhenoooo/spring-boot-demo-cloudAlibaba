package com.zhangz.demo.spring.cloud.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/*
 *@Author：zhangz
 *@Package：com.zhangz.demo.spring.cloud.product.entity
 *@Project：spring-cloud
 *@name：OrderInfo
 *@Date：2023/12/21  15:33
 *@Filename：OrderInfo
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
  private long remark;

  /**
   * 是否下单
   */
  @TableField("ordered")
  private boolean ordered;
  /**
   * 下单时间
   */
  @TableField("ordered_time")
  private boolean orderedTime;
  
}
