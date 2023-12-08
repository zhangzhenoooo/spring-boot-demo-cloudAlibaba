/**
  * Copyright 2023 json.cn 
  */
package com.zhangz.demo.spring.cloud.product.entity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Auto-generated: 2023-12-08 16:56:6
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
public class Notice implements Serializable {

    private String content;
    private Date dateAdd;
    private int id;
    private boolean isRemind;
    private boolean isShow;
    private int remindUid;
    private String title;
    private int userId;
    

}