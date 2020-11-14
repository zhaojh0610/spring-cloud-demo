package com.zjh.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaojh
 * @date 2020/11/14 14:00
 */
@RestController
@Slf4j
public class Controller {
    @Value("${server.port}")
    public String port;


    @RequestMapping("/sayHi")
    public String sayHi() {
        log.info("hi my port is :" + port);
        return "my port is " + port;
    }

    @PostMapping("/sayHi")
    public Friend sayHi(@RequestBody Friend friend) {
        friend.setPort(port);
        log.info(" you are " + friend.getName());
        return friend;
    }

}
