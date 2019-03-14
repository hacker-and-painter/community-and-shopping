package com.beautifulsoup.chengfeng.redis;

import com.beautifulsoup.chengfeng.ChengfengApplicationTests;
import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.constant.RedisConstant;
import com.beautifulsoup.chengfeng.pojo.Community;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class RedisTemplateTest extends ChengfengApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Autowired
    private RedisTemplate<String, Serializable> redisSerializableTemplate;

    @Test
    public void setValueTest(){
        stringRedisTemplate.opsForValue().set("name","SpringBoot");
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

    @Test
    public void getReverseRangeByScore() {
        Set<String> set = stringRedisTemplate.opsForZSet().reverseRange(RedisConstant.PROPER_NOTICE_ORDER+1,
                0,3);
        set.stream().forEach(value->{
            Object object = stringRedisTemplate.opsForHash().get(RedisConstant.PROPER_NOTICES+1, value);
            System.out.println(object);
        });
    }


}
