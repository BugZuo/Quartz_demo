package com.flyzfq.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Quartz_Demo Created by bug on 16/5/31.
 */
@Configuration
@EnableWebMvc
//@EnableAspectJAutoProxy
@ComponentScan("com.flyzfq")
public class AppConfig extends WebMvcConfigurerAdapter {
}
