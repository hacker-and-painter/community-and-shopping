package com.beautifulsoup.chengfeng.tasks.scheduler;

import com.beautifulsoup.chengfeng.tasks.job.StockJob;
import org.quartz.*;
import org.springframework.stereotype.Component;

@Component
public class StockScheduler {

    public void scheduleJob1(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail= JobBuilder.newJob(StockJob.class).withIdentity("job1","stock").build();
        CronScheduleBuilder cronScheduleBuilder=CronScheduleBuilder.cronSchedule("0 0/30 * * * ?");//半小时同步一次库存信息
        CronTrigger cronTrigger=TriggerBuilder.newTrigger().withIdentity("trigger1","stock").withSchedule(cronScheduleBuilder)
                .build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }
}
