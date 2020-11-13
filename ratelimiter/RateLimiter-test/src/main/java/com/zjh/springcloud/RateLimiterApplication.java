package com.zjh.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author zhaojh
 * @date 2020/11/13 23:03
 */
@SpringBootApplication
public class RateLimiterApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(RateLimiterApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
