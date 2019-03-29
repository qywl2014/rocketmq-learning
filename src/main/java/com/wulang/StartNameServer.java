package com.wulang;

import org.apache.rocketmq.namesrv.NamesrvStartup;

public class StartNameServer {
    //需要设置ROCKETMQ_HOME环境变量 值为rocketmq目录
    public static void main(String[] args) {
        NamesrvStartup.main(new String[]{});
    }
}
