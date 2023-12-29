package com.zhangz.demo.spring.cloud.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.platform.entity.GoodsCategory;

/*
 *@Author：zhangz
 *@Package：com.zhangz.demo.spring.cloud.product.service
 *@Project：spring-cloud
 *@name：GoodInfoService
 *@Date：2023/12/12  17:41
 *@Filename：GoodInfoService
 */
public interface GoodsCategoryService extends IService<GoodsCategory> {
 CommonPage<GoodsCategory> listByShopIdAndIdName(Integer shopid, String id,String categoryName,Integer pageNum,Integer pageSize);

 void removeAllByShopId(Long shopid) throws BussinessException;

 void insertOrUpdate(Long id, String name,Long shopid) throws BussinessException;
 
}
