package com.beautifulsoup.chengfeng.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {SwaggerConfig.class,RabbitmqConfig.class})
@EnableCaching
public class ChengfengConfig {



}
