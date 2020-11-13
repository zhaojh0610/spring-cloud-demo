package com.zjh.springcloud;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zhaojh
 * @date 2020/11/12 20:59
 */
@RestController
@Slf4j
public class Controller {

    RateLimiter limiter = RateLimiter.create(2);

    //  非阻塞限流
    @RequestMapping("tryAcquire")
    public String tryAcquire(Integer count) {
        if (limiter.tryAcquire(count)) {
            log.info("success, rate is {}", limiter.getRate());
            return "success";
        } else {
            log.info("fail, rate is {}", limiter.getRate());
            return "fail";
        }
    }

    //  限定时间的非阻塞限流
    @RequestMapping("tryAcquireWithTimeout")
    public String tryAcquireWithTimeout(Integer count, Integer timeout) {
        if (limiter.tryAcquire(count, timeout, TimeUnit.SECONDS)) {
            log.info("success, rate is {}", limiter.getRate());
            return "success";
        } else {
            log.info("fail, rate is {}", limiter.getRate());
            return "fail";
        }
    }

    //  同步阻塞限流
    @RequestMapping("acquire")
    public String acquire(Integer count) {
        limiter.acquire(count);
        log.info("success, rate is {}", limiter.getRate());
        return "success";
    }

    @RequestMapping("nginx")
    public String nginx() {
        log.info("nginx success");
        return "success";
    }



}
