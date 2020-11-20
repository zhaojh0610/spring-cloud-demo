package com.zjh.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaojh
 * @date 2020/11/17 22:30
 */
@RestController
@Slf4j
public class Controller implements IService {

    @Value("${server.port}")
    private String port;

    @Override
    public String sayHi() {
        log.info("hi my port is :" + port);
        return "my port is " + port;
    }

    @Override
    public Friend sayHi(@RequestBody Friend friend) {
        friend.setPort(port);
        log.info(" you are " + friend.getName());
        return friend;
    }

    @Override
    public String retry(@RequestParam(name = "timeout") int timeout) {
        while (timeout-- > 0) {
            try {
                log.info("你累了，休息一秒。。。");
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
        log.info("retry " + port);
        return port;
    }

    @Override
    public String fallback() {
        throw new RuntimeException("black sheeep");
    }
}
