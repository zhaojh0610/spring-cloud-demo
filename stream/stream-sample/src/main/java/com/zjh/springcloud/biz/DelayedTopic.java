package com.zjh.springcloud.biz;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author zhaojh
 * @date 2020/11/28 9:27
 * @description
 */
public interface DelayedTopic {

    String INPUT = "delayed-consumer";
    String OUTPUT = "delayed-producer";

    @Input(DelayedTopic.INPUT)
    SubscribableChannel input();

    @Output(DelayedTopic.OUTPUT)
    MessageChannel output();
}
