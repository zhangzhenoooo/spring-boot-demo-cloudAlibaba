package com.zhangz.demo.spring.cloud.sql.elasticsearch.springcloudsqlelasticsearch.config;

import lombok.Getter;

@Getter
@SuppressWarnings("ALL")
public enum AnalyzerEnum {

    NO("不使用分词"),
    /**
     * 标准分词，默认分词器
     */
    STANDARD("standard"),

    /**
     * ik_smart：会做最粗粒度的拆分；已被分出的词语将不会再次被其它词语占有
     */
    IK_SMART("ik_smart"),

    /**
     * ik_max_word ：会将文本做最细粒度的拆分；尽可能多的拆分出词语
     */
    IK_MAX_WORD("ik_max_word");

    private String type;

    AnalyzerEnum(String type) {
        this.type = type;
    }
}
