package com.beautifulsoup.chengfeng.security;

import com.beautifulsoup.chengfeng.ChengfengApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class SpringSecurityTest extends ChengfengApplicationTests {

    private PasswordEncoder passwordEncoder;

    @Before
    public void setup(){
        passwordEncoder=new BCryptPasswordEncoder();
    }

    @Test
    public void bcryptEncodePassword(){
        String encode = passwordEncoder.encode("password");
        log.info(encode);
        String encode2 = passwordEncoder.encode("12345678910");
        log.info(encode2);
    }

}
