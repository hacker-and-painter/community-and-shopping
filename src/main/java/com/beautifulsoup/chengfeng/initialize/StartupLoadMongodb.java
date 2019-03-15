package com.beautifulsoup.chengfeng.initialize;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class StartupLoadMongodb implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

    }
}
