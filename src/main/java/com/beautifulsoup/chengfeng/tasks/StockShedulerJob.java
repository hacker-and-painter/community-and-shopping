package com.beautifulsoup.chengfeng.tasks;

import com.beautifulsoup.chengfeng.tasks.scheduler.StockScheduler;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class StockShedulerJob {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private StockScheduler stockScheduler;

    public void scheduleJobs()throws SchedulerException {
        Scheduler scheduler=schedulerFactoryBean.getScheduler();
        stockScheduler.scheduleJob1(scheduler);
    }

}
