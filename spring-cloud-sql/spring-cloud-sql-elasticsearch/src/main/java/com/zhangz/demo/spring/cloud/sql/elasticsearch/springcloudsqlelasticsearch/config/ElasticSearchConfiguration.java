package com.zhangz.demo.spring.cloud.sql.elasticsearch.springcloudsqlelasticsearch.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * es配置类
 */
@Configuration
public class ElasticSearchConfiguration implements BeanFactoryPostProcessor, EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        BindResult<ElasticSearchProperties> bindResult;
        try {
            bindResult = Binder.get(environment).bind(ElasticSearchProperties.PREFIX, ElasticSearchProperties.class);
        } catch (Exception e) {
            throw new RuntimeException("client must be set.");
        }
        if (bindResult == null || !bindResult.isBound()) {
            return;
        }
        ElasticSearchProperties elasticSearchProperties = bindResult.get();
        Map<String, ElasticSearchProperties.ClientInfo> clientInfoMap = elasticSearchProperties.getClient();
        if (CollectionUtils.isEmpty(clientInfoMap)) {
            Assert.notNull(clientInfoMap, "[Assertion Failed] At least one clientInfo must be set.");
        }
        for (Map.Entry<String, ElasticSearchProperties.ClientInfo> client : clientInfoMap.entrySet()) {
            RestHighLevelClient restHighLevelClient = doCreateRestHighLevelClient(client.getValue());
            configurableListableBeanFactory.registerSingleton(client.getKey(), restHighLevelClient);
        }
    }

    public RestHighLevelClient doCreateRestHighLevelClient(ElasticSearchProperties.ClientInfo clientInfo) {
        String hosts = clientInfo.getHosts();
        Assert.hasText(hosts, "[Assertion Failed] At least one host must be set.");

        String[] var2 = hosts.split(",");
        HttpHost[] httpHosts = new HttpHost[var2.length];
        for (int i = 0; i < var2.length; i++) {
            String h = var2[i];
            httpHosts[i] = new HttpHost(h.split(":")[0], Integer.parseInt(h.split(":")[1]), "http");
        }
        RestClientBuilder builder = RestClient.builder(httpHosts);

        String username = clientInfo.getUsername(), password = clientInfo.getPassword();
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
            builder.setHttpClientConfigCallback(e -> e.setDefaultCredentialsProvider(credentialsProvider));
        }

        return new RestHighLevelClient(builder);
    }

}
