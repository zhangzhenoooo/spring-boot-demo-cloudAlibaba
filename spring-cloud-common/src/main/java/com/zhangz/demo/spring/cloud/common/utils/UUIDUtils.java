package com.zhangz.demo.spring.cloud.common.utils;


import java.util.UUID;

public class UUIDUtils {
    
    public static final String getUUID32(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
 
}
