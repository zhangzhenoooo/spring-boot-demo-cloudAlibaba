 
package com.zhangz.demo.spring.cloud.product.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * generated: 2023-12-08 16:44:15
 *
 * @author zhangz
 * @desc 商品分类
 */
@Data
public class GoodCategoryItem implements Serializable {

    private long id;
    private boolean isUse;
    private int level;
    private String name;
    private int paixu;
    private int pid;
    private int shopId;
    private int userId;
     

}