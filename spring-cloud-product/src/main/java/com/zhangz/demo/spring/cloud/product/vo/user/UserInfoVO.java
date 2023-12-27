package com.zhangz.demo.spring.cloud.product.vo.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class UserInfoVO implements Serializable {

    private String userId;

    private String userName;
    private String nick;
    private String phone;

    private String createTime;

    private String remark;

    private String token;

    private int status;
    private String avatarUrl;

    private int level;

}
