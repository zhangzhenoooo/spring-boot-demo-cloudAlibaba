package com.zhangz.demo.spring.cloud.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.platform.dto.BannerDTO;
import com.zhangz.demo.spring.cloud.platform.entity.Banner;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.service
 * 
 * @Project：spring-cloud
 * 
 * @name：GoodInfoService
 * 
 * @Date：2023/12/12 17:41
 * 
 * @Filename：GoodInfoService
 */
public interface BannerService extends IService<Banner> {

    CommonPage pageList(Integer page, Integer pageSize);

    void insertOrUpdate(BannerDTO bannerDTO);

    void changeStatus(Integer id, Integer status) throws BussinessException;
}
