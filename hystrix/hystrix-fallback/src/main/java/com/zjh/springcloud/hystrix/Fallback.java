package com.zjh.springcloud.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zjh.springcloud.Friend;
import com.zjh.springcloud.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhaojh
 * @date 2020/11/19 15:30
 */
@Component
@Slf4j
public class Fallback implements MyService {

    @HystrixCommand(fallbackMethod = "fallback2")
    @Override
    public String fallback() {
        log.info("Fallback: I'm not a black sheep any more");
        throw new RuntimeException("first fallback");
    }

    @HystrixCommand(fallbackMethod = "fallback3")
    public String fallback2() {
        log.info("fallback again");
        throw new RuntimeException("fallback again");
    }

    public String fallback3() {
        log.info("fallback again and again");
        return "success";
    }

    @Override
    public String sayHi() {
        return null;
    }

    @Override
    public Friend sayHi(Friend friend) {
        return null;
    }

    @Override
    public String retry(int timeout) {
        return "you are late ...";
    }
}
