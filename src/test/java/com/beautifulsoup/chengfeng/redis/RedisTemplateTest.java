package com.beautifulsoup.chengfeng.redis;

import com.beautifulsoup.chengfeng.ChengfengApplicationTests;
import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.constant.RedisConstant;
import com.beautifulsoup.chengfeng.pojo.Community;
import com.beautifulsoup.chengfeng.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Slf4j
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

    @Test
    public void saveObject(){
        User user=new User();
        user.setAvatar("http://wwww.baid.jpg");
        user.setSignUp(new Date());
        user.setUpdateTime(new Date());
        user.setBirthday(new Date());
        user.setIntegral(10);
        user.setNickname("旺夫运");
        redisSerializableTemplate.opsForHash().put("test.user","wangshu",user);
    }

    @Test
    public void increment(){

        User wangshu = (User) redisSerializableTemplate.opsForHash().get("test.user", "wangshu");
        wangshu.setIntegral(11);
        wangshu.setNickname("王小灏");
        redisSerializableTemplate.opsForHash().put("test.user","wangshu",wangshu);
    }


    @Test
    public void getObject(){
        User wangshu = (User) redisSerializableTemplate.opsForHash().get("test.user", "wangshu");
        log.error(wangshu.getNickname()+":"+wangshu.getIntegral());
    }

}
