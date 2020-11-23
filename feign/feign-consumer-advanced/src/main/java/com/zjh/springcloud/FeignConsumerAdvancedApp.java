package com.zjh.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhaojh
 * @date 2020/11/17 23:12
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class FeignConsumerAdvancedApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FeignConsumerAdvancedApp.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
