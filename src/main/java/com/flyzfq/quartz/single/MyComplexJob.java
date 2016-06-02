package com.flyzfq.quartz.single;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Quartz_Demo Created by bug on 16/6/1.
 */
public class MyComplexJob implements Job {
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    System.out.println("Show My Complex Job Here!");

    JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
    System.out.println("name: " + jobDataMap.get("name"));
    System.out.println("age: " + jobDataMap.getIntegerFromString("age"));
    System.out.println("company: " + jobDataMap.get("company"));
  }
}
