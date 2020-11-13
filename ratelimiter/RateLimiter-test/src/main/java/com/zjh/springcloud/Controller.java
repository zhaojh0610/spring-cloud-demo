package com.zjh.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaojh
 * @date 2020/11/13 23:06
 */
@RestController
public class Controller {

    @Autowired
    private AccessLimiter accessLimiter;

    @RequestMapping("test")
    public String test() {
        accessLimiter.accessLimiter("rateLimiter-test", 1);
        return "success";
    }

    @RequestMapping("test-annotation")
    @com.zjh.springcloud.annotation.AccessLimiter(methodKey = "rateLimiter",limit = 1)
    public String testAnnotation() {
        return "success";
    }
}
