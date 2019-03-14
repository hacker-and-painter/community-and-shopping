package com.beautifulsoup.chengfeng.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class AuthenticationInfoUtil {



    public static User getAuthenticationInfo(){
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal;
    }
}
