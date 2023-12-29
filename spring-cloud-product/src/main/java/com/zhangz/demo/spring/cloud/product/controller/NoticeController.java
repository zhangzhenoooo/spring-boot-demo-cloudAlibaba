package com.zhangz.demo.spring.cloud.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhangz.demo.spring.cloud.product.entity.Notice;
import com.zhangz.demo.spring.cloud.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/notice")
@Api(tags = "通知服务")
public class NoticeController {

    // curryburnt/config/value
    @ApiOperation(value = "获取最新通知", notes = "点餐页面，顶部通知消息")

    @GetMapping("/last-one")
    @ResponseBody
    public CommonResult lastOne() {
        String str =
            "{\"content\":\"<p>老店新开，欢迎新老客户光临！</p>\\n<p>海量商品优惠大酬宾！</p>\\n<p>实惠不要错过哦～</p>\",\"dateAdd\":\"2023-12-08 16:09:37\",\"id\":41296,\"isRemind\":false,\"isShow\":true,\"remindUid\":0,\"title\":\"老店新开，欢迎新老客户光临！\",\"userId\":59780}";
        Notice notice = JSONObject.parseObject(str, Notice.class);
        return CommonResult.success(notice);
    }
}
