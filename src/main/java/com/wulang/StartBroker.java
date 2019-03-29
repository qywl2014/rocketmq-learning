package com.wulang;

import org.apache.rocketmq.broker.BrokerStartup;

public class StartBroker {
    public static void main(String[] args) {
        BrokerStartup.main(new String[]{"-n","localhost:9876"});
    }
}
