package com.sgmoomin.quartz.springbatchquartz.listener;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobDefaultTrigger implements TriggerListener {

    public static final String EXECUTION_VETO = "EXECUTION_VETO";
    
    @Override
    public String getName() {
         log.info("getName : " + JobDefaultTrigger.class.getName());
         return JobDefaultTrigger.class.getName();
    }

    // trigger 실행 상태
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
         log.info("트리거 실행(triggerFired) : " + trigger.getKey().toString());
    }


    // trigger 중단 확인
    // JOB 실행 횟수가 5회 이상인 경우 작업 중단
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        JobDataMap map = context.getJobDetail().getJobDataMap();
        int executeCount = -1;
        if (map.containsKey(EXECUTION_VETO)) {
            executeCount = map.getInt(EXECUTION_VETO);
        }
        log.info("트리거 중단 확인(triggerFired) : " + trigger.getKey().toString());     
        return executeCount >= 5; 
    }

    // trigger 중단
    @Override
    public void triggerMisfired(Trigger trigger) {
        log.info("트리거 중단(triggerMisfired) : " + trigger.getKey().toString());     
    }


    // trigger 완료
    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context,
            CompletedExecutionInstruction triggerInstructionCode) {
        log.info("트리거 완료(triggerComplete) : " + trigger.getKey().toString());     
    }
 
    
}
