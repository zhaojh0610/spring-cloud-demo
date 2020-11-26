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
@Slf4j
@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class SleuthStraceAMain {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/traceA")
    public String traceA() {
        log.info("-------traceA");
        return restTemplate.getForEntity("http://sleuth-traceB/traceB", String.class).getBody();
    }

    public static void main(String[] args) {
        SpringApplication.run(SleuthStraceAMain.class);
    }
}
