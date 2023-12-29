package com.zhangz.demo.spring.cloud.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhangz.demo.spring.cloud.product.service.OrderInfoService;
import com.zhangz.demo.spring.cloud.product.vo.OrderedVO;
import com.zhangz.demo.spring.cloud.common.api.CommonResult;
import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@Slf4j
@RequestMapping("/order")
@Api(tags = "订单服务")
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    // order/list
    /**
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public CommonResult list(String token, Integer status) throws BussinessException {
        log.info("list ，token :{},status:{}", token, status);

        OrderedVO orderedVO = orderInfoService.listByUserIdAndStatus(token, status);
        return CommonResult.success(orderedVO);
    }

    @PostMapping("/pay")
    @ResponseBody
    public CommonResult pay(String token, BigDecimal money, String remark, String nextAction) throws BussinessException {
        log.info("pay 支付入参 ，token :{},money:{}，remark：{}，nextAction：{}", token, money, remark, nextAction);
        JSONObject jsonObject = JSONObject.parseObject(nextAction);
        orderInfoService.pay(token, money, remark, jsonObject.getString("orderId"));
        return CommonResult.success();
    }

    // order/detail
    @GetMapping("/detail")
    @ResponseBody
    public CommonResult detail(String id, String token, String hxNumber, String peisongOrderId) {
        log.info("进入订单详情，入参 id：{}，token：{}，hxNumber ： {}，peisongOrderId ：{}", id, token, hxNumber, peisongOrderId);
        return CommonResult.success();
    }


}
