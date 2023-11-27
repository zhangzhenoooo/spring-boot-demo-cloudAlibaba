# elasticSearch 集成

## 1. 快速使用

1. maven依赖

```xml

<dependency>
    <groupId>com.zhangz.demo</groupId>
    <artifactId>spring-cloud-sql-elasticsearch</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

2.增加es的配置文件

```yaml
es:
  client:
    # 集群名称
    client-01:
      hosts: 192.168.1.221:9200
      username: username
      password: password
    # 集群名称
    client-02:
      hosts: 192.168.1.221:9200
      username: username
      password: password


```

3.修改启动类

```java
@ComponentScan(basePackages = {
        "com.zhangz.spring.cloud.sql.elasticsearch"
})
```



 