package com.zhangz.demo.spring.cloud.product.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhangz.demo.spring.cloud.product.dto.TenantConfigDTO;
import com.zhangz.demo.spring.cloud.product.entity.GoodCategoryItem;
import com.zhangz.demo.spring.cloud.product.entity.GoodDetail;
import com.zhangz.demo.spring.cloud.product.entity.ShopDetail;
import com.zhangz.demo.spring.cloud.product.dto.ShopInfoVO;
import com.zhangz.spring.cloud.common.api.CommonResult;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/goods")
@Api(tags = "货物服务")
public class GoodController {

   
    // curryburnt/shop/goods/category/all
    @ApiOperation(value = "商品类别列表", notes = "商品类别列表，订单左侧分类列表展示")
    // @ApiImplicitParams({
    //         @ApiImplicitParam(paramType = "query", name = "reason", value = "失败原因", required = false, dataType = "string"),
    //         @ApiImplicitParam(paramType = "query", name = "pageSize", value = "页大小", required = true, dataType = "Integer"),
    //         @ApiImplicitParam(paramType = "query", name = "pageNo", value = "当前页", required = true, dataType = "Integer")
    // })
    @GetMapping("/goods/category/all")
    @ResponseBody
    public CommonResult goodsCategoryAll() {
        log.info("goodsCategoryAll params");
        String str =
                "{\"code\":0,\"data\":[{\"id\":411772,\"isUse\":true,\"level\":1,\"name\":\"心动夏天\",\"paixu\":0,\"pid\":0,\"shopId\":0,\"userId\":59780},{\"id\":411773,\"isUse\":true,\"level\":1,\"name\":\"店长推荐\",\"paixu\":0,\"pid\":0,\"shopId\":0,\"userId\":59780},{\"id\":411774,\"isUse\":true,\"level\":1,\"name\":\"找口感\",\"paixu\":0,\"pid\":0,\"shopId\":0,\"userId\":59780},{\"id\":411775,\"isUse\":true,\"level\":1,\"name\":\"找奶茶\",\"paixu\":0,\"pid\":0,\"shopId\":0,\"userId\":59780},{\"id\":411776,\"isUse\":true,\"level\":1,\"name\":\"找新鲜\",\"paixu\":0,\"pid\":0,\"shopId\":0,\"userId\":59780},{\"id\":411777,\"isUse\":true,\"level\":1,\"name\":\"找拿铁\",\"paixu\":0,\"pid\":0,\"shopId\":0,\"userId\":59780},{\"id\":411778,\"isUse\":true,\"level\":1,\"name\":\"其他\",\"paixu\":0,\"pid\":0,\"shopId\":0,\"userId\":59780}],\"msg\":\"success\"}";
        List<GoodCategoryItem> list = JSONArray.parseArray(str, GoodCategoryItem.class);
 
        return CommonResult.success(list);
    }
    // shop/goods/list

