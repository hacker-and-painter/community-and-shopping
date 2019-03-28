package com.beautifulsoup.chengfeng.tasks.job;

import com.beautifulsoup.chengfeng.annotation.NotUsed;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

@NotUsed
//@Slf4j
//@Setter
public class ChengfengJob extends QuartzJobBean {

    private String name;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
//        log.warn(String.format("Hello %s!",this.name));
    }
}
