package com.zhangz.demo.spring.cloud.platform.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*
 *@Author：zhangz
 *@Package：com.zhangz.demo.spring.cloud.platform.dto
 *@Project：spring-cloud
 *@name：DeleteGoodsDTO
 *@Date：2024/1/3  17:12
 *@Filename：DeleteGoodsDTO
 */

@Data
public class DeleteGoodsDTO implements Serializable
{
    private List<Long> ids;
}
