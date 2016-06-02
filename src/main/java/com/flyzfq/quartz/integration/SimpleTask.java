package com.flyzfq.quartz.integration;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Quartz_Demo Created by bug on 16/6/1.
 */
@Component
public class SimpleTask implements InitializingBean {
  @Resource
  private Scheduler scheduler;

  public void afterPropertiesSet() throws Exception {
    init();
  }

  public void init() throws Exception {
    JobDetail job = JobBuilder.newJob(SimpleJob.class)
        .withIdentity("simpleJob")
        .build();

    Trigger trigger = TriggerBuilder.newTrigger()
        .withIdentity("simpleTrigger")
        .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3)
            .withRepeatCount(3))
        .startNow()
        .build();

//    scheduler.scheduleJob(job, trigger);
  }
}

