package com.flyzfq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

/**
 * Quartz_Demo Created by bug on 16/5/31.
 */
@Configuration
public class PropertyConfig {
  @Bean
  public static PropertySourcesPlaceholderConfigurer properties() {
    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer =
        new PropertySourcesPlaceholderConfigurer();

    FileSystemResource fileSystemResource =
        new FileSystemResource("application.properties");
    if (fileSystemResource.isReadable()) {
      propertySourcesPlaceholderConfigurer.setLocation(fileSystemResource);
    }
    propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
    return propertySourcesPlaceholderConfigurer;
  }
}
