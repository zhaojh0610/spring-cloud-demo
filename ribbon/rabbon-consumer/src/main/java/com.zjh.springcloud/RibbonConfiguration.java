package com.zjh.springcloud;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaojh
 * @date 2020/11/15 22:53
 */
@Configuration
@RibbonClient( name = "eureka-client",configuration = com.netflix.loadbalancer.RandomRule.class)
public class RibbonConfiguration {

//    @Bean
//    public IRule rule() {
//        return new RandomRule();
//    }
}
