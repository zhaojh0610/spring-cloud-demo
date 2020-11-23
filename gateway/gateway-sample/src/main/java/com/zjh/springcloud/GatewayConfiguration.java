package com.zjh.springcloud;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;

/**
 * @author zhaojh
 * @date 2020/11/23 20:42
 */
@Configuration
public class GatewayConfiguration {

    @Bean
    @Order
    public RouteLocator customizedRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/java/hxj/**")
                        .and().method(HttpMethod.GET)
                        .and().header("name")
                        .filters(f -> f.stripPrefix(2)
                                .addResponseHeader("java-param", "gateway-config")
                        )
                        .uri("lb://FEIGN-CLIENT")
                ).build();
    }
}
