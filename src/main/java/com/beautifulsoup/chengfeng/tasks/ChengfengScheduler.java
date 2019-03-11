package com.beautifulsoup.chengfeng.tasks;

import com.beautifulsoup.chengfeng.tasks.job.ChengfengJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChengfengScheduler {

    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(ChengfengJob.class).withIdentity("chengfengJob").usingJobData("name","BeautifulSoup")
                .storeDurably().build();
    }

    @Bean
    public Trigger trigger(){
//        SimpleScheduleBuilder scheduleBuilder=SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(2).repeatForever();
        CronScheduleBuilder cronScheduleBuilder=CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
//        CronTrigger cronTrigger=TriggerBuilder.newTrigger()
        return TriggerBuilder.newTrigger().forJob(jobDetail())
                .withIdentity("chengfengTrigger").withSchedule(cronScheduleBuilder).build();
    }



}
