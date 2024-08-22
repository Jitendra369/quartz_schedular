package com.zomatouser.qrtzscheduler.controller;

import com.zomatouser.qrtzscheduler.jobDetails.SchedularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sch")
public class ScheduleController {

    @Autowired
    private SchedularService schedularService;

    @PostMapping
    public void setSchedularService(){
        this.schedularService.scheduleJob();
    }
}
