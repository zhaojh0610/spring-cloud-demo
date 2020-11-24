package com.zjh.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author zhaojh
 * @date 2020/11/24 21:29
 * @description
 */
@Component
@Slf4j
public class AuthFilter implements GatewayFilter, Ordered {

    private static final String AUTH = "Authorization";
    private static final String USERNAME = "username";

    @Autowired
    private AuthService authService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(AUTH);
        String username = headers.getFirst(USERNAME);

        //  校验token是否存在
        if (StringUtils.isBlank(token)) {
            log.info(" token not found ");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //  校验token是否合法
        AuthResponse authResponse = authService.verify(token, username);
        Long code = authResponse.getCode();
        if (code != 1) {
            log.info(" token invalid");
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();
        }
        //  TODO 将用户信息存放在请求header中传递给下游应用
        ServerHttpRequest.Builder mutate = request.mutate();
        ServerHttpRequest httpRequest = mutate.header(USERNAME, username).build();

        //  TODO 如果响应中需要放数据，也可以放在response的header中
        response.setStatusCode(HttpStatus.OK);
        return chain.filter(exchange.mutate().request(httpRequest).response(response).build());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
