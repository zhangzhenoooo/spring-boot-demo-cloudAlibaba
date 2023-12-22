package com.zhangz.demo.spring.cloud.product.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.product.dao.GoodInfoMapper;
import com.zhangz.demo.spring.cloud.product.entity.GoodInfo;
import com.zhangz.demo.spring.cloud.product.service.GoodInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
public class GoodInfoServiceImpl extends ServiceImpl<GoodInfoMapper, GoodInfo> implements GoodInfoService {
    @Resource
    private GoodInfoMapper goodInfoMapper;
    
    @Override
    public List<GoodInfo> listByCategory(int page, int pageSize, String categoryId) {
        if (StringUtils.isEmpty(categoryId)){
            return new ArrayList<>(0);
        }
        int from = (page - 1) * pageSize;
        return goodInfoMapper.listByCategory(from,pageSize, categoryId);
    }
}
