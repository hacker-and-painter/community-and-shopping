package com.beautifulsoup.chengfeng.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ChengfengStartupRunner implements CommandLineRunner {

    @Autowired
    private OrderSchedulerJob schedulerJob;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>定时任务开始执⾏<<<<<<<<<<<<<");
//        schedulerJob.scheduleJobs();
    }
}
