package com.zhangz.demo.spring.cloud.platform.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.platform.dao.UserInfoMapper;
import com.zhangz.demo.spring.cloud.platform.entity.GoodInfo;
import com.zhangz.demo.spring.cloud.platform.entity.UserInfo;
import com.zhangz.demo.spring.cloud.platform.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.platform.service.impl
 * 
 * @Project：spring-cloud
 * 
 * @name：GoodsPropertyServiceImpl
 * 
 * @Date：2024/1/2 16:49
 * 
 * @Filename：GoodsPropertyServiceImpl
 */
@Service
@Slf4j
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public CommonPage listByNamePhone(String userName, String phone, Integer page, Integer pageSize) {
        Page p = new Page();
        p.setSize(pageSize);
        p.setCurrent(page);
        List<UserInfo> userInfos = userInfoMapper.listByNamePhone(userName, phone, p);
        CommonPage commonPage = new CommonPage();
        commonPage.setList(userInfos);
        commonPage.setPageNum(page);
        commonPage.setTotalPage(p.getTotal());
        return commonPage;
    }
}
