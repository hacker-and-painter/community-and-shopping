# ChengFeng1.5
基于SpringBoot改造Chengfeng

## Link
[https://juejin.im/post/5ca1b08fe51d455201119177](https://juejin.im/post/5ca1b08fe51d455201119177)

## Web端
[基于Vue端的Web前端系统](https://github.com/hansonfang/community_and_shopping)

## Android端

### 社区APP
[社区版Android APP](https://github.com/Hanscang/AndroidCommunity)

### 商城APP
[商城版A](https://juejin.im/post/5ca1b08fe51d455201119177)

## 技术包括

1. SpringBoot 2.1.3
2. Spring Security+JWT
3. Spring Boot Cache
4. Spring Boot Mail
5. MyBatis+PageHelper
6. MySQL+Druid
7. Spring Data Mongodb
10. Spring Data ElasticSearch
8. Redis+Codis+Redisson
9. RabbitMQ
11. Kafka
12. Memcached
13. FastDFS
14. Quartz
15. Ehcache
15. Thymeleaf 
16. Hibernate Validator
17. Guava+apache commons+jackson+joda time
18. Swagger
19. Tomcat+Nginx
20. CentOS 7 

## 要点
1. 基于Redisson构建分布式锁
3. 基于RabbitMQ实现延迟消息投递,提高系统的吞吐量
4. 数据库层优化
   
   1. 长sql加入ehcache缓存
   
   2. 用户认证信息加入memcached缓存
   
   3. 采用spring cache缓存热点数据
   
   4. 热点数据预加入Redis(如商品的库存信息),采用Redis预减操作,然后采用定时任务同步缓存及数据库
5. Kafka收集订单信息,ElasticSearch进行数据分析,产生热卖商品并推荐

    
   
