package com.zomatouser.qrtzscheduler.jobDetails;

import com.zomatouser.qrtzscheduler.jobs.ColorJob;
import com.zomatouser.qrtzscheduler.jobs.HelloWorld;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import java.util.Date;

@Component
@Slf4j
public class HelloWorlsJobSchedular {

    public void scheduledJob() {

        try {
            StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = stdSchedulerFactory.getScheduler();

//            simpleSchedular(scheduler);
            schedularWithJobDataMap(scheduler);


        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private void schedularWithJobDataMap(Scheduler scheduler) throws SchedulerException {
        int TIME_INTERVAL = 3;
        int TRIGGER_REPEAT_COUNT = 3;
        int SCHEDULER_REPEAT_COUNT = 5;
        log.info(new Date().toString());
        Date nextScheduledTime = DateBuilder.nextGivenSecondDate(null, SCHEDULER_REPEAT_COUNT);
        JobDetail colorJobDetails = JobBuilder.newJob(ColorJob.class).withIdentity("colorJob", "group-color").build();

//        hit 4 time in 3 second
        SimpleTrigger colorJobTrigger = TriggerBuilder.newTrigger().withIdentity("color-job-trigger", "group-color-job")
                .startAt(nextScheduledTime)
                .withSchedule(simpleSchedule().withIntervalInSeconds(3).withRepeatCount(3)).build();
        colorJobDetails.getJobDataMap().put(ColorJob.FAVIOURTE_COLOR, "Blue");
        colorJobDetails.getJobDataMap().put(ColorJob.EXECUTION_COUNT, 1);

        scheduler.scheduleJob(colorJobDetails, colorJobTrigger);
        scheduler.start();
        log.info("job is scheduled at the time of : {}, and time interval : {} and for the frequency of  : {} ", new Date(),  TIME_INTERVAL,TRIGGER_REPEAT_COUNT);

    }

    private void simpleSchedular(Scheduler scheduler) throws SchedulerException {
        Date date = DateBuilder.nextGivenSecondDate(null, 10);

        JobDetail jobDetail = JobBuilder.newJob(HelloWorld.class).withIdentity("job1", "group1").build();
//            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
//                    .withIdentity("trigger1", "group1")
//                    .withSchedule(CronScheduleBuilder.cronSchedule("0 */2 * ? * *"))
//                    .build();


//            SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
//                    .withIdentity("trigger2", "group1")
//                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                            .withIntervalInSeconds(5)
//                            .repeatForever()
//                    ).build();

        SimpleTrigger simpleTrigger2 = (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "group2")
                .startAt(date)
                .withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(3))
                .forJob(jobDetail).build();


        scheduler.scheduleJob(jobDetail, simpleTrigger2);
        scheduler.start();
        log.info("hello world job has been scheduled ");
    }
}
