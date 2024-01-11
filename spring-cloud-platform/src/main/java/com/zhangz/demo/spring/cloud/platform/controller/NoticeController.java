package com.zhangz.demo.spring.cloud.platform.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Pair;
import com.alibaba.fastjson.JSON;
import com.zhangz.demo.spring.cloud.common.api.CommonPage;
import com.zhangz.demo.spring.cloud.common.api.CommonResult;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.platform.dto.DeleteGoodsDTO;
import com.zhangz.demo.spring.cloud.platform.dto.GoodsInfoDTO;
import com.zhangz.demo.spring.cloud.platform.dto.NoticeDTO;
import com.zhangz.demo.spring.cloud.platform.entity.Notice;
import com.zhangz.demo.spring.cloud.platform.service.GoodInfoService;
import com.zhangz.demo.spring.cloud.platform.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
@RequestMapping("/api/notice")
@Api(tags = "通知管理")
public class NoticeController {

    @Resource
    private NoticeService noticeService;

    @ApiOperation(value = "通知列表", notes = "通知列表")
    @PostMapping("/list")
    @ResponseBody
    public CommonResult list(String title, String dataAddFrom, Integer page, Integer pageSize) {
        String dataAddTo = "";
        if (StringUtils.isNotEmpty(dataAddFrom)) {
            String[] split = dataAddFrom.split(",");
            dataAddFrom = split[0];
            dataAddTo = split[1];
        }
        CommonPage commonPage = noticeService.pageList(title, dataAddFrom, dataAddTo, page, pageSize);
        return CommonResult.success(commonPage);
    }

    @ApiOperation(value = "通知详情", notes = "通知详情")
    @GetMapping("/detail")
    @ResponseBody
    public CommonResult detail(Long id) throws BussinessException {
        log.info("通知详情，通知id：{}", id);
        Notice notice = noticeService.getById(id);
        return CommonResult.success(notice);
    }

    @ApiOperation(value = "删除通知", notes = "删除通知")
    @DeleteMapping("/delete")
    @ResponseBody
    public CommonResult delete(Long id) {
        log.info("删除通知，通知id：{}", id);
        noticeService.removeById(id);
        return CommonResult.success();
    }

    @ApiOperation(value = "新增通知", notes = "新增通知")
    @PostMapping("/add")
    @ResponseBody
    public CommonResult add(NoticeDTO notice) {
        log.info("新增通知，通知信息：{}", JSON.toJSONString(notice));
        noticeService.insertOrUpdate(notice);
        return CommonResult.success();
    }

    @ApiOperation(value = "批量删除", notes = "批量删除")
    @PostMapping("/deleteBatch")
    @ResponseBody
    public CommonResult deleteBatch(@RequestBody DeleteGoodsDTO dto) {
        log.info("批量删除：{}", JSON.toJSONString(dto));
        noticeService.removeByIds(dto.getIds());
        return CommonResult.success();
    }

    @ApiOperation(value = "修改通知是否展示", notes = "修改通知是否展示")
    @GetMapping("/changeStatus")
    @ResponseBody
    public CommonResult changeStatus(Integer id, Integer isShow) throws BussinessException {
        log.info("修改用户状态：id{}:isShow{}", id, isShow);
        noticeService.changeStatus(id, isShow);
        return CommonResult.success();
    }

}
