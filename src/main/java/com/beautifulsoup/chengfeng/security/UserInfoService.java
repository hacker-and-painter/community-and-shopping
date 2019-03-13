package com.beautifulsoup.chengfeng.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.beautifulsoup.chengfeng.constant.RedisConstant;
import com.beautifulsoup.chengfeng.dao.UserMapper;
import com.beautifulsoup.chengfeng.exception.ParamException;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private PasswordEncoder passwordEncoder;

    public UserInfoService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)){
            throw new ParamException("用户名或密码不正确");
        }
        com.beautifulsoup.chengfeng.pojo.User user = userMapper.selectByNicknameAndPassword(username);
        if(null==user){
            throw new ParamException("用户不存在,登陆失败");
        }

        return  User.builder().username("BeautifulSoup")
                .password(passwordEncoder.encode("password")).authorities("/dada","/dasda").build();
    }

    public UserDetails getUserLoginInfo(String username) {
        String salt = stringRedisTemplate.opsForValue().get(RedisConstant.TOKEN_SALT + username);
        if (StringUtils.isBlank(salt)){
            log.error("salt失效");
            return null;
        }
        UserDetails user = loadUserByUsername(username);
        return User.builder().username(user.getUsername()).password(salt).authorities(user.getAuthorities()).build();
    }

    public String saveUserLoginInfo(UserDetails user) {
        String salt = BCrypt.gensalt();
        stringRedisTemplate.opsForValue().set(RedisConstant.TOKEN_SALT+user.getUsername(),salt,3600, TimeUnit.SECONDS);
        Algorithm algorithm = Algorithm.HMAC256(salt);//首先指定加密算法
        Date date = new Date(System.currentTimeMillis()+1000*3600);  //设置1小时后过期
        String jwtToken = JWT.create()
                .withSubject(user.getUsername())
                .withIssuer("auth0")
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
        return  jwtToken;
    }


    public void deleteUserLoginInfo(String username) {
       //清除缓存的salt
        stringRedisTemplate.delete(RedisConstant.TOKEN_SALT+username);
    }
}
