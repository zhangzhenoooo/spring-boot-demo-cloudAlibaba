package com.zhangz.demo.spring.cloud.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.product.config.CustomConfig;
import com.zhangz.demo.spring.cloud.product.dao.GoodInfoMapper;
import com.zhangz.demo.spring.cloud.product.dto.UserGoodSelect;
import com.zhangz.demo.spring.cloud.product.entity.GoodInfo;
import com.zhangz.demo.spring.cloud.product.entity.GoodProperty;
import com.zhangz.demo.spring.cloud.product.service.GoodInfoService;
import com.zhangz.demo.spring.cloud.product.service.GoodPropertyService;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class GoodInfoServiceImpl extends ServiceImpl<GoodInfoMapper, GoodInfo> implements GoodInfoService {
    @Resource
    private GoodInfoMapper goodInfoMapper;
    @Resource
    private GoodPropertyService goodPropertyService;

    @Resource
    private CustomConfig customConfig;

    @Override
    public List<GoodInfo> listByCategory(int page, int pageSize, String categoryId) {
        if (StringUtils.isEmpty(categoryId)) {
            return new ArrayList<>(0);
        }
        int from = (page - 1) * pageSize;
        List<GoodInfo> goodInfoList = goodInfoMapper.listByCategory(from, pageSize, categoryId);
        return goodInfoList.stream().map(g -> g.setPic(customConfig.getPubUrl() + g.getPic())).collect(Collectors.toList());
    }

    @Override
    public UserGoodSelect price(Integer goodsId, String propertyChildIds) throws BussinessException {
        GoodInfo goodInfo = goodInfoMapper.selectById(goodsId);
        if (null == goodInfo) {
            throw new BussinessException("产品不存在");
        }
        StringBuffer propertyChildNamesBuf = new StringBuffer();
        String[] split = propertyChildIds.split(",");
        for (String s : split) {
            String[] split1 = s.split(":");
            GoodProperty key = goodPropertyService.getById(split1[0]);
            GoodProperty value = goodPropertyService.getById(split1[1]);
            propertyChildNamesBuf.append(key.getName());
            propertyChildNamesBuf.append(":");
            propertyChildNamesBuf.append(value.getName());
        }
        UserGoodSelect u = BeanUtil.copyProperties(goodInfo, UserGoodSelect.class);
        u.setGoodsId(goodInfo.getId());
        u.setPropertyChildIds(propertyChildIds);
        u.setPropertyChildNames(propertyChildNamesBuf.toString());
        u.setPrice(goodInfo.getOriginalPrice());
        return u;
    }
}
