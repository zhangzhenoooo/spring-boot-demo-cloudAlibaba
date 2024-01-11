package com.zhangz.demo.spring.cloud.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.platform.dto.NoticeDTO;
import com.zhangz.demo.spring.cloud.platform.entity.Notice;

import javax.annotation.Resource;

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
public interface NoticeService extends IService<Notice> {
    CommonPage pageList(String title, String dataAddFrom, String dataAddTo, Integer page, Integer pageSize);

    void insertOrUpdate(NoticeDTO notice);

    void changeStatus(Integer id, Integer isShow) throws BussinessException;

}
