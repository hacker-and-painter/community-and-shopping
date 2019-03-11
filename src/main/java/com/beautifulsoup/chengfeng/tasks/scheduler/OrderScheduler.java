package com.beautifulsoup.chengfeng.tasks.scheduler;

import com.beautifulsoup.chengfeng.tasks.job.OrderJob;
import org.quartz.*;
import org.springframework.stereotype.Component;

@Component
public class OrderScheduler {


    public void scheduleJob1(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail= JobBuilder.newJob(OrderJob.class).withIdentity("job1","order").build();
        CronScheduleBuilder cronScheduleBuilder=CronScheduleBuilder.cronSchedule("*/3 * * * * ?");
        CronTrigger cronTrigger=TriggerBuilder.newTrigger().withIdentity("trigger1","order").withSchedule(cronScheduleBuilder)
                .build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }



}
