package com.zhangz.demo.spring.cloud.sql.elasticsearch.springcloudsqlelasticsearch.config;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface EsField {

     EsFieldEnum type() default  EsFieldEnum.TEXT;

    /**
     * 指定分词器
     */
    AnalyzerEnum analyzer() default AnalyzerEnum.STANDARD;


}

