package com.zjh.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhaojh
 * @date 2020/11/14 19:12
 */
@RestController
@Slf4j
public class Controller {

    @Autowired
    private LoadBalancerClient client;
//    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("hello")
    public String hello() {
        ServiceInstance instance = client.choose("eureka-client");
        if (instance == null) {
            return "No available instance";
        }
        String url = String.format("http://%s:%s/sayHi", instance.getHost(), instance.getPort());

        return restTemplate.getForObject(url, String.class);
    }

    @PostMapping("hello")
    public Friend helloTest() {
        ServiceInstance instance = client.choose("eureka-client");
        if (instance == null) {
            return null;
        }
        String url = String.format("http://%s:%s/sayHi", instance.getHost(), instance.getPort());
        Friend friend = new Friend();
        friend.setName("eureka-consumer");
        friend.setPort("32333");
        return restTemplate.postForObject(url, friend, Friend.class);

    }
}
