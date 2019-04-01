package com.beautifulsoup.chengfeng.handler;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.constant.RedisConstant;
import com.beautifulsoup.chengfeng.security.UserInfoService;
import com.beautifulsoup.chengfeng.utils.TokenConferUtil;
import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    private UserInfoService userInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    public UserLoginSuccessHandler(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException{

        boolean saltCheck = stringRedisTemplate.hasKey(RedisConstant.TOKEN_SALT + authentication.getName()).booleanValue();
        if (saltCheck){
           clearToken(authentication);
        }

        String token = userInfoService.saveUserLoginInfo((UserDetails)authentication.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        TokenConferUtil.conferToken(response,token);
    }

    protected void clearToken(Authentication authentication) {
        if(authentication == null)
            return;
        SecurityContextHolder.getContext().setAuthentication(null);
        UserDetails user = (UserDetails)authentication.getPrincipal();
        if(user!=null && user.getUsername()!=null)
            userInfoService.deleteUserLoginInfo(user.getUsername());

    }
}
