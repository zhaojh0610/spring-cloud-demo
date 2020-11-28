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
public interface FallbackTopic {

    String INPUT = "fallback-consumer";
    String OUTPUT = "fallback-producer";

    @Input(FallbackTopic.INPUT)
    SubscribableChannel input();

    @Output(FallbackTopic.OUTPUT)
    MessageChannel output();
}
