package com.sgmoomin.quartz.springbatchquartz.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class defaultJob {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job startJob() {
		Job job = jobBuilderFactory.get("startJob")
			.start(moominStep1())
			.next(moominStep2())
			.build();

		return job;
	}

	@Bean
	public Step moominStep1() {
		return stepBuilderFactory.get("moominStep1")
			.tasklet((contribution, chunkContext) -> {
				log.info(">>>>> moominStep1 START JOB");
				return RepeatStatus.FINISHED;
			})
			.build();
	}

	@Bean
	public Step moominStep2() {
		return stepBuilderFactory.get("moominStep2")
			.tasklet((contribution, chunkContext) -> {
				log.info(">>>>> moominStep2 START JOB");
				return RepeatStatus.FINISHED;
			})
			.build();
	}

}
