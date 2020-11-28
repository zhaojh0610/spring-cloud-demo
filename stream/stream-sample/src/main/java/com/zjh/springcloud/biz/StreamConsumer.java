package com.zjh.springcloud.biz;


import com.zjh.springcloud.topic.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhaojh
 * @date 2020/11/27 21:57
 * @description
 */
@Slf4j
@EnableBinding(value = {
        Sink.class,
        MyTopic.class,
        GroupTopic.class,
        DelayedTopic.class,
        ErrorTopic.class,
        RequeueTopic.class,
        DlqTopic.class,
        FallbackTopic.class
})
public class StreamConsumer {

    private AtomicInteger count = new AtomicInteger(1);

    @StreamListener(Sink.INPUT)
    public void consumer(Object payload) {
        log.info("message consumed successfully, payload={}", payload);
    }

    @StreamListener(MyTopic.INPUT)
    public void consumerMyMessage(Object payload) {
        log.info(" my message consumed successfully, payload={}", payload);
    }

    @StreamListener(GroupTopic.INPUT)
    public void consumerGroupMessage(Object payload) {
        log.info(" group message consumed successfully, payload={}", payload);
    }

    @StreamListener(DelayedTopic.INPUT)
    public void consumerDelayedMessage(MessageBean message) {
        log.info(" group message consumed successfully, payload={}", message.getPayload());
    }

    //  异常重试（单机版）
    @StreamListener(ErrorTopic.INPUT)
    public void consumerErrordMessage(MessageBean message) {
        log.info("are you ok?");
        if (count.incrementAndGet() % 3 == 0) {
            log.info("fine, thank you ,and you");
            count.set(0);
        } else {
            log.info("what's your problem");
            throw new RuntimeException("I'm not ok!");
        }
    }

    //  异常重试（联机版-重新入列）
    @StreamListener(RequeueTopic.INPUT)
    public void consumerRequeueMessage(MessageBean message) {
        log.info("are you ok?");
        try {
            Thread.sleep(3000L);
        } catch (Exception e) {
        }
        throw new RuntimeException();
    }

    //  异常重试（单机版）
    @StreamListener(DlqTopic.INPUT)
    public void consumerDlqMessage(MessageBean message) {
        log.info("Dlq are you ok?");
        if (count.incrementAndGet() % 3 == 0) {
            log.info("Dlq fine, thank you ,and you");
        } else {
            log.info("Dlq what's your problem");
            throw new RuntimeException("I'm not ok!");
        }
    }

    //  fallback + 升级版
    @StreamListener(FallbackTopic.INPUT)
    public void consumerFallbackMessage(MessageBean message, @Header("version") String version) {
        log.info("fallback are you ok?");
        if ("1.0".equals(version)) {
            log.info("fallback fine, thank you ,and you");
        } else if ("2.0".equals(version)) {
            log.info("fallback what's your problem");
            throw new RuntimeException("I'm not ok!");
        } else {
            log.info("fallback -version={}", version);
        }
    }

    @ServiceActivator(inputChannel = "fallback-topic.fallback-group.errors")
    public void fallback(Message message) {
        log.info("fallback entered");

    }
}
