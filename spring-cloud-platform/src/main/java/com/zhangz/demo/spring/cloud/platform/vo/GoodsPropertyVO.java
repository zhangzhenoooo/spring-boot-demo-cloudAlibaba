package com.zhangz.demo.spring.cloud.platform.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zhangz.demo.spring.cloud.platform.entity.GoodsProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.platform.vo
 * 
 * @Project：spring-cloud
 * 
 * @name：GoodsPropertyVO
 * 
 * @Date：2024/1/4 15:49
 * 
 * @Filename：GoodsPropertyVO
 */
@Data
public class GoodsPropertyVO implements Serializable {
    private String model;

    private long id;

    private String dateAdd;

    private String name;

    private int paixu;

    private int propertyId;

    private int userId;

    private List<GoodsProperty> childs;
}
