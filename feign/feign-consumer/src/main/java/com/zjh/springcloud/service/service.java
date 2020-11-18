package com.zjh.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zhaojh
 * @date 2020/11/17 20:26
 */
@FeignClient("eureka-client")
public interface service {

    @GetMapping("/sayHi")
    public String sayHi();

    @GetMapping("/retry")
    public String retry(Integer timeout);
}
