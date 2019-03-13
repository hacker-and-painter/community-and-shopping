package com.beautifulsoup.chengfeng.handler;

import com.beautifulsoup.chengfeng.exception.BaseException;
import com.beautifulsoup.chengfeng.exception.TokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
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
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity badcredentialsExceptionHandler(BadCredentialsException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity authenticationExceptionHandler(AuthenticationException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

}
