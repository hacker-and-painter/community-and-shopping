package com.beautifulsoup.chengfeng.tasks;

import com.beautifulsoup.chengfeng.tasks.scheduler.OrderScheduler;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class OrderSchedulerJob {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private OrderScheduler orderScheduler;

    public void scheduleJobs()throws SchedulerException{
        Scheduler scheduler=schedulerFactoryBean.getScheduler();
        orderScheduler.scheduleJob1(scheduler);
    }



}
