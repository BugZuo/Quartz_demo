package com.flyzfq.config;

import com.google.common.collect.Maps;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Quartz_Demo Created by bug on 16/6/1.
 */
@Configuration
public class KafkaConfig {
  @Value("${zookeeper.url}")
  private String zookeeper;

  @Value("${kafka.group}")
  private String groupId;

  @Value("#{'${kafka.topic}'.split(',')}")
  private List<String> topics;

  @Value("${partition.num}")
  private Integer partitionNum;

  @Bean
  public ConsumerConfig consumerConfig() {
    Properties properties = new Properties();
//    properties.put("consumer.id", "local-bug");
    properties.put("zookeeper.connect", zookeeper); // zookeeper连接,可配置多个,以逗号分隔
    properties.put("group.id", groupId); // 组

    properties.put("zookeeper.session.timeout.ms", "10000");
    properties.put("zookeeper.sync.time.ms", "500");
    properties.put("auto.commit.interval.ms", "1000");
    properties.put("refresh.leader.backoff.ms", "10000");
    properties.put("rebalance.backoff.ms", "10000");
//    properties.put("consumer.timeout.ms", "20000");
//    properties.put("auto.offset.reset", "smallest");
    return new ConsumerConfig(properties);
  }

  @Bean
  public ConsumerConnector consumerConnector() {
    return Consumer.createJavaConsumerConnector(consumerConfig());
  }

  @Bean
  public Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap() {
    Map<String, Integer> topicCountMap = Maps.newHashMap();
    for (String topic : topics) {
      topicCountMap.put(topic, partitionNum);
    }

    return consumerConnector().createMessageStreams(topicCountMap);
  }
}
