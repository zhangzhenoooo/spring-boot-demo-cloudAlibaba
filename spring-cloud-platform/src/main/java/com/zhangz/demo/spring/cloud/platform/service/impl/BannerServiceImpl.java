package com.zhangz.demo.spring.cloud.platform.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.platform.dao.BannerMapper;
import com.zhangz.demo.spring.cloud.platform.dto.BannerDTO;
import com.zhangz.demo.spring.cloud.platform.entity.Banner;
import com.zhangz.demo.spring.cloud.platform.entity.GoodInfo;
import com.zhangz.demo.spring.cloud.platform.entity.Notice;
import com.zhangz.demo.spring.cloud.platform.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.service.impl
 * 
 * @Project：spring-cloud
 * 
 * @name：GoodInfoServiceImpl
 * 
 * @Date：2023/12/12 17:42
 * 
 * @Filename：GoodInfoServiceImpl
 */
@Service
@Slf4j
@RefreshScope
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {
    @Resource
    private BannerMapper bannerMapper;
    @Value("${custome.pubUrl}")
    private String pubUrl;

    @Override
    public CommonPage pageList(Integer page, Integer pageSize) {
        Page p = new Page();
        p.setSize(pageSize);
        p.setCurrent(page);
        List<Banner> bannerList = bannerMapper.pageList(p);
        List<BannerDTO> collect = bannerList.stream().map(g -> {
            BannerDTO bannerDTO = BeanUtil.copyProperties(g, BannerDTO.class);
            bannerDTO.setPicUrl(pubUrl + bannerDTO.getPicUrl());
            return bannerDTO;
        }).collect(Collectors.toList());

        CommonPage commonPage = new CommonPage();
        commonPage.setList(collect);
        commonPage.setPageNum(page);
        commonPage.setTotal(p.getTotal());
        commonPage.setTotalPage(p.getTotal());
        return commonPage;

    }

    @Override
    public void insertOrUpdate(BannerDTO bannerDTO) {
        Long id = bannerDTO.getId();
        if (null == id) {
            Banner banner = BeanUtil.copyProperties(bannerDTO, Banner.class);
            banner.setId(bannerMapper.getMaxId() + 1);
            banner.setDateAdd(DateUtil.formatDateTime(new Date()));
            bannerMapper.insert(banner);
        } else {
            bannerMapper.updateById(BeanUtil.copyProperties(bannerDTO, Banner.class));
        }
    }

    @Override
    public void changeStatus(Integer id, Integer status) throws BussinessException {
        if (null == id || null == status) {
            throw new BussinessException("参数不能为空");
        }
        Banner notice = bannerMapper.selectById(id);
        if (null == notice) {
            throw new BussinessException("通知不存在");
        }
        notice.setStatus(status);
        bannerMapper.updateById(notice);
    }
}
