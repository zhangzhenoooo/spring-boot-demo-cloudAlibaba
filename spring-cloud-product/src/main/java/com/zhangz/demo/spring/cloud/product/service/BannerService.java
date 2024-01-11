package com.zhangz.demo.spring.cloud.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangz.demo.spring.cloud.product.entity.Banner;
import com.zhangz.demo.spring.cloud.product.entity.Notice;

import java.util.List;

public interface BannerService extends IService<Banner> {

    List<Banner> listActive();

}
