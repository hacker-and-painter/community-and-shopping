package com.beautifulsoup.chengfeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.beautifulsoup.chengfeng.dao")
public class ChengfengApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ChengfengApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ChengfengApplication.class, args);
    }

}
