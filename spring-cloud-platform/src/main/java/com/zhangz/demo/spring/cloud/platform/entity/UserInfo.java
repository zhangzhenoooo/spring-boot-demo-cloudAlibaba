package com.zhangz.demo.spring.cloud.platform.entity;

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
@TableName("user_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo implements Serializable {

    @TableId("user_id")
    private String userId;

    @TableField("user_name")
    private String userName;

    @TableField("nick")
    private String nick;
    
    @TableField("avatar_url")
    private String avatarUrl;
    
    @TableField("phone")
    private String phone;

    @TableField("create_time")
    private String createTime;

    @TableField("remark")
    private String remark;

    @TableField("password")
    private String password;
    /**
     * 
     */
    @TableField("status")
    private int status;
    
    @TableField("openid")
    private String openid;
    /**
     * '用户等级 0：普通用户，1 : vip ,2:vvip , 3 :vvvip , 4: 大爷，5;上帝'
     */
    @TableField("level")
    private int level;

}
