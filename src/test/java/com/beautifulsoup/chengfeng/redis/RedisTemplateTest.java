package com.beautifulsoup.chengfeng.redis;

import com.beautifulsoup.chengfeng.ChengfengApplicationTests;
import com.beautifulsoup.chengfeng.pojo.Community;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.Serializable;
import java.util.Date;

public class RedisTemplateTest extends ChengfengApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Autowired
    private RedisTemplate<String, Serializable> redisSerializableTemplate;

    @Test
    public void setValueTest(){
        redisTemplate.opsForValue().set("name","SpringBoot");
    }

    @Test
    public void redisTemplateTest(){
        Community community=new Community();
        community.setAddress("山东省济南市历下区");
        community.setCreateTime(new Date());
        community.setUpdateTime(new Date());
        community.setId(5);
        community.setAdmin("赵钱孙");
        redisSerializableTemplate.opsForValue().set("community",community);
    }

}
