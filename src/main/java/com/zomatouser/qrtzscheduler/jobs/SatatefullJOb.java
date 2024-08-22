package com.zomatouser.qrtzscheduler.jobs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
@AllArgsConstructor
public class SatatefullJOb implements Job {

    public static final String NUM_EXECUTIONS  = "NumExecutions";
    public static final String EXECUTION_DELAY = "ExecutionDelay";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("executing satatefull job : {} , at : {} ", context.getJobDetail().getKey().getName() , new Date());

        JobDetail jobDetail = context.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        int executeCount = 0;
        if (jobDataMap.containsKey(NUM_EXECUTIONS)){
            executeCount = jobDataMap.getInt(NUM_EXECUTIONS);
        }
        executeCount++;
        jobDataMap.put(NUM_EXECUTIONS, executeCount);

        long delay = 5000l;
        if (jobDataMap.containsKey(EXECUTION_DELAY)){
            delay = jobDataMap.getLong(EXECUTION_DELAY);
        }

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        System.err.println("  -" + context.getJobDetail().getKey() + " complete (" + executeCount + ").");

    }
}
