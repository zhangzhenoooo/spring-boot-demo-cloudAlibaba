package com.zhangz.demo.spring.cloud.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhangz.demo.spring.cloud.order.config.MQConfig;
import com.zhangz.demo.spring.cloud.order.dao.OrderMapper;
import com.zhangz.demo.spring.cloud.order.service.FeignProductService;
import com.zhangz.demo.spring.cloud.order.service.OrderService;
import com.zhangz.spring.cloud.common.entity.Order;
import com.zhangz.spring.cloud.common.entity.Product;
import com.zhangz.spring.cloud.common.service.ProductRpcService;
import com.zhangz.spring.cloud.common.utils.UUIDUtils;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private FeignProductService feignProductService;

    @Resource
    private OrderMapper orderMapper;

    @DubboReference
    private ProductRpcService productRPCService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    @Cacheable(value = {"order"}, key = "'order' + #orderId", unless = "#result?.size() == 0") // 返回值不为空的时候才缓存
    public List<Order> getOrderById(String orderId) {
        ArrayList<Order> orders = new ArrayList<>();
        String productStr = feignProductService.getProductByName("test feign ");
        Product product = JSON.parseObject(productStr, Product.class);
        Order order = Order.builder().oid(orderId).pname(product.getPname()).number(1).pprice(new BigDecimal("12.5")).build();
        orders.add(order);
        return orders;
    }

    @Override
    @GlobalTransactional(name = "cloud_alibaba_group") // 全局事务控制
    public Order createOrder(String pid, int number) throws Exception {
        String orderId = UUIDUtils.getUUID32();

        // Product productById = productRPCService.getProductById(pid);
        // log.info("dubbo 查询商品服务返回商品信息|{}", productById);

        String productStr = feignProductService.getProductById(pid);
        log.info("查询商品服务返回商品信息|{}", productStr);
        Product product = JSON.parseObject(productStr, Product.class);

        if ("-1".equals(product.getPid())) {
            return Order.builder().oid(orderId).pid("-1").pname("熔断啦").build();
        }

        Order build = Order.builder().oid(orderId).pid(pid).pname(product.getPname()).pprice(new BigDecimal("12.7")).number(number).uid("1111111").username("1111111").build();
        orderMapper.insert(build);
        log.info("生成订单信息|{}", JSON.toJSONString(build));

        // 扣减库存
        feignProductService.reduceInventory(pid, number);

        // 订单生成以后 发送消息通知用户 已下单
        rabbitTemplate.convertAndSend(MQConfig.ORDER_EXCHANGE, MQConfig.ORDER_RUTEKEY, "订单【" + orderId + "】已下单！");
        return build;
    }

}
