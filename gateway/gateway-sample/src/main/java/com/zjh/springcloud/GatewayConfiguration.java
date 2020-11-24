package com.zjh.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;

import java.time.ZonedDateTime;

/**
 * @author zhaojh
 * @date 2020/11/23 20:42
 */
@Configuration
public class GatewayConfiguration {

    @Autowired
    private TimeFilter timeFilter;

    @Bean
    @Order
    public RouteLocator customizedRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/java/hxj/**")
                        .and().method(HttpMethod.GET)
                        .and().header("name")
                        .filters(f -> f.stripPrefix(2)
                                .addResponseHeader("java-param", "gateway-config")
                                .filter(timeFilter)
                        )
                        .uri("lb://FEIGN-CLIENT")
                )
                .route(r -> r.path("/seckill/**")
                                .and().after(ZonedDateTime.now().plusMinutes(1))
//                        .and().before()
//                        .and().between()
                                .filters(f -> f.stripPrefix(1))
                                .uri("lb://FEIGN-CLIENT")
                ).build();
    }
}
