package com.beautifulsoup.chengfeng.handler;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.TokenVo;
import com.beautifulsoup.chengfeng.security.UserInfoService;
import com.beautifulsoup.chengfeng.utils.TokenConferUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    private UserInfoService userInfoService;

    public UserLoginSuccessHandler(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException{

        String token = userInfoService.saveUserLoginInfo((UserDetails)authentication.getPrincipal());
        TokenConferUtil.conferToken(response,token);
    }
}
