package com.sgmoomin.quartz.springbatchquartz.runnerScheduler;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.ListenerManager;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import com.sgmoomin.quartz.springbatchquartz.config.JobSchedulerConfig;
import com.sgmoomin.quartz.springbatchquartz.jobScheduler.JobConfiguration;
import com.sgmoomin.quartz.springbatchquartz.jobScheduler.JobConfigurations;
import com.sgmoomin.quartz.springbatchquartz.listener.JobDefaultListener;
import com.sgmoomin.quartz.springbatchquartz.listener.JobDefaultTrigger;

@Component
public class JobSchedulerCron extends JobSchedulerConfig {

    public static final String EXECUTION_VETO = "EXECUTION_VETO";

    @Autowired
    private Scheduler scheduler;

    @Override
    protected void startSchedulerCron(ApplicationArguments args) {

        JobDataMap jobDataMap1 = new JobDataMap();
        
        // execute
        int executeCount = 0;
        if (jobDataMap1.containsKey(EXECUTION_VETO)) {
            executeCount = jobDataMap1.getInt(EXECUTION_VETO);
        }
        executeCount += 1;
        jobDataMap1.put(EXECUTION_VETO, executeCount);
        jobDataMap1.put("Sgmoomin Job1", "sgmoomin-job 1");
        JobDetail jobDetail1 = buildJobDetail(JobConfiguration.class, "job1", "batch", jobDataMap1);
        Trigger trigger1 = buildJobTrigger("0/10 * * * * ?");
     
        JobDataMap jobDataMap2 = new JobDataMap();
        jobDataMap2.put("Sgmoomin Job2", "sgmoomin-job 2");
        JobDetail jobDetail2 = buildJobDetail(JobConfigurations.class, "job2", "batch", jobDataMap2);
        Trigger trigger2 = buildJobTrigger("0/10 * * * * ?");


        try{
            // scheduler
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            scheduler = schedulerFactory.getScheduler();

            // Listener
            ListenerManager listenrManager = scheduler.getListenerManager(); 
            listenrManager.addJobListener(new JobDefaultListener());
            listenrManager.addTriggerListener(new JobDefaultTrigger());
            

            scheduler.start();

            scheduler.scheduleJob(jobDetail1, trigger1);
            scheduler.scheduleJob(jobDetail2, trigger2);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    
    }


}
