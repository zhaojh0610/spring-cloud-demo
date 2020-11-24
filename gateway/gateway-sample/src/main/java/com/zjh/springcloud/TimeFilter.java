package com.zjh.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author zhaojh
 * @date 2020/11/24 11:01
 * @description 实现GatewayFilter接口实现自定义过滤器，也可以实现 GlobalFilter全局过滤器
 */
@Component
@Slf4j
public class TimeFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /*业务逻辑*/
        StopWatch time = new StopWatch();
        time.start(exchange.getRequest().getURI().toString());
        /*回调函数*/
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            time.stop();
            log.info(time.prettyPrint());
        }));

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
