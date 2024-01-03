package com.zhangz.demo.spring.cloud.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.platform.entity.GoodsCategory;
import com.zhangz.demo.spring.cloud.platform.entity.GoodsProperty;

import java.util.List;

/*
 *@Author：zhangz
 *@Package：com.zhangz.demo.spring.cloud.product.service
 *@Project：spring-cloud
 *@name：GoodInfoService
 *@Date：2023/12/12  17:41
 *@Filename：GoodInfoService
 */
public interface GoodsPropertyService extends IService<GoodsProperty> {
    List<GoodsProperty> listByPropertyId(Integer propertyId);

    void addOrUpdate(Long id, String name,Integer parentPropertyId) throws BussinessException;

}
