package com.zomatouser.qrtzscheduler.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class HelloWorld implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        log.info("Hello World");
        log.info("job details while executing the jobs");
        JobKey key = context.getJobDetail().getKey();
        log.info("job key is {} executing in date {} ", key, new Date());

    }
}
