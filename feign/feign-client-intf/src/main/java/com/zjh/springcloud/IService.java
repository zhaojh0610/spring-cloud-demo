package com.zjh.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhaojh
 * @date 2020/11/17 22:23
 */
@FeignClient("feign-client")
public interface IService {

    @RequestMapping("/sayHi")
    public String sayHi();

    @PostMapping("/sayHi")
    public Friend sayHi(@RequestBody Friend friend);

    @GetMapping("/retry")
    public String retry(@RequestParam(name = "timeout") int timeout);

    @GetMapping("/error")
    public String error();
}
