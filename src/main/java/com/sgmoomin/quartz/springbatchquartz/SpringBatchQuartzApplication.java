package com.sgmoomin.quartz.springbatchquartz;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableBatchProcessing		// batch 실행
@EnableScheduling
@SpringBootApplication
public class SpringBatchQuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchQuartzApplication.class, args);
	}

}
