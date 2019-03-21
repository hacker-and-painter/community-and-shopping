package com.beautifulsoup.chengfeng.utils;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.TokenVo;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenConferUtil {
    public static void conferToken(HttpServletResponse response,String token) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        TokenVo tokenVo=TokenVo.builder().toke(token).build();
        ResponseResult responseResult=ResponseResult.createBySuccess("用户登录成功",tokenVo);
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(response.getWriter(),responseResult);
        response.getWriter().flush();
//        response.getWriter().close();
    }

    public static void warningAuthentication(HttpServletResponse response,String warningMsg) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        ResponseResult responseResult=ResponseResult.createByErrorMessage(warningMsg);
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(response.getWriter(),responseResult);
        response.getWriter().flush();
//        response.getWriter().close();
    }
}
