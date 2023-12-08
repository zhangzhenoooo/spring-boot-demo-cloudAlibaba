package com.zhangz.demo.spring.cloud.product.entity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * zhangz: 2023-12-08 16:32:40
 *
 */
@Data
public class ShopDetail implements Serializable {

    private String activity;
    private String address;
    private long bindUid;
    private String characteristic;
    private String cityId;
    private int cyTablePayMod;
    private Date dateAdd;
    private Date dateUpdate;
    private String expressType;
    private boolean goodsNeedCheck;
    private long id;
    private String introduce;
    private double latitude;
    private String linkPhone;
    private boolean linkPhoneShow;
    private double longitude;
    private String name;
    private String number;
    private int numberFav;
    private int numberGoodReputation;
    private int numberOrder;
    private int numberReputation;
    private boolean openScan;
    private boolean openWaimai;
    private boolean openZiqu;
    private String openingHours;
    private int paixu;
    private String pic;
    private String provinceId;
    private int recommendStatus;
    private int serviceAmountMin;
    private int serviceDistance;
    private int status;
    private String statusStr;
    private int taxGst;
    private int taxService;
    private String type;
    private int userId;
    private int workStatus;
}