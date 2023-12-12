package com.zhangz.demo.spring.cloud.product.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.zhangz.demo.spring.cloud.product.dto.GoodSelectItem;
import com.zhangz.demo.spring.cloud.product.dto.UserGoodSelect;
import com.zhangz.demo.spring.cloud.product.entity.*;
import com.zhangz.demo.spring.cloud.product.param.UserGoodSelectParam;
import com.zhangz.demo.spring.cloud.product.vo.ShopDetailVO;
import com.zhangz.spring.cloud.common.api.CommonResult;
import com.zhangz.spring.cloud.common.api.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Slf4j
@RequestMapping("/goods")
@Api(tags = "货物服务")
public class GoodController {

    // curryburnt/shop/goods/category/all
    @ApiOperation(value = "商品类别列表", notes = "商品类别列表，订单左侧分类列表展示")
    // @ApiImplicitParams({
    // @ApiImplicitParam(paramType = "query", name = "reason", value = "失败原因", required = false, dataType = "string"),
    // @ApiImplicitParam(paramType = "query", name = "pageSize", value = "页大小", required = true, dataType = "Integer"),
    // @ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页", required = true, dataType = "Integer")
    // })
    @GetMapping("/category/all")
    @ResponseBody
    public CommonResult goodsCategoryAll() {
        log.info("goodsCategoryAll params");
        String str =
            "[{\"id\": 411772, \"isUse\": true, \"level\": 1, \"name\": \"霸王茶姬\", \"paixu\": 0, \"pid\": 0, \"shopId\": 0, \"userId\": 59780 }, {\"id\": 411773, \"isUse\": true, \"level\": 1, \"name\": \"滬上阿姨\", \"paixu\": 0, \"pid\": 0, \"shopId\": 0, \"userId\": 59780 }, {\"id\": 411774, \"isUse\": true, \"level\": 1, \"name\": \"兰熊鮮奶\", \"paixu\": 0, \"pid\": 0, \"shopId\": 0, \"userId\": 59780 } ]";
        List<GoodCategoryItem> list = JSONArray.parseArray(str, GoodCategoryItem.class);

        return CommonResult.success(list);
    }
    // shop/goods/list

    @PostMapping("/list")
    @ResponseBody
    public CommonResult goodsCategoryAll(int page, String categoryId, int pageSize) {
        log.info("goodsCategoryAll params");
        String str =
            "[{\"afterSale\": \"0,1,2\", \"categoryId\": 411772, \"commission\": 0.00, \"commissionSettleType\": 0, \"commissionType\": 0, \"commissionUserType\": 0, \"dateAdd\": \"2023-12-08 16:09:38\", \"dateUpdate\": \"2023-12-08 15:31:19\", \"fxType\": 2, \"gotScore\": 0, \"gotScoreType\": 0, \"hasAddition\": false, \"hasTourJourney\": false, \"hidden\": 0, \"id\": 1613534, \"kanjia\": false, \"kanjiaPrice\": 0.00, \"limitation\": false, \"logisticsId\": 85797, \"maxCoupons\": 1, \"miaosha\": false, \"minBuyNumber\": 1, \"minPrice\": 10.00, \"minScore\": 0, \"name\": \"茶冻柠檬青\", \"numberFav\": 0, \"numberGoodReputation\": 2, \"numberOrders\": 110, \"numberReputation\": 1, \"numberSells\": 144, \"originalPrice\": 0.00, \"overseas\": false, \"paixu\": 0, \"persion\": 0, \"pic\": \"https://dcdn.it120.cc/2020/08/01/75418f1b-1d4d-48ad-a7b7-85db05f7e699.jfif\", \"pingtuan\": false, \"pingtuanPrice\": 0.00, \"propertyIds\": \",185330,185329,185328,\", \"recommendStatus\": 0, \"recommendStatusStr\": \"普通\", \"seckillBuyNumber\": 0, \"sellEnd\": false, \"sellStart\": true, \"shopId\": 0, \"status\": 0, \"statusStr\": \"上架\", \"storeAlert\": false, \"stores\": 999863, \"stores0Unsale\": false, \"storesExt1\": 0, \"storesExt2\": 0, \"type\": 0, \"unit\": \"份\", \"unusefulNumber\": 0, \"usefulNumber\": 0, \"userId\": 59780, \"vetStatus\": 1, \"views\": 3053, \"weight\": 0.00 }, {\"afterSale\": \"0,1,2\", \"categoryId\": 411772, \"commission\": 0.00, \"commissionSettleType\": 0, \"commissionType\": 0, \"commissionUserType\": 0, \"dateAdd\": \"2023-12-08 16:09:38\", \"dateEndPingtuan\": \"2120-09-01 13:12:37\", \"dateUpdate\": \"2023-12-08 15:31:19\", \"discountPrice\": 0.00, \"fxType\": 2, \"gotScore\": 0, \"gotScoreType\": 0, \"hasAddition\": true, \"hasTourJourney\": false, \"hidden\": 0, \"id\": 1613533, \"kanjia\": false, \"kanjiaPrice\": 0.00, \"limitation\": false, \"logisticsId\": 85797, \"maxCoupons\": 1, \"miaosha\": false, \"minBuyNumber\": 1, \"minPrice\": 12.00, \"minScore\": 0, \"name\": \"茶冻四季拿铁\", \"numberFav\": 0, \"numberGoodReputation\": 2, \"numberOrders\": 39, \"numberReputation\": 0, \"numberSells\": 48, \"originalPrice\": 0.00, \"overseas\": false, \"paixu\": 0, \"persion\": 0, \"pic\": \"https://dcdn.it120.cc/2020/08/01/8c96382c-e4d0-44cf-a2f6-16556b94b741.jfif\", \"pingtuan\": true, \"pingtuanPrice\": 5.00, \"priceShopSell\": 0.00, \"propertyIds\": \",185330,\", \"recommendStatus\": 0, \"recommendStatusStr\": \"普通\", \"seckillBuyNumber\": 0, \"sellEnd\": false, \"sellStart\": true, \"shopId\": 0, \"status\": 0, \"statusStr\": \"上架\", \"storeAlert\": false, \"stores\": 33625, \"stores0Unsale\": false, \"storesExt1\": 0, \"storesExt2\": 0, \"tax\": 0.000, \"type\": 0, \"unit\": \"份\", \"unusefulNumber\": 0, \"usefulNumber\": 0, \"userId\": 59780, \"vetStatus\": 1, \"views\": 6395, \"weight\": 0.00 }]";
        List<GoodDetail> list = JSONArray.parseArray(str, GoodDetail.class);

        return CommonResult.success(list);
    }

