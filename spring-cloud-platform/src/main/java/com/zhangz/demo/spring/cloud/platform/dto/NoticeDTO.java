/**
 * Copyright 2023 json.cn
 */
package com.zhangz.demo.spring.cloud.platform.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Auto-generated: 2023-12-08 16:56:6
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
public class NoticeDTO implements Serializable {

    private Integer id;

    private String content;

    private String dateAdd;

    private Integer isRemind;

    private Integer isShow;

    private Integer remindUid;

    private String title;

    private Integer userId;

}