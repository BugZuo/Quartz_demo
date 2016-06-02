package com.flyzfq.config;

import org.quartz.JobDataMap;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Quartz_Demo Created by bug on 16/6/1.
 */
@Configuration
public class TaskConfig {
  @Bean
  public Scheduler scheduler() throws Exception {
    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
    scheduler.start();
    return scheduler;
  }

  @Bean
  public JobDataMap jobDataMap() {
    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.put("name", "左左");
    return jobDataMap;
  }
}
