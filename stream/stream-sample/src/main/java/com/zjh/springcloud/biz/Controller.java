package com.zjh.springcloud.biz;

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
public class Controller {

    @Autowired
    private MyTopic producer;

    @PostMapping("/send")
    public void sendMessage(@RequestParam("body") String body) {
        Message<String> message = MessageBuilder.withPayload(body).build();
        producer.output().send(message);
    }
}
