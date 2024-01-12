package com.zhangz.demo.spring.cloud.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.product.config.CustomConfig;
import com.zhangz.demo.spring.cloud.product.dao.BannerMapper;
import com.zhangz.demo.spring.cloud.product.entity.Banner;
import com.zhangz.demo.spring.cloud.product.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.service.impl
 * 
 * @Project：spring-cloud
 * 
 * @name：NoticeServiceImpl
 * 
 * @Date：2024/1/11 15:26
 * 
 * @Filename：NoticeServiceImpl
 */
@Service
@Slf4j
@RefreshScope
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Resource
    private BannerMapper bannerMapper;
    @Resource
    private CustomConfig customConfig;

    @Override
    public List<Banner> listActive() {
        List<Banner> banners = bannerMapper.listActive();
        List<Banner> collect = banners.stream().map(g -> g.setPicUrl(customConfig.getPubUrl() + g.getPicUrl())).collect(Collectors.toList());
        return collect;
    }
}
