package com.beautifulsoup.chengfeng.handler;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.constant.RedisConstant;
import com.beautifulsoup.chengfeng.controller.vo.TokenVo;
import com.beautifulsoup.chengfeng.exception.UserAuthenticationException;
import com.beautifulsoup.chengfeng.security.UserInfoService;
import com.beautifulsoup.chengfeng.utils.TokenConferUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
            TokenConferUtil.warningAuthentication(response,"用户已经登录请勿重复登录");
            return;
        }

        String token = userInfoService.saveUserLoginInfo((UserDetails)authentication.getPrincipal());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        TokenConferUtil.conferToken(response,token);
    }
}
