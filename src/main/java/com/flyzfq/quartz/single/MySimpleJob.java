package com.flyzfq.quartz.single;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Quartz_Demo Created by bug on 16/5/31.
 */
public class MySimpleJob implements Job {
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    System.out.println("Show My Simple Job Here!");
  }
}