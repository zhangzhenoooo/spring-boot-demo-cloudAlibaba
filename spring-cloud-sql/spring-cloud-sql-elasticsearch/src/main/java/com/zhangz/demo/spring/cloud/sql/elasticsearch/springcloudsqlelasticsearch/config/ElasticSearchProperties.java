package com.zhangz.demo.spring.cloud.sql.elasticsearch.springcloudsqlelasticsearch.config;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ElasticSearchProperties {

    public static final String PREFIX = "es";

    private Map<String, ClientInfo> client = new HashMap<>();

    @Data
    public static class ClientInfo {

        private String hosts = "localhost:9200";

        private String username;

        private String password;
    }
}
