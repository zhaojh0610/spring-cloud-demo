package com.zjh.springcloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
        return myService.fallback();
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


    @GetMapping("/timeout")
    @HystrixCommand(
            fallbackMethod = "timeoutFallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")})
    public String timeout(Integer timeout) {
        return myService.retry(timeout);
    }

    public String timeoutFallback(Integer timeout) {
        return "success";
    }
}
