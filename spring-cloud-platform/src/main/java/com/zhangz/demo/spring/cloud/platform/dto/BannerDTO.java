/**
 * Copyright 2023 json.cn
 */
package com.zhangz.demo.spring.cloud.platform.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;

/**
 * Auto-generated: 2023-12-08 16:56:6
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
public class BannerDTO implements Serializable {

    private Long id;
    private Integer businessId;
    private String dateAdd;
    private String linkType;
    private Integer paixu;
    private String picUrl;
    private Integer shopId;
    private String status;
    private String statusStr;
    private String title;
    private String type;
    private Integer userId;

}