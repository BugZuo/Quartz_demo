package com.flyzfq.quartz.single;

import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Quartz_Demo Created by bug on 16/5/31.
 */
public class SingleApp {
  private static Scheduler scheduler = null;

  static {
    try {
      // 获取默认调度器
      scheduler = StdSchedulerFactory.getDefaultScheduler();
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws Exception {
//    simplest();

    complexJobWithData();

//    jobWithMultiTrigger();

    scheduler.start();
    Thread.sleep(20000);
    scheduler.shutdown();
  }

  public static void simplest() throws Exception {
    System.out.println("Scheduler Name: ----- " + scheduler.getSchedulerName() + "------");


    JobDetail simpleJob = JobBuilder.newJob(MySimpleJob.class)
        .withIdentity("simpleJob")  // JobKey 标识一个任务名,及所属任务组
        .build();

    Trigger simpleTrigger = TriggerBuilder.newTrigger()
        .forJob(simpleJob)
        .withIdentity("simpleTrigger")  // TriggerKey 标识一个Trigger
        .withSchedule(
            SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2)
            .repeatForever())
        .startNow()
//        .startAt(getStartAt())
        .build();

    scheduler.scheduleJob(simpleJob, simpleTrigger);
  }


  public static void complexJobWithData() throws Exception {

    Trigger trigger = TriggerBuilder.newTrigger()
//        .forJob("complexJob")
        .withSchedule(
            CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))  // 每隔2s 执行一次
        .withPriority(1)  // trigger优先级
        .startNow()
        .build();

    JobDetail job = getJobWithData();
//    scheduler.scheduleJob(trigger);
    scheduler.deleteJob(new JobKey("complexJob"));
    scheduler.scheduleJob(job, trigger);

  }

  public static JobDetail getJobWithData() {
    JobDataMap data = new JobDataMap();
    data.put("name", "Bug");
    data.putAsString("age", 18);

    return JobBuilder.newJob(MyComplexJob.class)
        .withIdentity("complexJob")
        .setJobData(data)  // 默认有自己的JobDataMap
        .usingJobData("company", "堆糖")
        .withDescription("This is complexJob")
        .storeDurably() // 设置Job durable
        .build();
  }

  public static void jobWithMultiTrigger() throws Exception {
    JobDetail job = getJobWithData();

    Trigger trigger1 = TriggerBuilder.newTrigger()
        .forJob(job)
        .withSchedule(
            SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2)
                .withRepeatCount(3))
        .startNow()
        .build();

    Trigger trigger2 = TriggerBuilder.newTrigger()
        .forJob(job)
        .withSchedule(
            SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(8)
                .repeatForever())
        .startAt(getStartAt())
        .build();


    scheduler.addJob(job, false);  // boolean 是否覆盖job
    scheduler.scheduleJob(trigger1);
    scheduler.scheduleJob(trigger2);
  }

  /** DSL Domain Specify Language
   * DateBuilder  quartz提供的时间build工具
   */
  public static Date getStartAt() {
    return DateBuilder.futureDate(10, DateBuilder.IntervalUnit.SECOND);
  }
}
