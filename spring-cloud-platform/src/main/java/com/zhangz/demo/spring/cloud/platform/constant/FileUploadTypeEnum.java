package com.zhangz.demo.spring.cloud.platform.constant;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.product.entity
 * 
 * @Project：spring-cloud
 * 
 * @name：OrderStatusEnum
 * 
 * @Date：2023/12/22 11:14
 * 
 * @Filename：OrderStatusEnum
 */

public enum FileUploadTypeEnum {
    GOOD_IMG(0, "goodsImg", "商品图片"), ORDERED(1, "bannerImg", "下架");

    private int code;
    private String name;
    private String desc;

    FileUploadTypeEnum(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getDescByCode(int code) {
        for (FileUploadTypeEnum value : FileUploadTypeEnum.values()) {
            if (value.getCode() == code) {
                return value.desc;
            }
        }
        return null;
    }

    public static String getNameByCode(int code) {
        for (FileUploadTypeEnum value : FileUploadTypeEnum.values()) {
            if (value.getCode() == code) {
                return value.name;
            }
        }
        return "null";
    }

}
