package com.zhangz.demo.spring.cloud.product.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhangz.demo.spring.cloud.product.dto.shoppingcart.SkuItem;
import com.zhangz.demo.spring.cloud.product.service.ShoppingCartService;
import com.zhangz.spring.cloud.common.api.CommonResult;
import com.zhangz.spring.cloud.common.exception.BussinessException;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/shopping-cart")
@Api(tags = "购物车服务")
public class ShoppingCartController {
    
    @Resource
    private ShoppingCartService shoppingCartService;

    /**
     * 加入购物车
     * @param goodsId 
     * @param number
     * @param additionStr
     * @param skuStr
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public CommonResult add(@RequestParam("goodsId") long goodsId, @RequestParam("number") int number, @RequestParam("addition") String additionStr,
        @RequestParam("sku") String skuStr) {
        log.info("configById params  AddCartDTO:{}", JSON.toJSONString(skuStr));
        List<SkuItem> skuItems = JSONArray.parseArray(skuStr, SkuItem.class);
        shoppingCartService.add(goodsId,number, skuItems);
        return CommonResult.success();
    }

    
    /**
     * 清空购物车
     * @return
     */
    @PostMapping("/empty")
    @ResponseBody
    public CommonResult empty() throws BussinessException {
        log.info("empty cart");
        
        shoppingCartService.empty();
        return CommonResult.success();
    }
    

}
