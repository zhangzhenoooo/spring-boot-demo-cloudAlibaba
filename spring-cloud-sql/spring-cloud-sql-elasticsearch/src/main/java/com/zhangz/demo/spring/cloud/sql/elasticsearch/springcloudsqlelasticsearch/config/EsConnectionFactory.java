package com.zhangz.demo.spring.cloud.sql.elasticsearch.springcloudsqlelasticsearch.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zhangz.spring.cloud.common.exception.BussinessException;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangz
 * @date 2022/6/21
 * @description es连接工厂
 */
@Component
@Slf4j
public class EsConnectionFactory implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    private static HashMap<String, RestHighLevelClient> esConns = new HashMap<>();

    private ApplicationContext context;

    private static final String PREX = "";

    public static final String RECEIVER_CLIENT_NAME = "receiverRestHighLevelClient";

    private static String esClients;

    @Value("${esNewClients}")
    public void initesClients(String s) {
        esClients = s;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            String[] names = context.getBeanNamesForType(RestHighLevelClient.class);
            for (int i = 0; i < names.length; i++) {
                String name = names[i];
                // 排除身份证索引客户端
                if (!RECEIVER_CLIENT_NAME.equals(name)) {
                    RestHighLevelClient bean = (RestHighLevelClient)context.getBean(name);
                    esConns.put(name, bean);
                }
            }
        }

    }

    public static List<RestHighLevelClient> getUnitEsCons() throws BussinessException {
        return getEsCons(esClients.split(","));
    }

    public static List<RestHighLevelClient> getEsCons(String[] clientNames) throws BussinessException {
        List<RestHighLevelClient> esClients = new ArrayList<>();
        for (String clientName : clientNames) {
            esClients.add(getEsCon(clientName));
        }
        return esClients;
    }

    /**
     * 获取es连接信息
     *
     * @param clientName
     * @return
     * @throws BussinessException
     */
    public static RestHighLevelClient getEsCon(String clientName) throws BussinessException {
        if (CollectionUtils.isEmpty(esConns)) {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                log.error("获取es连接的线程，休眠出现异常", e);
            }
        }
        String key = clientName;

        // 再次获取
        RestHighLevelClient restHighLevelClient = esConns.get(key);
        if (null == restHighLevelClient) {
            throw new BussinessException("key:" + key + "系统未有对应的es连接信息，请检查");
        } else {
            return restHighLevelClient;
        }
    }

    public static List<RestHighLevelClient> getEsCons() throws BussinessException {
        if (CollectionUtils.isEmpty(esConns)) {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                log.error("获取es连接的线程，休眠出现异常", e);
            }
        }

        ArrayList<RestHighLevelClient> list = new ArrayList<RestHighLevelClient>(4);
        esConns.forEach((k, v) -> {
            list.add(v);
        });

        if (CollectionUtils.isEmpty(list)) {
            throw new BussinessException("系统未有对应的es连接信息，请检查");
        }
        return list;
    }
}
