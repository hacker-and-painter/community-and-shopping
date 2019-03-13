package com.beautifulsoup.chengfeng.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class UserAuthenticationException extends AuthenticationException {
    private String errorMsg;
    public UserAuthenticationException(String errorMsg) {
        super(errorMsg);
        this.errorMsg=errorMsg;
    }

}
