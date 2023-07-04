Quartz + Spring Batch
========
문서 작성자(Author) : sg-moomin
--------

## 목차 
1. Quartz + Spring Batch Process
2. 코드 구조 
3. 환경 설정
4. Next Plan

## 1. Qaurtz + Spring Batch Process
![20230704_sgmoomin_springbatchqaurtzprocessing](~@source/../src/main/resources/quartz%20springbatch.jpeg)

- JobDetail : 스케줄러에서 수행할 작업 생성
  - 작업 전달 시 JobBuilder를 이용하여 전달
- Trigger : 스케줄러의 작동 주기를 설정
- Listener : 프로세스의 실행, 종료, 중단 등 라이프 사이클에 따른 로그 및 추가 작업을 위해 사용된다(필수 아님)
  - JobListener : 작업 상태 
  - TriggerListener : 실행 상태 
- SchedulerFactoryBean : 스케줄러의 라이프 사이클을 관리한다.
- JobLauncher : 작업(Job)을 실행시키는 역할을 한다. 
  - jobLauncher.run -> JobExceution result return
  - Spring Batch 실행 시 JobLauncher 빈을 생성하고 jobLauncherApplicationRunner를 자동으로 실행한다. 
- QuartzJobBean : 추상클레스를 제공하며 Quartz Job을 생성한다.
  - Qaurtz에서 제공되는 Bean(JobDetailFacotryBean, SimpleTriggerFacotryBean)을 지원 
- QuartzJobBeanFactory : QuartzJobBean 참조하는 클레스는 의존성 주입이 불가(@autowird)함으로 직접 jobFactory를 구현하여 jobLauncher를 주입시키는 작업을 한다. 
  - https://examples.javacodegeeks.com/java-development/enterprise-java/spring/batch/quartz-spring-batch-example/



## 2. 코드 구조(TBD)
- 파일 명칭은 재 변경 필요

### 2.2 코드 구조 > 디렉토리 구조
<pre><code>
springbatchquartz - config - common 
                  - job          
                  - listener
                  - quartz
                  - step
                  - tasklet
</code></pre>


## 3. 환경 설정
- build.gradle
<code><pre>
	'org.springframework.boot:spring-boot-starter-batch'
	'com.h2database:h2'
  'org.springframework.boot:spring-boot-starter-quartz'
</code></pre>
  - In Memory DataBase : H2 
- application.yml
<code><pre>
spring:
    datasource:
            url: jdbc:h2:mem:testdb 
            driverClassName: org.h2.Driver
            username: 
            password:
        batch:
            job:
                names: ${job.name:NONE}
                enabled: false
        quartz:
            # scheduler
            scheduler:
                instanceId: AUTO
            threadPool:
                threadCount: 5
</code></pre>

## 4. Next Plan
- API 구성 
  - url 호출 시 run/restart/reset과 같은 기능들에 대해 검토 필요 
- jobDetail/trigger 분기 처리
- 기타..
