package com.flyzfq;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Quartz_Demo Created by bug on 16/5/31.
 */
public class Application implements WebApplicationInitializer {
  public void onStartup(ServletContext servletContext) throws ServletException {
    System.out.println("Initializing Application for " + servletContext.getServerInfo());

    // Create ApplicationContext
    AnnotationConfigWebApplicationContext applicationContext =
        new AnnotationConfigWebApplicationContext();
    applicationContext.setConfigLocation("com.flyzfq.config");

    // Add the servlet mapping manually and make it initialize automatically
    DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
    ServletRegistration.Dynamic servlet = servletContext.addServlet("mvc-dispatcher",
        dispatcherServlet);
    servlet.addMapping("/*");
    servlet.setAsyncSupported(true);
    servlet.setLoadOnStartup(1);
  }
}
