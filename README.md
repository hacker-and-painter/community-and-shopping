# ChengFeng1.5
基于SpringBoot改造Chengfeng

## 技术包括

1. SpringBoot 2.1.3
2. Spring Security+JWT
3. Spring Cache+Ehcache
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
1. 基于Redis构建分布式锁,保证请求的幂等性
2. 基于Guava实现限流,提高系统的并发
3. 基于RabbitMQ实现延迟消息投递,提高系统的吞吐量
4. 数据库层优化
   
   长sql加入ehcache缓存
   用户认证信息加入memcached缓存
   