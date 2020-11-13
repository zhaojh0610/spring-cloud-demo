package com.zjh.springcloud.annotation;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author zhaojh
 * @date 2020/11/13 23:45
 */
@Slf4j
@Aspect
@Component
public class RateLimiterAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisScript<Boolean> rateLimitLua;

    @Pointcut("@annotation(com.zjh.springcloud.annotation.AccessLimiter)")
    public void cut() {
    }

    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        AccessLimiter annotation = method.getAnnotation(AccessLimiter.class);
        String key = annotation.methodKey();
        Integer limit = annotation.limit();
        //  如果没有设置methodKey，从调用方法签名自动生成key
        if (StringUtils.isEmpty(key)) {
            Class<?>[] types = method.getParameterTypes();
            key = method.getClass() + method.getName();
            if (types != null) {
                String paramTypes = Arrays.stream(types).map(Class::getName).collect(Collectors.joining(","));
                log.info("param types:" + paramTypes);
                key += "#" + paramTypes;
            }
        }

        // 2. 调用Redis
        boolean acquired = stringRedisTemplate.execute(
                rateLimitLua, // Lua script的真身
                Lists.newArrayList(key), // Lua脚本中的Key列表
                limit.toString() // Lua脚本Value列表
        );

        if (!acquired) {
            log.error("your access is blocked, key={}", key);
            throw new RuntimeException("Your access is blocked");
        }

    }
}
