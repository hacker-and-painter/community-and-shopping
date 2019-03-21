package com.beautifulsoup.chengfeng;

import com.beautifulsoup.chengfeng.listener.ChengfengReadyListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.beautifulsoup.chengfeng.dao")
public class ChengfengApplication extends SpringBootServletInitializer{

    //tomcat war支持
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ChengfengApplication.class);
    }

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");//ElasticSearch启动报错
        SpringApplication application=new SpringApplication(ChengfengApplication.class);
        application.addListeners(new ChengfengReadyListener());
        application.run(args);
    }

}
//