package com.flyzfq.quartz.integration;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Quartz_Demo Created by bug on 16/6/1.
 */
public class SimpleJob implements Job {

  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    System.out.println("Show My Simple Job -- with Spring!");
  }
}