package com.zhangz.demo.spring.cloud.product.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhangz.demo.spring.cloud.product.service.OrderInfoService;
import com.zhangz.demo.spring.cloud.product.vo.OrderedVO;
import com.zhangz.spring.cloud.common.api.CommonResult;
import com.zhangz.spring.cloud.common.exception.BussinessException;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
     * 清空购物车
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public CommonResult list(String token, int status) throws BussinessException {
        log.info("list ，token :{},status:{}", token, status);

        OrderedVO orderedVO = orderInfoService.listByUserIdAndStatus(token, status);
        return CommonResult.success(orderedVO);
    }
    @PostMapping("/pay")
    @ResponseBody
    public CommonResult pay(String token, BigDecimal money,String remark,String nextAction) throws BussinessException {
        log.info("pay 支付入参 ，token :{},money:{}，remark：{}，nextAction：{}", token, money,remark,nextAction);
        JSONObject jsonObject = JSONObject.parseObject(nextAction);
        orderInfoService.pay(token, money,remark,jsonObject.getString("orderId"));
        return CommonResult.success();
    }
}
