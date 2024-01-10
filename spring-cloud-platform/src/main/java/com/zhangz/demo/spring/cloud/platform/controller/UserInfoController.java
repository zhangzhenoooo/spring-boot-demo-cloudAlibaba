package com.zhangz.demo.spring.cloud.platform.controller;

import cn.hutool.core.io.FileTypeUtil;
import com.alibaba.fastjson.JSON;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.common.api.CommonResult;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.platform.dto.GoodsInfoDTO;
import com.zhangz.demo.spring.cloud.platform.dto.UserInfoDTO;
import com.zhangz.demo.spring.cloud.platform.service.UserInfoService;
import com.zhangz.spring.cloud.file.minio.config.MinioProperties;
import com.zhangz.spring.cloud.file.minio.service.MinIOService;
import com.zhangz.spring.cloud.file.minio.utils.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.springcloudplatform.controller
 * 
 * @Project：spring-cloud
 * 
 * @name：GoodsController
 * 
 * @Date：2023/12/28 11:03
 * 
 * @Filename：GoodsController
 */
@RestController
@Slf4j
@RequestMapping("/api/User")
@Api(tags = "用户管理")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @ApiOperation(value = "用户列表", notes = "用户列表")
    @PostMapping("/list")
    @ResponseBody
    public CommonResult add(String userName, String phone, Integer page, Integer pageSize) {
        log.info("list，userName：{},phone：{}", userName, phone);
        CommonPage commonPage = userInfoService.listByNamePhone(userName, phone, page, pageSize);
        return CommonResult.success(commonPage);
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @PostMapping("/update")
    @ResponseBody
    public CommonResult update(UserInfoDTO userInfoDTO) throws BussinessException {
        log.info("修改用户信息：{}", JSON.toJSONString(userInfoDTO));
        userInfoService.updateUserInfo(userInfoDTO);
        return CommonResult.success();
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @DeleteMapping("/delete")
    @ResponseBody
    public CommonResult delete(String ids) throws BussinessException {
        log.info("删除用户：{}", ids);
        userInfoService.updateStatus(ids);
        return CommonResult.success();
    }

    @ApiOperation(value = "修改用户状态", notes = "修改用户状态")
    @GetMapping("/lock")
    @ResponseBody
    public CommonResult changeStatus(String userId, String status) throws BussinessException {
        log.info("修改用户状态：userId{}:status{}", userId, status);
        userInfoService.changeStatus(userId, status);
        return CommonResult.success();
    }
}
