package com.flyzfq.kafka;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

/**
 * Quartz_Demo Created by bug on 16/6/1.
 */
public class MyConsumer implements Runnable {

  private KafkaStream kafkaStream;
  private int thread_num;
  private String topic;

  public MyConsumer(KafkaStream kafkaStream, String topic, int thread_num) {
    this.kafkaStream = kafkaStream;
    this.thread_num = thread_num;
    this.topic = topic;
  }

  public void run() {
    ConsumerIterator<byte[], byte[]> it = kafkaStream.iterator();
    String msg;
    try {
      while (it.hasNext()) {
        msg = new String(it.next().message());
        System.out.println(topic + ":" + thread_num + " : " + msg);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("Thread-number-" + thread_num + " shutdown.");

  }
}