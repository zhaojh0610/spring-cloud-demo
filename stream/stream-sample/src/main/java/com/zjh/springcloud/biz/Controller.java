package com.zjh.springcloud.biz;

import com.zjh.springcloud.topic.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhaojh
 * @date : 2020/11/28 9:40
 * @description :
 */
@RestController
@Slf4j
public class Controller {

    @Autowired
    private MyTopic producer;

    @Autowired
    private GroupTopic groupProducerProducer;

    @Autowired
    private DelayedTopic delayedTopicProducer;

    @Autowired
    private ErrorTopic errorTopicProducer;

    @Autowired
    private RequeueTopic requeueTopicProducer;

    @Autowired
    private DlqTopic dlqTopicProducer;

    @Autowired
    private FallbackTopic fallbackTopicProducer;
    //  消息广播
    @PostMapping("/send")
    public void sendMessage(@RequestParam("body") String body) {
        Message<String> message = MessageBuilder.withPayload(body).build();
        producer.output().send(message);
    }

    //  消费组和消息分区
    @PostMapping("/sendToGroup")
    public void sendToGroup(@RequestParam("body") String body) {
        Message<String> message = MessageBuilder.withPayload(body).build();
        groupProducerProducer.output().send(message);
    }

    //  延迟消息
    @PostMapping("/sendDM")
    public void sendDelayedMessage(@RequestParam("body") String body, @RequestParam("seconds") Integer seconds) {
        MessageBean message = new MessageBean();
        message.setPayload(body);
        log.info("ready to send delayed message");
        delayedTopicProducer.output().send(MessageBuilder
                .withPayload(message)
                .setHeader("x-delay", 1000 * seconds)
                .build());
    }

    //  异常重试（单机版）
    @PostMapping("/error")
    public void sendErrorMessage(@RequestParam("body") String body) {
        MessageBean message = new MessageBean();
        message.setPayload(body);
        log.info("ready to send error message");
        errorTopicProducer.output().send(MessageBuilder
                .withPayload(message)
                .build());
    }

    //  异常重试（异常消息重新放入队列）
    @PostMapping("/requeue")
    public void sendRequeueMessage(@RequestParam("body") String body) {
        MessageBean message = new MessageBean();
        message.setPayload(body);
        log.info("ready to send error message");
        requeueTopicProducer.output().send(MessageBuilder
                .withPayload(message)
                .build());
    }

    // 死信队列 测试
    @PostMapping("/dlq")
    public void sendMessageToDlq(@RequestParam("body") String body) {
        MessageBean message = new MessageBean();
        message.setPayload(body);
        log.info("dlq ready to send error message");
        dlqTopicProducer.output().send(MessageBuilder
                .withPayload(message)
                .build());
    }

    // fallback + 升版
    @PostMapping("/fallback")
    public void sendMessageToFallback(@RequestParam("body") String body, @RequestParam("version") String version) {
        MessageBean message = new MessageBean();
        message.setPayload(body);
        log.info("dlq ready to send error message");
        fallbackTopicProducer.output().send(MessageBuilder
                .withPayload(message)
                .setHeader("version", version)
                .build());
    }
}
