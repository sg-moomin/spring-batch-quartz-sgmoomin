package com.sgmoomin.quartz.springbatchquartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobDefaultListener implements JobListener {
    
    @Override
    public String getName() {
        log.info("Job GetName : " + JobListener.class.getName());
        return JobListener.class.getName();
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        log.info("JOB 시작(jobToBeExecuted) : " + context.getJobDetail().getKey().toString());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        log.info("JOB 중단(jobExecutionVetoed) : " + context.getJobDetail().getKey().toString());
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        log.info("JOB 완료 및 Next JOB(jobWasExecuted) : " + context.getJobDetail().getKey().toString());
    }
    
}
