package com.zjh.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhaojh
 * @date 2020/11/15 21:48
 */
@RestController
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("sayHi")
    public String sayHi() {
        return restTemplate.getForObject("http://eureka-client/sayHi",String.class);
    }
}
