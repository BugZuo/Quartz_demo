package com.flyzfq.kafka;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import kafka.consumer.KafkaStream;

/**
 * Quartz_Demo Created by bug on 16/6/1.
 */
@Component
public class SimpleKafka implements InitializingBean {
  @Value("#{'${kafka.topic}'.split(',')}")
  private List<String> topics;

  @Value("${partition.num}")
  private Integer partitionNum;

  @Resource
  private Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap;

  private static ExecutorService executor = Executors.newFixedThreadPool(5);

  public void afterPropertiesSet() {
    for (String topic : topics) {
      for (int i = 0; i < partitionNum; i++) {
        final Integer indexOfStream = i;
        KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(indexOfStream);
        executor.submit(new MyConsumer(stream, topic, i));
      }
    }
  }
}
