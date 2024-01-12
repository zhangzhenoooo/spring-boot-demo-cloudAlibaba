package com.zhangz.demo.spring.cloud.product.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.zhangz.demo.spring.cloud.common.api.CommonResult;
import com.zhangz.demo.spring.cloud.product.config.ShopInfoConfig;
import com.zhangz.demo.spring.cloud.product.dto.ShopInfoVO;
import com.zhangz.demo.spring.cloud.product.dto.TenantConfigDTO;
import com.zhangz.demo.spring.cloud.product.dto.shoppingcart.ShoppingCartInfoDTO;
import com.zhangz.demo.spring.cloud.product.entity.Banner;
import com.zhangz.demo.spring.cloud.product.entity.ShopInfo;
import com.zhangz.demo.spring.cloud.product.service.BannerService;
import com.zhangz.demo.spring.cloud.product.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/shop")
@Api(tags = "商户服务")
public class ShopController {

    @Resource
    private ShoppingCartService shoppingCartService;

    @Resource
    private BannerService bannerService;

    @Autowired
    private ShopInfoConfig shopInfoConfig;

    @GetMapping("/configById")
    @ResponseBody
    public CommonResult configById(@RequestParam("tenantId") String tenantId) {
        log.info("configById params  tenantId:{}", tenantId);
        return CommonResult.success(shopInfoConfig.getItems());
    }

 
    @ApiOperation(value = "商店信息", notes = "点餐页面，商家信息")
    @GetMapping("/subshop/detail")
    @ResponseBody
    public CommonResult subShopDetail(@RequestParam("id") String id) {
        log.info("subShopDetail params  id:{}", id);
        ShopInfoVO info = ShopInfoVO.builder().extJson("{}").info(shopInfoConfig.getBase()).build();
        return CommonResult.success(info);
    }

  
    @ApiOperation(value = "横幅", notes = "点餐页面，横幅展示")
    @GetMapping("/banner/list")
    @ResponseBody
    public CommonResult bannerList() {
        List<Banner> bannerList = bannerService.listActive();
        return CommonResult.success(bannerList);
    }

 
    @ApiOperation(value = "购物车信息", notes = "购物车信息")
    @GetMapping("/cart/info")
    @ResponseBody
    public CommonResult cardInfo() {
        ShoppingCartInfoDTO shoppingCartInfoDTO = shoppingCartService.info();
        return CommonResult.success(shoppingCartInfoDTO);
    }

     @ApiOperation(value = "商铺详情", notes = "商铺详情")
    @PostMapping("/subshop/list")
    @ResponseBody
    public CommonResult subShopList(String nameLike,String pageSize,Double curlatitude ,Double curlongitude ) {

        return CommonResult.success(Lists.newArrayList(shopInfoConfig.getBase()));
    }
}
