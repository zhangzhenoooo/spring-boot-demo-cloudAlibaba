package com.zhangz.demo.spring.cloud.product.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhangz.demo.spring.cloud.product.dto.TenantConfigDTO;
import com.zhangz.demo.spring.cloud.product.dto.shoppingcart.AddCartDTO;
import com.zhangz.demo.spring.cloud.product.dto.shoppingcart.SkuItem;
import com.zhangz.demo.spring.cloud.product.entity.Banner;
import com.zhangz.demo.spring.cloud.product.entity.ShopInfo;
import com.zhangz.demo.spring.cloud.product.dto.ShopInfoVO;
import com.zhangz.spring.cloud.common.api.CommonResult;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/shopping-cart")
@Api(tags = "购物车服务")
public class ShoppingCartController {

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

        return CommonResult.success();
    }

}
