package com.zhangz.demo.spring.cloud.product.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.zhangz.demo.spring.cloud.product.dto.GoodPropertyDTO;
import com.zhangz.demo.spring.cloud.product.dto.GoodSelectItem;
import com.zhangz.demo.spring.cloud.product.dto.UserGoodSelect;
import com.zhangz.demo.spring.cloud.product.entity.*;
import com.zhangz.demo.spring.cloud.product.service.GoodCategoryService;
import com.zhangz.demo.spring.cloud.product.service.GoodInfoService;
import com.zhangz.demo.spring.cloud.product.service.GoodPropertyService;
import com.zhangz.demo.spring.cloud.product.vo.ShopDetailVO;
import com.zhangz.spring.cloud.common.api.CommonResult;
import com.zhangz.spring.cloud.common.api.ResultCode;
import com.zhangz.spring.cloud.common.exception.BussinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@Slf4j
@RequestMapping("/goods")
@Api(tags = "货物服务")
public class GoodInfoController {

    @Resource
    private GoodInfoService goodInfoService;

    @Resource
    private GoodCategoryService goodCategoryService;

    @Resource
    private GoodPropertyService goodPropertyService;

    @ApiOperation(value = "商品类别列表", notes = "商品类别列表，订单左侧分类列表展示")
    @GetMapping("/category/all")
    @ResponseBody
    public CommonResult goodsCategoryAll() {
        log.info("goodsCategoryAll params");
        List<GoodCategory> list = goodCategoryService.list();
        return CommonResult.success(list);
    }

    @PostMapping("/list")
    @ResponseBody
    public CommonResult goodsCategoryAll(int page, String categoryId, int pageSize) {
        log.info("goodsCategoryAll params");
        List<GoodInfo> list = goodInfoService.listByCategory(page,pageSize,categoryId);
        return CommonResult.success(list);
    }

    /**
     * 
     * @param id goodId
     * @return
     */
    @GetMapping("/detail")
    @ResponseBody
    public CommonResult detail(String id) {
        log.info("detail params id:{}", id);

        ShopDetailVO detailVO = new ShopDetailVO();
        GoodInfo goodInfo = goodInfoService.getById(id);
        detailVO.setBasicInfo(goodInfo);

        GoodCategory goodCategory = goodCategoryService.getById(138948);
        detailVO.setCategory(goodCategory);
        
        detailVO.setContent("<p>茶冻搭配奶绿，细腻中带着爽滑，清新中透着茶香。</p>");

        Logistics logistics = JSONObject.parseObject(
            "   {\"details\": [{\"addAmount\": 5.00, \"addNumber\": 1.00, \"firstAmount\": 8.00, \"firstNumber\": 49.00, \"type\": 0, \"userId\": 27 } ], \"feeType\": 3, \"feeTypeStr\": \"按金额\", \"isFree\": false }",
            Logistics.class);
        detailVO.setLogistics(logistics);

        List<Object> prcs = new ArrayList<>();
        Map prcsMap = new HashMap();
        prcsMap.put("goodsId", 521056);
        prcsMap.put("id", 2192359);
        prcsMap.put("pic", "https://dcdn.it120.cc/2020/08/01/c8244a16-6848-40e3-9d4d-60ebe8dc7416.jfif");
        prcsMap.put("userId", 27);
        prcs.add(prcsMap);
        detailVO.setPics(prcs);

        List<String> pics2 = new ArrayList<>();
        pics2.add("https://dcdn.it120.cc/2020/08/01/c8244a16-6848-40e3-9d4d-60ebe8dc7416.jfif");
        detailVO.setPics2(pics2);
        String[] split = goodInfo.getPropertyIds().split(",");
        Set<GoodPropertyDTO>  poperites  = goodPropertyService.queryBypropertyIds(new HashSet<>(Arrays.asList(split)));
        detailVO.setProperties(poperites);
        
        detailVO.setSkuList(null);
        detailVO.setSubPics(Lists.newArrayList(0));
        return CommonResult.success(detailVO);
    }

    // goods/goodsAddition?goodsId=521055
    @GetMapping("/goodsAddition")
    @ResponseBody
    public CommonResult goodsAddition(String goodsId) {
        log.info("detail params goodsId:{}", goodsId);
        String list =
            "[{\"categoryId\":0,\"id\":107,\"items\":[{\"id\":278,\"name\":\"1\",\"pid\":107,\"price\":0.00},{\"id\":279,\"name\":\"2\",\"pid\":107,\"price\":0.00}],\"name\":\"测试\",\"required\":true,\"type\":0}]";
        List<GoodAddition> goodAdditions = JSONArray.parseArray(list, GoodAddition.class);
        return CommonResult.success(goodAdditions);
    }

    // times/schedule
    @PostMapping("/times/schedule")
    @ResponseBody
    public CommonResult timeSchedule(String busNo,String returnJson) {
        log.info("detail params goodsId");
        return CommonResult.failed(ResultCode.NO_DATA, "暂无数据");
    }

    // /shop/goods/price
    @PostMapping("/price")
    @ResponseBody
    public CommonResult price(String propertyChildIds, Integer goodsId) throws BussinessException {
        log.info("detail params price");
        
        UserGoodSelect u =   goodInfoService.price(goodsId,propertyChildIds);
        return CommonResult.success(u);
    }

}
