package com.beautifulsoup.chengfeng.handler;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.TokenVo;
import com.beautifulsoup.chengfeng.security.UserInfoService;
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
        //生成token，并把token加密相关信息缓存，具体请看实现类
        String token = userInfoService.saveUserLoginInfo((UserDetails)authentication.getPrincipal());

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        TokenVo tokenVo=TokenVo.builder().toke(token).build();
        ResponseResult responseResult=ResponseResult.createBySuccess(tokenVo);
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(response.getWriter(),responseResult);
        response.getWriter().flush();
    }
}
