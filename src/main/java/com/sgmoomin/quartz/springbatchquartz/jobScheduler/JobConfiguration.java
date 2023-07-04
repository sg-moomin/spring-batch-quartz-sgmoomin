
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
public class JobConfiguration extends QuartzJobBean {

    @Autowired
    private Job job1;

    @Autowired
    private JobLauncher jobLauncher;


    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("JobConfiguration Start Job 1!");
        String name = context.getJobDetail().getJobDataMap().get("Sgmoomin Job1").toString();
        System.out.println("JobConfiguration Start Job 1! : " + name);
        
    }
    
    // @SneakyThrows
    // @Override
    // protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        
    //     System.out.print("JobConfiguration Start Job 1!");
    //     JobParameters jobParameter = new JobParametersBuilder()
    //         .addLong("regId1", new Date().getTime())
    //         .toJobParameters();
    //     // job 실행
    //     jobLauncher.run(job1, jobParameter);
     
    // }
}