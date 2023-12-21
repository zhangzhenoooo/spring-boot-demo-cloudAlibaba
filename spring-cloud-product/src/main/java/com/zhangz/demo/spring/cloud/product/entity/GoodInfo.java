/**
 * Copyright 2023 json.cn
 */
package com.zhangz.demo.spring.cloud.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Auto-generated: 2023-12-08 17:17:38
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("good_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GoodInfo implements Serializable {

    @TableId("id")
    private long id;
    @TableField("after_sale")
    private String after_sale;
    @TableField("category_id")
    private long categoryId;
    @TableField("commission")
    private int commission;
    @TableField("commission_settle_type")
    private int commissionSettleType;
    @TableField("commission_type")
    private int commissionType;
    @TableField("commission_user_type")
    private int commissionUserType;
    @TableField("date_add")
    private String dateAdd;
    @TableField("date_update")
    private String dateUpdate;
    @TableField("fx_type")
    private int fxType;
    @TableField("got_score")
    private int gotScore;
    @TableField("got_score_type")
    private int gotScoreType;
    @TableField("has_addition")
    private boolean hasAddition;
    @TableField("has_tour_journey")
    private boolean hasTourJourney;
    @TableField("hidden")
    private int hidden;
    @TableField("kanjia")
    private boolean kanjia;
    /**
     * 砍价
     */
    @TableField("kanjia_price")
    private int kanjiaPrice;
    @TableField("limitation")
    private boolean limitation;
    @TableField("logistics_id")
    private long logisticsId;
    @TableField("max_coupons")
    private int maxCoupons;
    @TableField("miaosha")
    private boolean miaosha;
    @TableField("min_buy_number")
    private int minBuyNumber;
    @TableField("min_price")
    private int minPrice;
    @TableField("min_score")
    private int minScore;
    @TableField("name")
    private String name;
    @TableField("number_fav")
    private int numberFav;
    @TableField("number_good_reputation")
    private int numberGoodReputation;
    @TableField("number_orders")
    private int numberOrders;
    @TableField("number_reputation")
    private int numberReputation;
    @TableField("number_sells")
    private int numberSells;
    /**
     * 原价
     */
    @TableField("original_price")
    private int originalPrice;
    @TableField("overseas")
    private boolean overseas;
    @TableField("paixu")
    private int paixu;
    @TableField("persion")
    private int persion;
    @TableField("pic")
    private String pic;
    @TableField("pingtuan")
    private boolean pingtuan;
    /**
     * 拼团价
     */
    @TableField("pingtuan_price")
    private int pingtuanPrice;
    @TableField("property_ids")
    private String propertyIds;
    @TableField("recommend_status")
    private int recommendStatus;
    @TableField("recommend_status_str")
    private String recommendStatusStr;
    @TableField("seckill_buy_number")
    private int seckillBuyNumber;
    @TableField("sell_end")
    private boolean sellEnd;
    @TableField("sell_start")
    private boolean sellStart;
    @TableField("shop_id")
    private int shopId;
    @TableField("status")
    private int status;
    @TableField("status_str")
    private String statusStr;
    @TableField("store_alert")
    private boolean storeAlert;
    @TableField("stores")
    private long stores;
    @TableField("stores0_unsale")
    private boolean stores0Unsale;
    @TableField("stores_ext1")
    private int storesExt1;
    @TableField("stores_ext2")
    private int storesExt2;
    @TableField("type")
    private int type;
    @TableField("unit")
    private String unit;
    @TableField("unuseful_number")
    private int unusefulNumber;
    @TableField("useful_number")
    private int usefulNumber;
    @TableField("user_id")
    private int userId;
    @TableField("vet_status")
    private int vetStatus;
    @TableField("views")
    private int views;
    @TableField("weight")
    private int weight;

}