package com.beautifulsoup.chengfeng.handler;

import com.beautifulsoup.chengfeng.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ChengfengExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity baseExceptionHandler(BaseException base){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(base.getMessage());
    }
}
