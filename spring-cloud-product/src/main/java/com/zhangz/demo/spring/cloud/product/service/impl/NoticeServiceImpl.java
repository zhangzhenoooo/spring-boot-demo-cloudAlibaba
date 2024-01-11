package com.zhangz.demo.spring.cloud.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.product.dao.NoticeMapper;
import com.zhangz.demo.spring.cloud.product.entity.Notice;
import com.zhangz.demo.spring.cloud.product.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public Notice lastOne() {
        return noticeMapper.lastOne();
    }
}
