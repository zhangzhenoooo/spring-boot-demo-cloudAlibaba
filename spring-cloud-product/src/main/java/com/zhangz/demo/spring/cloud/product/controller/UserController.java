package com.zhangz.demo.spring.cloud.product.controller;

import com.alibaba.fastjson.JSON;
import com.zhangz.spring.cloud.common.api.CommonResult;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = "用户服务")
public class UserController {

    /**
     * 加入购物车
     * @param code 
     * @param referrer
     * @return
     */
    @PostMapping("/wxapp/authorize")
    @ResponseBody
    public CommonResult wxappAuthorize(@RequestParam("code") String code, @RequestParam("referrer") String referrer) {
        log.info("wxappAuthorize params  code:{},referrer:{}", code, referrer);

        return CommonResult.success();
    }

}