    @PostMapping("/goods/list")
    @ResponseBody
    public CommonResult goodsCategoryAll(int page,String categoryId,int pageSize) {
        log.info("goodsCategoryAll params");
        String str =
                "[{\"afterSale\":\"0,1,2\",\"categoryId\":411772,\"commission\":0.00,\"commissionSettleType\":0,\"commissionType\":0,\"commissionUserType\":0,\"dateAdd\":\"2023-12-08 16:09:38\",\"dateUpdate\":\"2023-12-08 15:31:19\",\"fxType\":2,\"gotScore\":0,\"gotScoreType\":0,\"hasAddition\":false,\"hasTourJourney\":false,\"hidden\":0,\"id\":1613534,\"kanjia\":false,\"kanjiaPrice\":0.00,\"limitation\":false,\"logisticsId\":85797,\"maxCoupons\":1,\"miaosha\":false,\"minBuyNumber\":1,\"minPrice\":10.00,\"minScore\":0,\"name\":\"茶冻柠檬青\",\"numberFav\":0,\"numberGoodReputation\":2,\"numberOrders\":110,\"numberReputation\":1,\"numberSells\":144,\"originalPrice\":0.00,\"overseas\":false,\"paixu\":0,\"persion\":0,\"pic\":\"https://dcdn.it120.cc/2020/08/01/75418f1b-1d4d-48ad-a7b7-85db05f7e699.jfif\",\"pingtuan\":false,\"pingtuanPrice\":0.00,\"propertyIds\":\",185330,185329,185328,\",\"recommendStatus\":0,\"recommendStatusStr\":\"普通\",\"seckillBuyNumber\":0,\"sellEnd\":false,\"sellStart\":true,\"shopId\":0,\"status\":0,\"statusStr\":\"上架\",\"storeAlert\":false,\"stores\":999863,\"stores0Unsale\":false,\"storesExt1\":0,\"storesExt2\":0,\"type\":0,\"unit\":\"份\",\"unusefulNumber\":0,\"usefulNumber\":0,\"userId\":59780,\"vetStatus\":1,\"views\":3053,\"weight\":0.00},{\"afterSale\":\"0,1,2\",\"categoryId\":411772,\"commission\":0.00,\"commissionSettleType\":0,\"commissionType\":0,\"commissionUserType\":0,\"dateAdd\":\"2023-12-08 16:09:38\",\"dateEndPingtuan\":\"2120-09-01 13:12:37\",\"dateUpdate\":\"2023-12-08 15:31:19\",\"discountPrice\":0.00,\"fxType\":2,\"gotScore\":0,\"gotScoreType\":0,\"hasAddition\":true,\"hasTourJourney\":false,\"hidden\":0,\"id\":1613533,\"kanjia\":false,\"kanjiaPrice\":0.00,\"limitation\":false,\"logisticsId\":85797,\"maxCoupons\":1,\"miaosha\":false,\"minBuyNumber\":1,\"minPrice\":12.00,\"minScore\":0,\"name\":\"茶冻四季拿铁\",\"numberFav\":0,\"numberGoodReputation\":2,\"numberOrders\":39,\"numberReputation\":0,\"numberSells\":48,\"originalPrice\":0.00,\"overseas\":false,\"paixu\":0,\"persion\":0,\"pic\":\"https://dcdn.it120.cc/2020/08/01/8c96382c-e4d0-44cf-a2f6-16556b94b741.jfif\",\"pingtuan\":true,\"pingtuanPrice\":5.00,\"priceShopSell\":0.00,\"propertyIds\":\",185330,\",\"recommendStatus\":0,\"recommendStatusStr\":\"普通\",\"seckillBuyNumber\":0,\"sellEnd\":false,\"sellStart\":true,\"shopId\":0,\"status\":0,\"statusStr\":\"上架\",\"storeAlert\":false,\"stores\":33625,\"stores0Unsale\":false,\"storesExt1\":0,\"storesExt2\":0,\"tax\":0.000,\"type\":0,\"unit\":\"份\",\"unusefulNumber\":0,\"usefulNumber\":0,\"userId\":59780,\"vetStatus\":1,\"views\":6395,\"weight\":0.00},{\"afterSale\":\"0,1,2\",\"categoryId\":411772,\"commission\":0.00,\"commissionSettleType\":0,\"commissionType\":0,\"commissionUserType\":0,\"dateAdd\":\"2023-12-08 16:09:38\",\"dateUpdate\":\"2023-12-08 15:31:19\",\"fxType\":2,\"gotScore\":0,\"gotScoreType\":0,\"hasAddition\":false,\"hasTourJourney\":false,\"hidden\":0,\"id\":1613532,\"kanjia\":false,\"kanjiaPrice\":0.00,\"limitation\":false,\"logisticsId\":85797,\"maxCoupons\":1,\"miaosha\":false,\"minBuyNumber\":1,\"minPrice\":12.00,\"minScore\":0,\"name\":\"茶冻奶绿\",\"numberFav\":0,\"numberGoodReputation\":5,\"numberOrders\":510,\"numberReputation\":2,\"numberSells\":864,\"originalPrice\":0.00,\"overseas\":false,\"paixu\":0,\"persion\":0,\"pic\":\"https://dcdn.it120.cc/2020/08/01/c8244a16-6848-40e3-9d4d-60ebe8dc7416.jfif\",\"pingtuan\":false,\"pingtuanPrice\":0.00,\"propertyIds\":\",185330,185329,185328,\",\"recommendStatus\":0,\"recommendStatusStr\":\"普通\",\"seckillBuyNumber\":0,\"sellEnd\":false,\"sellStart\":true,\"shopId\":0,\"status\":0,\"statusStr\":\"上架\",\"storeAlert\":false,\"stores\":999154,\"stores0Unsale\":false,\"storesExt1\":0,\"storesExt2\":0,\"type\":0,\"unit\":\"份\",\"unusefulNumber\":0,\"usefulNumber\":0,\"userId\":59780,\"vetStatus\":1,\"views\":14450,\"weight\":0.00},{\"afterSale\":\"0,1,2\",\"categoryId\":411772,\"commission\":0.00,\"commissionSettleType\":0,\"commissionType\":0,\"commissionUserType\":0,\"dateAdd\":\"2023-12-08 16:09:38\",\"dateUpdate\":\"2023-12-02 23:20:14\",\"discountPrice\":0.00,\"fxType\":2,\"gotScore\":0,\"gotScoreType\":0,\"hasAddition\":false,\"hasTourJourney\":false,\"hidden\":0,\"id\":1613531,\"kanjia\":false,\"kanjiaPrice\":0.00,\"limitation\":false,\"logisticsId\":85797,\"maxCoupons\":1,\"miaosha\":false,\"minBuyNumber\":2,\"minPrice\":11.00,\"minScore\":0,\"name\":\"燕麦乌龙奶\",\"numberFav\":0,\"numberGoodReputation\":14,\"numberOrders\":371,\"numberReputation\":7,\"numberSells\":1000007,\"originalPrice\":0.00,\"overseas\":false,\"paixu\":0,\"persion\":0,\"pic\":\"https://dcdn.it120.cc/2020/08/01/7c2ef87f-f4fb-434e-b3a8-5e2728fc3218.jpg\",\"pingtuan\":false,\"pingtuanPrice\":0.00,\"priceShopSell\":0.00,\"recommendStatus\":0,\"recommendStatusStr\":\"普通\",\"seckillBuyNumber\":0,\"sellEnd\":false,\"sellStart\":true,\"shopId\":0,\"status\":0,\"statusStr\":\"上架\",\"storeAlert\":false,\"stores\":0,\"stores0Unsale\":false,\"storesExt1\":0,\"storesExt2\":0,\"tax\":0.000,\"type\":0,\"unit\":\"份\",\"unusefulNumber\":0,\"usefulNumber\":0,\"userId\":59780,\"vetStatus\":1,\"views\":2228,\"weight\":0.00},{\"afterSale\":\"0,1,2\",\"categoryId\":411772,\"commission\":0.00,\"commissionSettleType\":0,\"commissionType\":0,\"commissionUserType\":0,\"dateAdd\":\"2023-12-08 16:09:38\",\"dateUpdate\":\"2023-12-08 00:14:36\",\"fxType\":2,\"gotScore\":0,\"gotScoreType\":0,\"hasAddition\":false,\"hasTourJourney\":false,\"hidden\":0,\"id\":1613530,\"kanjia\":false,\"kanjiaPrice\":0.00,\"limitation\":false,\"logisticsId\":85797,\"maxCoupons\":1,\"miaosha\":false,\"minBuyNumber\":1,\"minPrice\":12.00,\"minScore\":0,\"name\":\"翡翠柠檬\",\"numberFav\":0,\"numberGoodReputation\":7,\"numberOrders\":340,\"numberReputation\":3,\"numberSells\":1000090,\"originalPrice\":0.00,\"overseas\":false,\"paixu\":0,\"persion\":0,\"pic\":\"https://dcdn.it120.cc/2020/08/01/da9ba471-bb12-461d-a720-bbd438f39e7e.jfif\",\"pingtuan\":false,\"pingtuanPrice\":0.00,\"propertyIds\":\",185330,185329,185328,\",\"recommendStatus\":0,\"recommendStatusStr\":\"普通\",\"seckillBuyNumber\":0,\"sellEnd\":false,\"sellStart\":true,\"shopId\":0,\"status\":0,\"statusStr\":\"上架\",\"storeAlert\":false,\"stores\":0,\"stores0Unsale\":false,\"storesExt1\":0,\"storesExt2\":0,\"type\":0,\"unit\":\"份\",\"unusefulNumber\":0,\"usefulNumber\":0,\"userId\":59780,\"vetStatus\":1,\"views\":34969,\"weight\":0.00}]";
        List<GoodDetail> list = JSONArray.parseArray(str, GoodDetail.class);

        return CommonResult.success(list);
    }
}
