package com.zomatouser.qrtzscheduler.jobs;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@NoArgsConstructor
@Slf4j
@Component
public class ColorJob implements Job {

    public static final String FAVIOURTE_COLOR ="BLUE";
    public static final String EXECUTION_COUNT ="count";
    private int count = -1;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        JobKey key = jobDetail.getKey();
        if (jobDetail != null){
            JobDataMap jobDataMap = jobDetail.getJobDataMap();
            String favouriteColor = jobDataMap.getString(FAVIOURTE_COLOR);
            count = jobDataMap.getInt(EXECUTION_COUNT);

            log.info("job key : {}, at date  : {}, favourite color : {} , counter value : {} ", key.getName(), new Date().toString(), favouriteColor, count);
            count++;
            jobDataMap.put(EXECUTION_COUNT, count);
        }
    }
}
