package com.beautifulsoup.chengfeng.handler;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.enums.ResponseCode;
import com.beautifulsoup.chengfeng.exception.BaseException;
import com.beautifulsoup.chengfeng.exception.ParamException;
import com.beautifulsoup.chengfeng.exception.TokenException;
import com.beautifulsoup.chengfeng.exception.UserAuthenticationException;
import com.google.common.collect.Maps;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import java.util.Map;

@ControllerAdvice(basePackages = {"com.beautifulsoup.chengfeng"})
public class ChengfengExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity baseExceptionHandler(BaseException base){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(base.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity multipartExceptionHandler(MultipartException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity tokenExpiredExceptionHandler(TokenException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity badcredentialsExceptionHandler(BadCredentialsException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler(UserAuthenticationException.class)
    public ResponseEntity authenticationExceptionHandler(UserAuthenticationException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getErrorMsg());
    }



    @ExceptionHandler(ParamException.class)
    @ResponseBody
    @ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult<Map<String,Object>> paramExceptionHandler(ParamException exception){

        Map<String,Object> result= Maps.newHashMap();
        result.put("ExceptionMsg", exception.getMessage());

        return ResponseResult.createByError(ResponseCode.ERROR.getCode()
                ,ResponseCode.ERROR.getDesc(),result);
    }
}
