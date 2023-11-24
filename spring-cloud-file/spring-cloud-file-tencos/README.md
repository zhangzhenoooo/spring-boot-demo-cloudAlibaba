## 集成方式

1. 增加maven依赖

```xml

<dependency>
    <groupId>com.zhangz.demo</groupId>
    <artifactId>spring-cloud-file-tencos</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

2. 增加配置文件

```yaml
file:
  tencent:
    cos:
      region: ''
      secretId: ''
      secretKey: ''

```

3. 修改启动类 扫描包路径

> 将对应的包交给spring管理

```java
@ComponentScan(basePackages = {
        "com.zhangz.spring.cloud.file.tencos"
})
```