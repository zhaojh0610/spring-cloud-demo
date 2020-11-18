package com.zjh.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhaojh
 * @date 2020/11/17 22:28
 */
@EnableDiscoveryClient
@SpringBootApplication
public class FeignClientApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FeignClientApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}

