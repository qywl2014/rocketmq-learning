package com.wulang.consumer;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.Date;
import java.util.List;

public class Consumer1 {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
                "rmq-rl-group-0612-3");

        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.subscribe("TopicTest", "*");

//        consumer.setNamesrvAddr("mq1.kktv2.com:9876");
//        consumer.subscribe("lala", "*");
//        consumer.subscribe("OrderNewTopic", "*");

        consumer.setInstanceName("consumer113");//默认集群 从最后消费位置开始消费
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
//        consumer.setConsumeTimestamp("20190528143300");
//        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.setPullBatchSize(10);
        consumer.setConsumeMessageBatchMaxSize(1);

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(
                    List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//                for (MessageExt msg : msgs) {
//                    System.out.println(new Date(msg.getStoreTimestamp()) +"  "+msgs.size());
//                }
                System.out.println("...size = "+msgs.size());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer Started.");
    }
}
