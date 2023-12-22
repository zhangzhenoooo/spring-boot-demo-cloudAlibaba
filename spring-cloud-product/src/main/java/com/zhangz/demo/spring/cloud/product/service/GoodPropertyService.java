package com.zhangz.demo.spring.cloud.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangz.demo.spring.cloud.product.dto.GoodPropertyDTO;
import com.zhangz.demo.spring.cloud.product.entity.GoodProperty;

import java.util.Set;

/*
 *@Author：zhangz
 *@Package：com.zhangz.demo.spring.cloud.product.service
 *@Project：spring-cloud
 *@name：GoodPropertyService
 *@Date：2023/12/13  9:54
 *@Filename：GoodPropertyService
 */
public interface GoodPropertyService extends IService<GoodProperty> {
    Set<GoodPropertyDTO> queryBypropertyIds(Set<String> ids);
    
    Set<GoodProperty> queryByPropertyId(long propertyId);
}
