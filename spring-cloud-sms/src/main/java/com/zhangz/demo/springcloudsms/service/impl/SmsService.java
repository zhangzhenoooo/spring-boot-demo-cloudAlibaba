package com.zhangz.demo.springcloudsms.service.impl;

import com.zhangz.demo.springcloudsms.domain.AliSms;
import com.zhangz.demo.springcloudsms.domain.TenSMSDTO;
import com.zhangz.demo.springcloudsms.service.AliSmsService;
import com.zhangz.demo.springcloudsms.service.TenSmsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SmsService {

    @Resource
    private AliSmsService aliSmsService;

    @Resource
    private TenSmsService tenSmsService;

    public void sendSMS(AliSms aliSms) throws Exception {
        aliSmsService.sendSms(aliSms);
    }

    public void sendSMS(TenSMSDTO smsdto) throws Exception {
        tenSmsService.sendSMS(smsdto);
    }
}
