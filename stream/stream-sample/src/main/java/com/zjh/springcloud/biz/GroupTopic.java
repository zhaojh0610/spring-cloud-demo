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
public interface GroupTopic {

    String INPUT = "group-consumer";
    String OUTPUT = "group-producer";

    @Input(GroupTopic.INPUT)
    SubscribableChannel input();

    @Output(GroupTopic.OUTPUT)
    MessageChannel output();
}
