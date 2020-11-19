package com.zjh.springcloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaojh
 * @date 2020/11/19 22:10
 */
@Service
@Slf4j
public class RequestCacheService {

    @Autowired
    private MyService service;

    @CacheResult
    @HystrixCommand(commandKey = "cacheKey")
    public Friend requestCache(@CacheKey String name) {
        log.info("request cache " + name);
        Friend friend = new Friend();
        friend.setName(name);
        friend = service.sayHi(friend);
        log.info("after cache " + name);
        return friend;
    }
}
