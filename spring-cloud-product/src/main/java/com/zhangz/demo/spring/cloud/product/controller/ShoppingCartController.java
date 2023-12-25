package com.zhangz.demo.spring.cloud.product.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhangz.demo.spring.cloud.product.dto.shoppingcart.SkuItem;
import com.zhangz.demo.spring.cloud.product.service.ShoppingCartService;
import com.zhangz.spring.cloud.common.api.CommonResult;
import com.zhangz.spring.cloud.common.exception.BussinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
        shoppingCartService.add(goodsId, number, skuItems);
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

    // cyTable/add-order
    @PostMapping("/add-order")
    @ResponseBody
    public CommonResult addOrder(String propertyChildIds) throws BussinessException {
        log.info("add-order，propertyChildIds：{}", propertyChildIds);

        shoppingCartService.addOrder(propertyChildIds);
        return CommonResult.success();
    }


    // shopping-cart/modifyNumber
    @ApiOperation(value = "修改数量", notes = "修改数量")
    @PostMapping("/modifyNumber")
    @ResponseBody
    public CommonResult modifyNumber(@RequestParam("token") String token , @RequestParam("key") String key,@RequestParam("number") int number) throws BussinessException {
        log.info("修改数量 ,入参：token：{}，key :{},number:{}",token,key,number);
        
        shoppingCartService.modifyNumber(token,key,number);
        return CommonResult.success();
    }
    // shopping-cart/remove

    @ApiOperation(value = "删除商品", notes = "删除商品")
    @PostMapping("/remove")
    @ResponseBody
    public CommonResult remove(@RequestParam("token") String token , @RequestParam("key") String key) throws BussinessException {
        log.info("删除商品 ,入参：token：{}，key :{}",token,key);

        shoppingCartService.remove(token,key);
        return CommonResult.success();
    }
}
