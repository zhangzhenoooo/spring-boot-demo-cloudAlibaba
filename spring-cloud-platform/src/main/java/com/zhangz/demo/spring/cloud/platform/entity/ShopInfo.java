package com.zhangz.demo.spring.cloud.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * zhangz: 2023-12-08 16:32:40
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("shop_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShopInfo implements Serializable {

    @TableId("id")
    private long id;

    @TableField("activity")
    private String activity;

    @TableField("address")
    private String address;

    @TableField("bind_uid")
    private long bindUid;

    @TableField("characteristic")
    private String characteristic;

    @TableField("city_id")
    private String cityId;

    @TableField("cy_table_pay_mod")
    private int cyTablePayMod;

    @TableField("date_add")
    private Date dateAdd;

    @TableField("date_update")
    private Date dateUpdate;

    @TableField("express_type")
    private String expressType;

    @TableField("goods_need_check")
    private boolean goodsNeedCheck;

    @TableField("introduce")
    private String introduce;

    @TableField("latitude")
    private double latitude;

    @TableField("link_phone")
    private String linkPhone;

    @TableField("link_phone_show")
    private boolean linkPhoneShow;

    @TableField("longitude")
    private double longitude;

    @TableField("name")
    private String name;

    @TableField("number")
    private String number;

    @TableField("number_fav")
    private int numberFav;

    @TableField("number_good_reputation")
    private int numberGoodReputation;

    @TableField("number_order")
    private int numberOrder;

    @TableField("number_reputation")
    private int numberReputation;

    @TableField("open_scan")
    private boolean openScan;

    @TableField("open_waimai")
    private boolean openWaimai;

    @TableField("open_ziqu")
    private boolean openZiqu;

    @TableField("opening_hours")
    private String openingHours;

    @TableField("paixu")
    private int paixu;

    @TableField("pic")
    private String pic;

    @TableField("province_id")
    private String provinceId;

    @TableField("recommend_status")
    private int recommendStatus;

    @TableField("service_amount_min")
    private int serviceAmountMin;

    @TableField("service_distance")
    private int serviceDistance;

    @TableField("status")
    private int status;

    @TableField("status_str")
    private String statusStr;

    @TableField("tax_gst")
    private int taxGst;

    @TableField("tax_service")
    private int taxService;

    @TableField("type")
    private String type;

    @TableField("user_id")
    private int userId;

    @TableField("work_status")
    private int workStatus;
}