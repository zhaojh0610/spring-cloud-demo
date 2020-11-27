package com.zjh.springcloud.biz;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @author zhaojh
 * @date 2020/11/27 21:57
 * @description
 */
@Slf4j
@EnableBinding(value = {
        Sink.class
})
public class StreamConsumer {

    @StreamListener(Sink.INPUT)
    public void consumer(Object payload) {
        log.info("message consumed successfully, payload={}", payload);
    }


}
