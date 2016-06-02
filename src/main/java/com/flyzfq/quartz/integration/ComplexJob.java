package com.flyzfq.quartz.integration;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Quartz_Demo Created by bug on 16/6/1.
 */
public class ComplexJob implements Job {
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    System.out.println("Execute Complex Job With Spring!");

    JobDataMap data = jobExecutionContext.getMergedJobDataMap();
    System.out.println("name: " + data.get("name"));
  }
}
