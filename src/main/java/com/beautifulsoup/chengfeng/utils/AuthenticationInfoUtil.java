package com.beautifulsoup.chengfeng.utils;

import com.beautifulsoup.chengfeng.dao.UserMapper;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.concurrent.TimeoutException;

public class AuthenticationInfoUtil {


    public static User getAuthenticationInfo(){
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal;
    }

    public static com.beautifulsoup.chengfeng.pojo.User getUser(UserMapper userMapper, MemcachedClient memcachedClient) throws InterruptedException, MemcachedException, TimeoutException {
        User authenticationInfo = AuthenticationInfoUtil.getAuthenticationInfo();
        String userJson = memcachedClient.get(authenticationInfo.getUsername());
        com.beautifulsoup.chengfeng.pojo.User user;
        if (StringUtils.isBlank(userJson)){
            user= JsonSerializableUtil.string2Obj(userJson, com.beautifulsoup.chengfeng.pojo.User.class);

        }else{
            user=userMapper.selectByNickname(authenticationInfo.getUsername());
        }
        return user;
    }

}
