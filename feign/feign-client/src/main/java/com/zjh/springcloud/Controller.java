package com.zjh.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaojh
 * @date 2020/11/17 22:30
 */
@RestController
@Slf4j
public class Controller implements IService{

    @Value("${server.port}")
    private String port;

    @Override
    public String sayHi() {
        log.info("hi my port is :" + port);
        return "my port is " + port;
    }

    @Override
    public Friend sayHi(Friend friend) {
        friend.setPort(port);
        log.info(" you are " + friend.getName());
        return friend;
    }
}
