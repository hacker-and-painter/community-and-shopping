package com.beautifulsoup.chengfeng.initialize;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 初始化加载ElasticSearch数据
 */
@Component
@Order(value = 2)
public class StartupLoadElasticSearch implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

    }
}
