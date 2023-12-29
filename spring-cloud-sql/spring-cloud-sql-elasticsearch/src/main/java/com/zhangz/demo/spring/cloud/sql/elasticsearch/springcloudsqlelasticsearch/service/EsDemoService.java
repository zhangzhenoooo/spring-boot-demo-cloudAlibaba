package com.zhangz.demo.spring.cloud.sql.elasticsearch.springcloudsqlelasticsearch.service;

import com.zhangz.demo.spring.cloud.common.exception.BussinessException;
import com.zhangz.demo.spring.cloud.common.exception.SystemException;

import java.io.IOException;

public interface EsDemoService {

    void createIndex(String indexName, Integer shardNum) throws Exception;

    void createMapping(String indexName) throws Exception;

    void testSearch() throws IOException, BussinessException;

    void testAggs() throws BussinessException, SystemException;
}
