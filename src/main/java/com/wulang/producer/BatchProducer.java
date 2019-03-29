package com.wulang.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wulang
 * @description:batch、
 **/
public class BatchProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("rmq-group");
        producer.setNamesrvAddr("10.0.17.134:9876");
        producer.setInstanceName("producer1");
        producer.start();
        try {
            for (int i = 0; i < 10; i++) {
//                Thread.sleep(1000);  //每秒发送一次MQ
                String topic="TopicTest";
                List<Message> messages = new ArrayList<>();
                messages.add(new Message(topic, "TagA", "OrderID001", "Hello world 0".getBytes()));
                messages.add(new Message(topic, "TagA", "OrderID002", "Hello world 1".getBytes()));
                messages.add(new Message(topic, "TagA", "OrderID003", "Hello world 2".getBytes()));
//                SendResult sendResult = producer.send(messages);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}
