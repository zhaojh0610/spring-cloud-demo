package com.zjh.springcloud.hystrix;

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

    @Override
    public String error() {
        log.info("Fallback: I'm not a black sheep any more");
        return "Fallback: I'm not a black sheep any more";
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
