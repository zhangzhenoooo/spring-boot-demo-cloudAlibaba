package com.zhangz.demo.spring.cloud.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangz.demo.spring.cloud.product.entity.GoodInfo;

import java.util.List;

/*
 *@Author：zhangz
 *@Package：com.zhangz.demo.spring.cloud.product.service
 *@Project：spring-cloud
 *@name：GoodInfoService
 *@Date：2023/12/12  17:41
 *@Filename：GoodInfoService
 */
public interface GoodInfoService extends IService<GoodInfo> {
    List<GoodInfo> listByCategory(int page, int pageSize, String categoryId);
}
