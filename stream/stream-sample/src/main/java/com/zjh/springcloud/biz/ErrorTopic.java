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
public interface ErrorTopic {

    String INPUT = "error-consumer";
    String OUTPUT = "error-producer";

    @Input(ErrorTopic.INPUT)
    SubscribableChannel input();

    @Output(ErrorTopic.OUTPUT)
    MessageChannel output();
}
