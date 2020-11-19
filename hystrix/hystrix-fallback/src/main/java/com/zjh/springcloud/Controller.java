package com.zjh.springcloud;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaojh
 * @date 2020/11/19 16:02
 */
@RestController
public class Controller {

    @Autowired
    private MyService myService;

    @Autowired
    private RequestCacheService requestCacheService;

    @GetMapping("/fallback")
    public String fallback() {
        return myService.error();
    }

    @GetMapping("/retry")
    public String retry(Integer timeout) {
        return myService.retry(timeout);
    }

    @GetMapping("/cache")
    public Friend requestCache(String name) {
        @Cleanup HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Friend friend = requestCacheService.requestCache(name);
        friend = requestCacheService.requestCache(name);
        return friend;

    }
}
