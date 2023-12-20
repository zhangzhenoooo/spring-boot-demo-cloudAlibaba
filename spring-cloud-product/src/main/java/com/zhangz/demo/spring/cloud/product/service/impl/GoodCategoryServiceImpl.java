package com.zhangz.demo.spring.cloud.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.product.dao.GoodCategoryMapper;
import com.zhangz.demo.spring.cloud.product.dao.GoodInfoMapper;
import com.zhangz.demo.spring.cloud.product.entity.GoodCategory;
import com.zhangz.demo.spring.cloud.product.entity.GoodInfo;
import com.zhangz.demo.spring.cloud.product.service.GoodCategoryService;
import com.zhangz.demo.spring.cloud.product.service.GoodInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class GoodCategoryServiceImpl extends ServiceImpl<GoodCategoryMapper, GoodCategory> implements GoodCategoryService {}
