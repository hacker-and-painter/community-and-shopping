package com.beautifulsoup.chengfeng.exception;

import org.springframework.security.web.authentication.www.NonceExpiredException;

public class TokenException extends NonceExpiredException {
    public TokenException(String message) {
        super(message);
    }
}
