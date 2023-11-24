package com.zhangz.demo.spring.cloud.order.controller;

import com.alibaba.fastjson.JSON;
import com.zhangz.spring.cloud.common.entity.Order;
import com.zhangz.spring.cloud.common.vo.Result;
import com.zhangz.demo.spring.cloud.order.service.OrderService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@Api(tags = "订单服务")
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/getOrderById")
    public String getOrderById(String orderId) {
        log.info("getOrderById params  orderId:{}", orderId);
        List<Order> productList = orderService.getOrderById(orderId);
        return JSON.toJSONString(productList);
    }

    @PostMapping("/createOrder")
    public Result createOrder(@RequestParam("pid") String pid,@RequestParam("number") int number) throws Exception {
        log.info("getOrderById params pid:{},number:{}", pid,number);
        Order order = orderService.createOrder(pid,number);
        return Result.success(JSON.toJSONString(order));
    }
}
