package com.wulang.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer2 {
    private static AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
                "rmq-group");

//        consumer.setNamesrvAddr("10.0.17.134:9876");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setInstanceName("consumer2");
        consumer.setConsumeThreadMin(2);
        consumer.setConsumeThreadMax(2);
        consumer.subscribe("TopicTest", "*");
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(
                    List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                try{
                    int currentCount = count.incrementAndGet();
                    System.out.println(currentCount+"---begin thread name:"+Thread.currentThread().getName());
                    Thread.sleep(60000);
                    for (MessageExt msg : msgs) {
                        System.out.println(currentCount+"---"+Thread.currentThread().getName()+"-"+new String(msg.getBody()));
                    }
                    System.out.println(currentCount+"---end");
                }catch (Exception e){
                    System.out.println(e);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer Started.");
    }
}
