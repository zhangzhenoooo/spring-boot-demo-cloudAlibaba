package com.zhangz.demo.spring.cloud.product.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhangz.demo.spring.cloud.product.dto.TenantConfigDTO;
import com.zhangz.demo.spring.cloud.product.entity.GoodCategoryItem;
import com.zhangz.demo.spring.cloud.product.entity.ShopDetail;
import com.zhangz.demo.spring.cloud.product.dto.ShopInfoVO;
import com.zhangz.spring.cloud.common.api.CommonResult;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/shop")
@Api(tags = "商户服务")
public class ShopController {

    // curryburnt/config/value
    @GetMapping("/configById")
    @ResponseBody
    public CommonResult configById(@RequestParam("tenantId") String tenantId) {
        log.info("configById params  tenantId:{}", tenantId);
        String str =
                "[{\"dateUpdate\":\"2021-01-19 11:11:15\",\"key\":\"mallName\",\"remark\":\"商城名称\",\"value\":\"咖喱糊了\"},{\"key\":\"myBg\",\"remark\":\"我的页面的背景图片\",\"value\":\"https://dcdn.it120.cc/2020/08/01/252f429e-1a7f-4bc6-9e06-92b210c437b4.png\"},{\"dateUpdate\":\"2020-08-01 22:39:06\",\"key\":\"order_hx_uids\",\"remark\":\"核销人员编号\",\"value\":\"1545780\"},{\"key\":\"share_profile\",\"remark\":\"分享文案\",\"value\":\"清凉一夏尽情狂欢\"},{\"key\":\"mapPos\",\"remark\":\"地址上标记图片\",\"value\":\"https://dcdn.it120.cc/2020/08/05/83f02ea6-4449-4e19-92f0-274ac614a134.png\"},{\"dateUpdate\":\"2020-08-10 09:42:10\",\"key\":\"zxdz\",\"remark\":\"在线订座预约项目id\",\"value\":\"377\"},{\"dateUpdate\":\"2020-10-06 13:59:15\",\"key\":\"admin_uids\",\"remark\":\"管理员绑定的用户编号，多个用户用英文逗号隔开\",\"value\":\"1545780\"},{\"dateUpdate\":\"2021-11-23 11:34:59\",\"key\":\"shop_goods_split\",\"remark\":\"是否区分门店商品\",\"value\":\"0\"},{\"key\":\"QQ_MAP_KEY\",\"remark\":\"腾讯地图KEY\",\"value\":\"TJ3BZ-6LVCF-C25JP-JOA3O-EWZFJ-5FBMI\"},{\"key\":\"shop_join_open\",\"remark\":\"是否开启商家入驻入口\",\"value\":\"0\"},{\"key\":\"create_order_select_time\",\"remark\":\"下单的时候需要选择取餐/送达时间\",\"value\":\"1\"},{\"key\":\"customerServiceChatUrl\",\"remark\":\"企业微信客服链接\",\"value\":\"https://work.weixin.qq.com/kfid/kfcb144516034dd7dd9\"},{\"key\":\"customerServiceChatCorpId\",\"remark\":\"企业微信企业ID\",\"value\":\"ww85e610c6f6f533a8\"}]";
        List<TenantConfigDTO> list = JSONArray.parseArray(str, TenantConfigDTO.class);
        return CommonResult.success(list);
    }
    
    // subshop/detail/v2?id=78194
    @ApiOperation(value = "商店信息", notes = "点餐页面，商家信息")
    @GetMapping("/subshop/detail/v2?id=78194")
    @ResponseBody
    public CommonResult subShopDetail(@RequestParam("id") String id) {
        log.info("subShopDetail params  id:{}", id);
        String str =
            "{\"activity\":\"8折\",\"address\":\"北京市东城区建国门内大街8号中粮广场B座307室\",\"bindUid\":2398223,\"characteristic\":\"这是一家非常有特色的奶茶店，奶茶香甜可口，值得品尝。\",\"cityId\":\"110101000000\",\"cyTablePayMod\":1,\"dateAdd\":\"2023-12-08 16:09:37\",\"dateUpdate\":\"2023-11-01 09:39:27\",\"expressType\":\"dada\",\"goodsNeedCheck\":false,\"id\":78194,\"introduce\":\"店铺介绍\",\"latitude\":39.907082,\"linkPhone\":\"13500000000\",\"linkPhoneShow\":true,\"longitude\":116.429314,\"name\":\"东城店（中粮广场B座307室）\",\"number\":\"4217-1478883\",\"numberFav\":0,\"numberGoodReputation\":0,\"numberOrder\":-1,\"numberReputation\":0,\"openScan\":true,\"openWaimai\":true,\"openZiqu\":true,\"openingHours\":\"00:00-23:59\",\"paixu\":0,\"pic\":\"https://dcdn.it120.cc/2020/08/04/3b5c2e23-7c9c-481a-b8de-3b0cdc1c6273.jpg\",\"provinceId\":\"110000000000\",\"recommendStatus\":0,\"serviceAmountMin\":86.00,\"serviceDistance\":5.00,\"status\":1,\"statusStr\":\"正常\",\"taxGst\":0.00,\"taxService\":0.00,\"type\":\"自营\",\"userId\":59780,\"workStatus\":0}";
        ShopDetail shopDetail = JSONObject.parseObject(str, ShopDetail.class);
        ShopInfoVO info = ShopInfoVO.builder().extJson("{}").info(shopDetail).build();
        return CommonResult.success(info);
    }

  
   
}
