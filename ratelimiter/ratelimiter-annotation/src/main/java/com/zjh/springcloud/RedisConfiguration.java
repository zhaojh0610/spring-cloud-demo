package com.zjh.springcloud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

/**
 * @author zhaojh
 * @date 2020/11/13 22:17
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory factory) {
        return new StringRedisTemplate(factory);

    }

    @Bean
    public DefaultRedisScript loadRedisScript() {
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setLocation(new ClassPathResource("rateLimiter.lua"));
        redisScript.setResultType(Boolean.class);
        return redisScript;
    }
}
