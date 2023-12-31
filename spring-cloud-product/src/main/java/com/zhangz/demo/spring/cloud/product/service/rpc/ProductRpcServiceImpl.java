package com.zhangz.demo.spring.cloud.product.service.rpc;

import com.zhangz.demo.spring.cloud.product.service.ProductService;
import com.zhangz.spring.cloud.common.entity.Product;
import com.zhangz.spring.cloud.common.service.ProductRpcService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

@DubboService(interfaceClass = ProductRpcService.class)
@Slf4j
public class ProductRpcServiceImpl implements ProductRpcService {

    @Resource
    private ProductService productService;

    @Override
    public List<Product> getProductByName(String productName) {
        log.info("进入 PRC 调用 getProductByName ");
        
        return productService.getProductByName(productName);
    }

    @Override
    public Product getProductById(String pid) {
        log.info("进入 PRC 调用 getProductById ");

        return productService.getProductById(pid);
    }
}
