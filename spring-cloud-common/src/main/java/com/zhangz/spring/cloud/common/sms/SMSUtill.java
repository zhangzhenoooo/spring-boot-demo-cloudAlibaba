package com.zhangz.spring.cloud.common.sms;

import com.zhangz.spring.cloud.common.dto.AliSMSDTO;
import com.zhangz.spring.cloud.common.dto.TenSMSDTO;

public class SMSUtill {
    
    public static void sendSMS(AliSMSDTO smsdto) throws Exception {
        AliSMSUtils.sendSms(smsdto);
    }


    public static void sendSMS(TenSMSDTO smsdto) throws Exception {
        TenSMSUtils.sendSMS(smsdto);
    }
}
