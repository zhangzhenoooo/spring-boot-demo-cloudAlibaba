package com.zhangz.demo.spring.cloud.product;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhangz.demo.spring.cloud.product.entity.GoodCategory;
import com.zhangz.demo.spring.cloud.product.entity.GoodInfo;
import com.zhangz.demo.spring.cloud.product.entity.GoodProperty;
import com.zhangz.demo.spring.cloud.product.service.GoodCategoryService;
import com.zhangz.demo.spring.cloud.product.service.GoodInfoService;
import com.zhangz.demo.spring.cloud.product.service.GoodPropertyService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product
 * 
 * @Project：spring-cloud
 * 
 * @name：GoodServiceTest
 * 
 * @Date：2023/12/12 17:46
 * 
 * @Filename：GoodServiceTest
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootDemoCloudAlibabaProductApplication.class)

public class GoodServiceTest {
    @Resource
    private GoodInfoService goodInfoService;

    @Resource
    private GoodCategoryService goodCategoryService;
    

    @Resource
    private GoodPropertyService goodPropertyService;
    @Test
    public void insertGoodInfo() {
        // String basicInfoStr =
        //     "{\"afterSale\": \"0,1,2\", \"categoryId\": 138948, \"commission\": 0.00, \"commissionSettleType\": 0, \"commissionType\": 0, \"commissionUserType\": 0, \"dateAdd\": \"2020-08-01 21:19:24\", \"dateUpdate\": \"2023-12-11 16:36:10\", \"fxType\": 2, \"gotScore\": 0, \"gotScoreType\": 0, \"hasAddition\": false, \"hasTourJourney\": false, \"hidden\": 0, \"id\": 521056, \"kanjia\": false, \"kanjiaPrice\": 0.00, \"limitation\": false, \"logisticsId\": 35458, \"maxCoupons\": 1, \"miaosha\": false, \"minBuyNumber\": 1, \"minPrice\": 12.00, \"minScore\": 0, \"name\": \"茶冻奶绿\", \"numberFav\": 0, \"numberGoodReputation\": 5, \"numberOrders\": 521, \"numberReputation\": 2, \"numberSells\": 875, \"originalPrice\": 0.00, \"overseas\": false, \"paixu\": 0, \"persion\": 0, \"pic\": \"https://dcdn.it120.cc/2020/08/01/c8244a16-6848-40e3-9d4d-60ebe8dc7416.jfif\", \"pingtuan\": false, \"pingtuanPrice\": 0.00, \"propertyIds\": \",41223,41224,41225,\", \"recommendStatus\": 0, \"recommendStatusStr\": \"普通\", \"seckillBuyNumber\": 0, \"sellEnd\": false, \"sellStart\": true, \"shopId\": 0, \"status\": 0, \"statusStr\": \"上架\", \"storeAlert\": false, \"stores\": 11999114, \"stores0Unsale\": false, \"storesExt1\": 0, \"storesExt2\": 0, \"type\": 0, \"unit\": \"份\", \"unusefulNumber\": 0, \"usefulNumber\": 0, \"userId\": 27, \"vetStatus\": 1, \"views\": 14501, \"weight\": 0.00 }";
        // GoodInfo goodInfo = JSONObject.parseObject(basicInfoStr, GoodInfo.class);
        //
        // goodInfoService.save(goodInfo);
        
        String str =
                "[{\"afterSale\": \"0,1,2\", \"categoryId\": 411772, \"commission\": 0.00, \"commissionSettleType\": 0, \"commissionType\": 0, \"commissionUserType\": 0, \"dateAdd\": \"2023-12-08 16:09:38\", \"dateUpdate\": \"2023-12-08 15:31:19\", \"fxType\": 2, \"gotScore\": 0, \"gotScoreType\": 0, \"hasAddition\": false, \"hasTourJourney\": false, \"hidden\": 0, \"id\": 1613534, \"kanjia\": false, \"kanjiaPrice\": 0.00, \"limitation\": false, \"logisticsId\": 85797, \"maxCoupons\": 1, \"miaosha\": false, \"minBuyNumber\": 1, \"minPrice\": 10.00, \"minScore\": 0, \"name\": \"茶冻柠檬青\", \"numberFav\": 0, \"numberGoodReputation\": 2, \"numberOrders\": 110, \"numberReputation\": 1, \"numberSells\": 144, \"originalPrice\": 0.00, \"overseas\": false, \"paixu\": 0, \"persion\": 0, \"pic\": \"https://dcdn.it120.cc/2020/08/01/75418f1b-1d4d-48ad-a7b7-85db05f7e699.jfif\", \"pingtuan\": false, \"pingtuanPrice\": 0.00, \"propertyIds\": \",185330,185329,185328,\", \"recommendStatus\": 0, \"recommendStatusStr\": \"普通\", \"seckillBuyNumber\": 0, \"sellEnd\": false, \"sellStart\": true, \"shopId\": 0, \"status\": 0, \"statusStr\": \"上架\", \"storeAlert\": false, \"stores\": 999863, \"stores0Unsale\": false, \"storesExt1\": 0, \"storesExt2\": 0, \"type\": 0, \"unit\": \"份\", \"unusefulNumber\": 0, \"usefulNumber\": 0, \"userId\": 59780, \"vetStatus\": 1, \"views\": 3053, \"weight\": 0.00 }, {\"afterSale\": \"0,1,2\", \"categoryId\": 411772, \"commission\": 0.00, \"commissionSettleType\": 0, \"commissionType\": 0, \"commissionUserType\": 0, \"dateAdd\": \"2023-12-08 16:09:38\", \"dateEndPingtuan\": \"2120-09-01 13:12:37\", \"dateUpdate\": \"2023-12-08 15:31:19\", \"discountPrice\": 0.00, \"fxType\": 2, \"gotScore\": 0, \"gotScoreType\": 0, \"hasAddition\": true, \"hasTourJourney\": false, \"hidden\": 0, \"id\": 1613533, \"kanjia\": false, \"kanjiaPrice\": 0.00, \"limitation\": false, \"logisticsId\": 85797, \"maxCoupons\": 1, \"miaosha\": false, \"minBuyNumber\": 1, \"minPrice\": 12.00, \"minScore\": 0, \"name\": \"茶冻四季拿铁\", \"numberFav\": 0, \"numberGoodReputation\": 2, \"numberOrders\": 39, \"numberReputation\": 0, \"numberSells\": 48, \"originalPrice\": 0.00, \"overseas\": false, \"paixu\": 0, \"persion\": 0, \"pic\": \"https://dcdn.it120.cc/2020/08/01/8c96382c-e4d0-44cf-a2f6-16556b94b741.jfif\", \"pingtuan\": true, \"pingtuanPrice\": 5.00, \"priceShopSell\": 0.00, \"propertyIds\": \",185330,\", \"recommendStatus\": 0, \"recommendStatusStr\": \"普通\", \"seckillBuyNumber\": 0, \"sellEnd\": false, \"sellStart\": true, \"shopId\": 0, \"status\": 0, \"statusStr\": \"上架\", \"storeAlert\": false, \"stores\": 33625, \"stores0Unsale\": false, \"storesExt1\": 0, \"storesExt2\": 0, \"tax\": 0.000, \"type\": 0, \"unit\": \"份\", \"unusefulNumber\": 0, \"usefulNumber\": 0, \"userId\": 59780, \"vetStatus\": 1, \"views\": 6395, \"weight\": 0.00 }]";

        List<GoodInfo> goodInfos = JSONArray.parseArray(str, GoodInfo.class);
        goodInfoService.saveBatch(goodInfos);
    }
    @Test
    public void insertGoodCata() {
        String cateStr = "{\"id\": 138948, \"isUse\": true, \"name\": \"心动夏天\", \"paixu\": 0, \"pid\": 0, \"shopId\": 0, \"userId\": 27}";
        GoodCategory goodCategory = JSONObject.parseObject(cateStr, GoodCategory.class);
        goodCategoryService.save(goodCategory);
    }

