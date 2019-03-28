package com.beautifulsoup.chengfeng.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChengfengStartupRunner implements CommandLineRunner {

    @Autowired
    private OrderSchedulerJob schedulerJob;

    @Autowired
    private StockShedulerJob stockShedulerJob;

    @Override
    public void run(String... args) throws Exception {
        log.info(">>>>>>>>>>>>>>>定时任务开始执⾏<<<<<<<<<<<<<");
//        schedulerJob.scheduleJobs();
        stockShedulerJob.scheduleJobs();
    }
}
