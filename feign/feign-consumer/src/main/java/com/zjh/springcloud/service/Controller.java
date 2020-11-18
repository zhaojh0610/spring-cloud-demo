package com.zjh.springcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaojh
 * @date 2020/11/17 20:27
 */
@RestController
public class Controller {

    @Autowired
    private service service;

    @GetMapping("sayHi")
    public String sayHi() {
        return service.sayHi();
    }

    @GetMapping("retry")
    public String retry(Integer timeout) {
        return service.retry(timeout);
    }
}
