package com.zjh.springcloud;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : zhaojh
 * @date : 2020/11/29 15:04
 * @description :
 */
@SpringBootApplication
@EnableDubbo
public class DubboApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboApplication.class);
    }
}
