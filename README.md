# 项目介绍

author ：是大魔王本王 @1804919062qq.com

> 菜鸟自己总结的，有很多不足之处，望指正  
> 很多地方都借鉴了大佬们，没有标注的可以联系我，谢谢！

# 1. 项目介绍

有关spring cloud 相关技术的整合，以及常用技术的整合。实现简单的操作等。 用于归纳自己遇到的技术以及对新技术的实践。

## 1.1 项目模块

模块名称 | 部署 | 访问地址
------ | ------| ----
gateway | idea | http://localhost:7777
order | idea |http://localhost:9999/cloud-alibaba-order/doc.html
product | idea |http://localhost:8888/cloud-alibaba-product/doc.html

## 1.2 技术罗列

1. nacos 注册中心 + 配置中心
2. fegin 远程调用
3. Sentinel 服务容错
4. gateway 网关
5. Sleuth +Zinkin 链路追踪
6. rabbitMQ 消息驱动
7. SMS 短信服务
8. seata 分布式事务
9. dubbo Rpc通信
10. cos 文件存储

* TODO

1. minio ...
2. redis ...
3. elasticSearch ...
4. task ...
5. Doris ...
6.

# 2. 中间件清单

名称    |  版本号  | 服务器地址 | 部署路径 | 客户端地址 | 其他
------ |-------- | -------- | ------- | -------- | -----
nacos | 2.1.0 | 192.168.1.225 | /opt/bssoft/nacos| http://192.168.1.225:8848/naco |
sentinel |  | local | 本机idea启动  | http://localhost:8080/#/dashboard/degrade/sentinel-dashboard | https://github.com/zhangzhenoooo/sentinel-dashboard-zk.git
zipkin | 2.23.9  | local | D:\Users\100451\zipkin | http://localhost:9411 | 启动方式 ： java -DSTORAGE_TYPE=elasticsearch -DES_HOSTS=http://192.168.2.14:9200 -jar  zipkin-server-2.23.9-exec.jar > publish.log &
mysql | 5.7 |192.168.1.188 |  |
zookeeper|3.4.6| 192.168.1.250 |  |
seata | 1.6.1 | local | E:\强者之路\soft\seata |   |

* 附：  
  中间件下载链接：

> 1. [seata 1.0 - 1.7](https://pan.baidu.com/s/14xyvWMiwNglzNzJbJ0AXsQ?pwd=1234)
>2. [nacos](https://pan.baidu.com/s/1qz6aRlUnDqK1eph76zNUvQ?pwd=1234)
>3. [es 7.3.2 ](https://pan.baidu.com/s/1lwYlde-9z434C_Yq-cSy2g?pwd=1234)
>4. [kibana 7.3.1](https://pan.baidu.com/s/1ACkapsB5pl8oarA-4acGrw?pwd=1234)
>5. [rabbitMQ 3.7.5](https://pan.baidu.com/s/141u3bQOPOmj7Kxpixcgnrw?pwd=1234)
>6. [zipkin 2.23.9](https://pan.baidu.com/s/1PkWi_L2ofJUlF0oDEnlEkg?pwd=1234)

# 3. 启动项目

## 3.1 启动中间件

* 请阅读README.md相关模块进行启动

## 3.2 启动服务

* 下载项目到idea，在idea启动即可，接口调用通过swagger调用。  
  简单实现并没有复杂业务场景