    @GetMapping("/detail")
    @ResponseBody
    public CommonResult detail(String id) {
        log.info("detail params id:{}", id);

        ShopDetailVO detailVO = new ShopDetailVO();
        String basicInfoStr =
            "{\"afterSale\": \"0,1,2\", \"categoryId\": 138948, \"commission\": 0.00, \"commissionSettleType\": 0, \"commissionType\": 0, \"commissionUserType\": 0, \"dateAdd\": \"2020-08-01 21:19:24\", \"dateUpdate\": \"2023-12-11 16:36:10\", \"fxType\": 2, \"gotScore\": 0, \"gotScoreType\": 0, \"hasAddition\": false, \"hasTourJourney\": false, \"hidden\": 0, \"id\": 521056, \"kanjia\": false, \"kanjiaPrice\": 0.00, \"limitation\": false, \"logisticsId\": 35458, \"maxCoupons\": 1, \"miaosha\": false, \"minBuyNumber\": 1, \"minPrice\": 12.00, \"minScore\": 0, \"name\": \"茶冻奶绿\", \"numberFav\": 0, \"numberGoodReputation\": 5, \"numberOrders\": 521, \"numberReputation\": 2, \"numberSells\": 875, \"originalPrice\": 0.00, \"overseas\": false, \"paixu\": 0, \"persion\": 0, \"pic\": \"https://dcdn.it120.cc/2020/08/01/c8244a16-6848-40e3-9d4d-60ebe8dc7416.jfif\", \"pingtuan\": false, \"pingtuanPrice\": 0.00, \"propertyIds\": \",41223,41224,41225,\", \"recommendStatus\": 0, \"recommendStatusStr\": \"普通\", \"seckillBuyNumber\": 0, \"sellEnd\": false, \"sellStart\": true, \"shopId\": 0, \"status\": 0, \"statusStr\": \"上架\", \"storeAlert\": false, \"stores\": 11999114, \"stores0Unsale\": false, \"storesExt1\": 0, \"storesExt2\": 0, \"type\": 0, \"unit\": \"份\", \"unusefulNumber\": 0, \"usefulNumber\": 0, \"userId\": 27, \"vetStatus\": 1, \"views\": 14501, \"weight\": 0.00 }";
        detailVO.setBasicInfo(JSONObject.parseObject(basicInfoStr, GoodDetail.class));
        String cateStr = "{\"id\": 138948, \"isUse\": true, \"name\": \"心动夏天\", \"paixu\": 0, \"pid\": 0, \"shopId\": 0, \"userId\": 27}";
        detailVO.setCategory(JSONObject.parseObject(cateStr, GoodCategoryItem.class));
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

        Set<Object> poperites = new HashSet<>();
        Map<String, Object> p1 = new HashMap<>();
        p1.put("childsCurGoods", JSONArray.parseArray(
            "[{\"dateAdd\": \"2020-06-17 10:12:28\", \"id\": 430950, \"name\": \"微甜\", \"paixu\": 0, \"propertyId\": 41223, \"userId\": 27 }, {\"dateAdd\": \"2020-06-17 10:12:36\", \"id\": 430951, \"name\": \"酸甜\", \"paixu\": 0, \"propertyId\": 41223, \"userId\": 27 } ]",
            ChildCurGood.class));
        p1.put("dateAdd", "2020-06-17 10:12:13");
        p1.put("id", 41223);
        p1.put("name", "甜度");
        p1.put("paixu", 0);
        p1.put("userId", 27);
        poperites.add(p1);
        Map<String, Object> p2 = new HashMap<>();
        p2.put("childsCurGoods", JSONArray.parseArray(
            "[{\"dateAdd\": \"2020-06-17 10:12:50\", \"id\": 430952, \"name\": \"常温\", \"paixu\": 0, \"propertyId\": 41224, \"userId\": 27 }, {\"dateAdd\": \"2020-06-17 10:12:56\", \"id\": 430953, \"name\": \"加冰\", \"paixu\": 0, \"propertyId\": 41224, \"userId\": 27 }, {\"dateAdd\": \"2020-06-17 10:13:02\", \"id\": 430954, \"name\": \"少冰\", \"paixu\": 0, \"propertyId\": 41224, \"userId\": 27 } ]",
            ChildCurGood.class));
        p2.put("dateAdd", "2020-06-17 10:12:43");
        p2.put("id", 41224);
        p2.put("name", "口感");
        p2.put("paixu", 0);
        p2.put("userId", 27);
        poperites.add(p2);

        Map<String, Object> p3 = new HashMap<>();
        p3.put("childsCurGoods", JSONArray.parseArray(
            "[{\"dateAdd\": \"2020-06-17 10:13:26\", \"id\": 430955, \"name\": \"中份\", \"paixu\": 0, \"propertyId\": 41225, \"userId\": 27 }, {\"dateAdd\": \"2020-06-17 10:13:34\", \"id\": 430956, \"name\": \"大份\", \"paixu\": 0, \"propertyId\": 41225, \"userId\": 27 } ]",
            ChildCurGood.class));
        p3.put("dateAdd", "2020-06-17 10:13:14");
        p3.put("id", 41225);
        p3.put("name", "分量");
        p3.put("paixu", 0);
        p3.put("userId", 27);
        poperites.add(p3);

        detailVO.setProperties(poperites);
        String skuStr =
            "[{\"fxType\": 2, \"goodsId\": 521056, \"id\": 2523687, \"originalPrice\": 0.00, \"pingtuanPrice\": 0.00, \"price\": 12.00, \"propertyChildIds\": \"41223:430950,41224:430952,41225:430955,\", \"propertyChildNames\": \"甜度:微甜,口感:常温,分量:中份,\", \"score\": 0, \"stores\": 999694, \"userId\": 27 }, {\"fxType\": 2, \"goodsId\": 521056, \"id\": 2523688, \"originalPrice\": 0.00, \"pingtuanPrice\": 0.00, \"price\": 12.00, \"propertyChildIds\": \"41223:430950,41224:430952,41225:430956,\", \"propertyChildNames\": \"甜度:微甜,口感:常温,分量:大份,\", \"score\": 0, \"stores\": 999767, \"userId\": 27 }, {\"fxType\": 2, \"goodsId\": 521056, \"id\": 2523689, \"originalPrice\": 0.00, \"pingtuanPrice\": 0.00, \"price\": 12.00, \"propertyChildIds\": \"41223:430950,41224:430953,41225:430955,\", \"propertyChildNames\": \"甜度:微甜,口感:加冰,分量:中份,\", \"score\": 0, \"stores\": 999972, \"userId\": 27 }, {\"fxType\": 2, \"goodsId\": 521056, \"id\": 2523690, \"originalPrice\": 0.00, \"pingtuanPrice\": 0.00, \"price\": 12.00, \"propertyChildIds\": \"41223:430950,41224:430953,41225:430956,\", \"propertyChildNames\": \"甜度:微甜,口感:加冰,分量:大份,\", \"score\": 0, \"stores\": 999928, \"userId\": 27 }, {\"fxType\": 2, \"goodsId\": 521056, \"id\": 2523691, \"originalPrice\": 0.00, \"pingtuanPrice\": 0.00, \"price\": 12.00, \"propertyChildIds\": \"41223:430950,41224:430954,41225:430955,\", \"propertyChildNames\": \"甜度:微甜,口感:少冰,分量:中份,\", \"score\": 0, \"stores\": 999991, \"userId\": 27 }, {\"fxType\": 2, \"goodsId\": 521056, \"id\": 2523692, \"originalPrice\": 0.00, \"pingtuanPrice\": 0.00, \"price\": 12.00, \"propertyChildIds\": \"41223:430950,41224:430954,41225:430956,\", \"propertyChildNames\": \"甜度:微甜,口感:少冰,分量:大份,\", \"score\": 0, \"stores\": 999963, \"userId\": 27 }, {\"fxType\": 2, \"goodsId\": 521056, \"id\": 2523693, \"originalPrice\": 0.00, \"pingtuanPrice\": 0.00, \"price\": 12.00, \"propertyChildIds\": \"41223:430951,41224:430952,41225:430955,\", \"propertyChildNames\": \"甜度:酸甜,口感:常温,分量:中份,\", \"score\": 0, \"stores\": 999995, \"userId\": 27 }, {\"fxType\": 2, \"goodsId\": 521056, \"id\": 2523694, \"originalPrice\": 0.00, \"pingtuanPrice\": 0.00, \"price\": 12.00, \"propertyChildIds\": \"41223:430951,41224:430952,41225:430956,\", \"propertyChildNames\": \"甜度:酸甜,口感:常温,分量:大份,\", \"score\": 0, \"stores\": 999994, \"userId\": 27 }, {\"fxType\": 2, \"goodsId\": 521056, \"id\": 2523695, \"originalPrice\": 0.00, \"pingtuanPrice\": 0.00, \"price\": 12.00, \"propertyChildIds\": \"41223:430951,41224:430953,41225:430955,\", \"propertyChildNames\": \"甜度:酸甜,口感:加冰,分量:中份,\", \"score\": 0, \"stores\": 999990, \"userId\": 27 }, {\"fxType\": 2, \"goodsId\": 521056, \"id\": 2523696, \"originalPrice\": 0.00, \"pingtuanPrice\": 0.00, \"price\": 12.00, \"propertyChildIds\": \"41223:430951,41224:430953,41225:430956,\", \"propertyChildNames\": \"甜度:酸甜,口感:加冰,分量:大份,\", \"score\": 0, \"stores\": 999891, \"userId\": 27 }, {\"fxType\": 2, \"goodsId\": 521056, \"id\": 2523697, \"originalPrice\": 0.00, \"pingtuanPrice\": 0.00, \"price\": 12.00, \"propertyChildIds\": \"41223:430951,41224:430954,41225:430955,\", \"propertyChildNames\": \"甜度:酸甜,口感:少冰,分量:中份,\", \"score\": 0, \"stores\": 999995, \"userId\": 27 }, {\"fxType\": 2, \"goodsId\": 521056, \"id\": 2523698, \"originalPrice\": 0.00, \"pingtuanPrice\": 0.00, \"price\": 12.00, \"propertyChildIds\": \"41223:430951,41224:430954,41225:430956,\", \"propertyChildNames\": \"甜度:酸甜,口感:少冰,分量:大份,\", \"score\": 0, \"stores\": 999934, \"userId\": 27 } ]";
        List<GoodSelectItem> goodSelectItems = JSONArray.parseArray(skuStr, GoodSelectItem.class);
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
    public CommonResult timeSchedule() {
        log.info("detail params goodsId");
        return CommonResult.failed(ResultCode.NO_DATA, "暂无数据");
    }

    // /shop/goods/price
    @PostMapping("/price")
    @ResponseBody
    public CommonResult price(String propertyChildIds, Integer userId) {
        log.info("detail params price");
        String s =
            " {\"fxType\": 2, \"goodsId\": 521056, \"id\": 2523696, \"originalPrice\": 0.00, \"pingtuanPrice\": 0.00, \"price\": 12.00, \"propertyChildIds\": \"41223:430951,41224:430953,41225:430956,\", \"propertyChildNames\": \"甜度:酸甜,口感:加冰,分量:大份,\", \"score\": 0, \"stores\": 999890, \"userId\": 27 }";
        UserGoodSelect u = JSONObject.parseObject(s, UserGoodSelect.class);
        return CommonResult.success(u);
    }

}
