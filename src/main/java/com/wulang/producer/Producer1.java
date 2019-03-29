package com.wulang.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.Date;

/**
 * nohup sh mqnamesrv &
 * nohup sh mqbroker -n localhost:9876 &
 * sh mqshutdown namesrv
 * sh mqshutdown broker
 */
public class Producer1 {
    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("rmq-group");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setInstanceName("producer1");
        producer.start();
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);  //每秒发送一次MQ
                Message msg = new Message("TopicTest","TagA",("producer1 "+i).getBytes());
                msg.setDelayTimeLevel(3);
                SendResult sendResult = producer.send(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}
