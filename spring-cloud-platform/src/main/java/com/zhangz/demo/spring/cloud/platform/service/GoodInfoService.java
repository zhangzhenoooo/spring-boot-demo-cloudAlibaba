package com.zhangz.demo.spring.cloud.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.platform.dto.GoodsInfoDTO;
import com.zhangz.demo.spring.cloud.platform.entity.GoodInfo;

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
    CommonPage listByNameCategoryName(String goodsName, String categoryName, Integer page, Integer pageSize);

    void add(GoodsInfoDTO goodsInfoDTO);

    GoodsInfoDTO goodsDetailById(Long id) throws BussinessException;
}
