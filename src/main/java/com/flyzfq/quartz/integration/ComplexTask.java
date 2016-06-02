package com.flyzfq.quartz.integration;

import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Quartz_Demo Created by bug on 16/6/1.
 */
@Component
public class ComplexTask implements InitializingBean {

  @Resource
  private Scheduler scheduler;

  @Resource
  private JobDataMap jobDataMap;

  public void afterPropertiesSet() throws Exception {
    JobDetail complexJob = JobBuilder.newJob(ComplexJob.class)
        .setJobData(jobDataMap)
        .withIdentity(ComplexTask.class.getName())
        .storeDurably()
        .build();

    Trigger trigger = TriggerBuilder.newTrigger()
        .startAt(DateBuilder.futureDate(10, DateBuilder.IntervalUnit.SECOND))
        .forJob(complexJob)
        .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
        .build();

//    scheduler.addJob(complexJob, false);
//    scheduler.scheduleJob(trigger);
  }
}
