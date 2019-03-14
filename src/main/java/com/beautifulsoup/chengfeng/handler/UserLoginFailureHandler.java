package com.beautifulsoup.chengfeng.handler;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        ResponseResult responseResult=ResponseResult.createByError(HttpStatus.UNAUTHORIZED.value(),"用户认证失败,"+e.getMessage());

        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(response.getWriter(),responseResult);
        response.getWriter().flush();
    }
}
