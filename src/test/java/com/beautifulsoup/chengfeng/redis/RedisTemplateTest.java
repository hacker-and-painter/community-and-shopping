package com.beautifulsoup.chengfeng.redis;

import com.beautifulsoup.chengfeng.ChengfengApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisTemplateTest extends ChengfengApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void setValueTest(){
        redisTemplate.opsForValue().set("name","SpringBoot");
    }


}
