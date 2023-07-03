package com.sgmoomin.quartz.springbatchquartz.config;

import static org.quartz.JobBuilder.newJob;

import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public abstract class JobSchedulerConfig implements ApplicationRunner{

    @Override
    public void run(ApplicationArguments args) throws Exception {
        startSchedulerCron(args);
    }

    public Trigger buildJobTrigger(String scheduleExp) {
        return TriggerBuilder.newTrigger()
                // .withIdentity("sgmoomin_trigger", "sgmoomin_trigger_group")
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
    }

    public JobDetail buildJobDetail(Class job, String name, String group, Map params) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(params);

        return newJob(job).withIdentity(name, group)
                .usingJobData(jobDataMap)
                .build();
    }

    protected abstract void startSchedulerCron(ApplicationArguments args);


    
}