package com.zhangz.demo.spring.cloud.product.controller;


import com.zhangz.demo.spring.cloud.common.api.CommonResult;
import com.zhangz.demo.spring.cloud.product.entity.Notice;
import com.zhangz.demo.spring.cloud.product.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/notice")
@Api(tags = "通知服务")
public class NoticeController {

    @Resource
    private NoticeService noticeService;

    @ApiOperation(value = "获取最新通知", notes = "点餐页面，顶部通知消息")
    @GetMapping("/last-one")
    @ResponseBody
    public CommonResult lastOne() {
        Notice notice = noticeService.lastOne();
        return CommonResult.success(notice);
    }

    @ApiOperation(value = "获取最新通知", notes = "点餐页面，顶部通知消息")
    @GetMapping("/detail")
    @ResponseBody
    public CommonResult detail(String id) {
        Notice notice = noticeService.getById(id);
        return CommonResult.success(notice);
    }

}
