package com.zhangz.demo.spring.cloud.platform.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.platform.dao.UserInfoMapper;
import com.zhangz.demo.spring.cloud.platform.dto.UserInfoDTO;
import com.zhangz.demo.spring.cloud.platform.entity.GoodInfo;
import com.zhangz.demo.spring.cloud.platform.entity.UserInfo;
import com.zhangz.demo.spring.cloud.platform.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        commonPage.setTotalPage(p.getPages());
        commonPage.setTotal(p.getTotal());
        return commonPage;
    }

    @Override
    public void updateUserInfo(UserInfoDTO userInfoDTO) throws BussinessException {
        if (StringUtils.isEmpty(userInfoDTO.getUserId())) {
            throw new BussinessException("用户ID不能为空");
        }
        UserInfo userInfo = userInfoMapper.selectById(userInfoDTO.getUserId());
        if (null == userInfo) {
            throw new BussinessException("用户不存在");
        }

        userInfo.setUserName(userInfoDTO.getUserName());
        userInfo.setNick(userInfoDTO.getNick());
        userInfo.setAvatarUrl(userInfoDTO.getAvatarUrl());
        userInfoMapper.updateById(userInfo);
    }

    @Override
    public void updateStatus(String ids) throws BussinessException {
        if (StringUtils.isEmpty(ids)) {
            throw new BussinessException("参数不能为空");
        }
        List<String> collect = Arrays.stream(ids.split(",")).distinct().collect(Collectors.toList());
        userInfoMapper.updateStatus(collect);

    }

    @Override
    public void changeStatus(String userId, String status) throws BussinessException {
        if (StringUtils.isAnyEmpty(userId, status)) {
            throw new BussinessException("参数不能为空");
        }

        UserInfo userInfo = userInfoMapper.selectById(userId);
        if (null == userInfo) {
            throw new BussinessException("用户不存在");
        }
        userInfo.setStatus(Integer.parseInt(status));

        userInfoMapper.updateById(userInfo);
    }
}
