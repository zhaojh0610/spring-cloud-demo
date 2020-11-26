package com.zjh.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhaojh
 * @date 2020/11/26 19:19
 * @description
 */
@RestController
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class SleuthStraceBMain {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/traceB")
    public String traceB() {
        log.info("-------traceB");
        return "trancB";
    }

    public static void main(String[] args) {
        SpringApplication.run(SleuthStraceBMain.class);
    }
}
