package com.zjh.springcloud.topic;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author zhaojh
 * @date 2020/11/28 9:27
 * @description
 */
public interface RequeueTopic {

    String INPUT = "requeue-consumer";
    String OUTPUT = "requeue-producer";

    @Input(RequeueTopic.INPUT)
    SubscribableChannel input();

    @Output(RequeueTopic.OUTPUT)
    MessageChannel output();
}