    @Test
    public void insertGoodProperty() {
        Map<String, Object> p2 = new HashMap<>();
        List<GoodProperty> childCurGoods2 = JSONArray.parseArray(
                "[{\"dateAdd\": \"2020-06-17 10:12:50\", \"id\": 430952, \"name\": \"常温\", \"paixu\": 0, \"propertyId\": 41224, \"userId\": 27 }, {\"dateAdd\": \"2020-06-17 10:12:56\", \"id\": 430953, \"name\": \"加冰\", \"paixu\": 0, \"propertyId\": 41224, \"userId\": 27 }, {\"dateAdd\": \"2020-06-17 10:13:02\", \"id\": 430954, \"name\": \"少冰\", \"paixu\": 0, \"propertyId\": 41224, \"userId\": 27 } ]",
                GoodProperty.class);
        GoodProperty g2 = new GoodProperty();
        g2.setDateAdd("2020-06-17 10:12:43");
        g2.setId(41224);
        g2.setName("口感");
        g2.setPaixu(0);
        g2.setUserId(27);
        childCurGoods2.add(g2);

        List<GoodProperty> childCurGoods3 = JSONArray.parseArray(
                "[{\"dateAdd\": \"2020-06-17 10:13:26\", \"id\": 430955, \"name\": \"中份\", \"paixu\": 0, \"propertyId\": 41225, \"userId\": 27 }, {\"dateAdd\": \"2020-06-17 10:13:34\", \"id\": 430956, \"name\": \"大份\", \"paixu\": 0, \"propertyId\": 41225, \"userId\": 27 } ]",
                GoodProperty.class);

        GoodProperty g3 = new GoodProperty();
        g3.setDateAdd("2020-06-17 10:13:14");
        g3.setId(41225);
        g3.setName("分量");
        g3.setPaixu(0);
        g3.setUserId(27);
        childCurGoods3.add(g3);

        childCurGoods3.addAll(childCurGoods2);
        goodPropertyService.saveBatch(childCurGoods3);
    }
    
    @Test
    public void ininGoodCategory(){
        String str = "[{\"id\": 138949, \"isUse\": true, \"level\": 1, \"name\": \"店长推荐\", \"paixu\": 0, \"pid\": 0, \"shopId\": 0, \"userId\": 27 }, {\"id\": 138950, \"isUse\": true, \"level\": 1, \"name\": \"找口感\", \"paixu\": 0, \"pid\": 0, \"shopId\": 0, \"userId\": 27 }, {\"id\": 138951, \"isUse\": true, \"level\": 1, \"name\": \"找奶茶\", \"paixu\": 0, \"pid\": 0, \"shopId\": 0, \"userId\": 27 }, {\"id\": 138952, \"isUse\": true, \"level\": 1, \"name\": \"找新鲜\", \"paixu\": 0, \"pid\": 0, \"shopId\": 0, \"userId\": 27 }, {\"id\": 138953, \"isUse\": true, \"level\": 1, \"name\": \"找拿铁\", \"paixu\": 0, \"pid\": 0, \"shopId\": 0, \"userId\": 27 }, {\"id\": 138954, \"isUse\": true, \"level\": 1, \"name\": \"其他\", \"paixu\": 0, \"pid\": 0, \"shopId\": 0, \"userId\": 27 } ]";
        List<GoodCategory> goodCategories = JSONArray.parseArray(str, GoodCategory.class);
        goodCategoryService.saveBatch(goodCategories);
    }
    
    
}
