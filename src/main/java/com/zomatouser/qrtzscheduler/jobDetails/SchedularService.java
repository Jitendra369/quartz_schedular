package com.zomatouser.qrtzscheduler.jobDetails;

import com.zomatouser.qrtzscheduler.jobs.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

@Service
public class SchedularService {

    @Autowired
    private HelloWorlsJobSchedular helloWorlsJobSchedular;

    public void scheduleJob() {
        helloWorlsJobSchedular.scheduledJob();
    }
}
