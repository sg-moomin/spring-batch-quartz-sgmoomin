package com.sgmoomin.quartz.springbatchquartz.jobScheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.SneakyThrows;

@Configuration
public class JobConfigurations extends QuartzJobBean{

    @Autowired
    private Job job2;

    @Autowired
    private JobLauncher jobLauncher;

    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        
        System.out.println("JobConfiguration Start Job 2!");
        String name = context.getJobDetail().getJobDataMap().get("Sgmoomin Job2").toString();
        System.out.println("JobConfiguration Start Job 2! : " + name);

        // JobParameters jobParameter = new JobParametersBuilder()
        //     .addLong("regId2", new Date().getTime())
        //     .toJobParameters();
        // jobLauncher.run(job2, jobParameter);
     
    }    
}
