package com.zhangz.demo.spring.cloud.order.mq;

import com.zhangz.demo.spring.cloud.order.config.AliSMSProperties;
import com.zhangz.demo.spring.cloud.order.config.MQConfig;
import com.zhangz.demo.springcloudsms.domain.AliSms;
import com.zhangz.demo.springcloudsms.service.impl.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**  接收 MQ消息服务类
 * @author 100451
 */
@Slf4j
@Component
public class MqReceiveService {

    @Autowired
    private AliSMSProperties aliSMSProperties;

    @Resource
    private SmsService smsService;

    /**
     * 接收订单下单后 发送用户通知的消息
     * 当队列不存在时自动创建并且自动绑定exchange
     * @param message 消息体
     */
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(MQConfig.ORDER_QUEUE), exchange = @Exchange(MQConfig.ORDER_EXCHANGE), key = MQConfig.ORDER_RUTEKEY)})
    public void orderNotion(String message) {
        log.info("接收到消息体|{}", message);

        // 下单成功后发送短信通知
        AliSms dto = new AliSms();
        dto.setAccessKeyId(aliSMSProperties.getAccessKeyId());
        dto.setAccessKeySecret(aliSMSProperties.getAccessKeySecret());
        dto.setPhoneNumbers("17752412045");
        dto.setSignName("咖喱糊了");
        dto.setTemplateCode("SMS_463920350");
        Map<String, String> m = new HashMap<>();
        // m.put("code", "1234");
        dto.setTemplateParam(m);
        try {
            smsService.sendSMS(dto);
            log.info("已通知用户-通知方式【短信】");
        } catch (Exception e) {
            log.error("发送短信通知异常|{}", e.getMessage(), e);
            // TODO 进入补偿通知
        }
    }
}
