package com.zhangz.demo.springcloudsms.service;
/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.springcloudsms.service.impl
 * 
 * @Project：spring-cloud
 * 
 * @name：AliSmsService
 * 
 * @Date：2023/12/1 16:28
 * 
 * @Filename：AliSmsService
 */

import com.zhangz.demo.springcloudsms.domain.AliSms;
import com.zhangz.demo.springcloudsms.domain.TenSMSDTO;

public interface TenSmsService {
    void sendSMS(TenSMSDTO tenSms) throws Exception;
}
