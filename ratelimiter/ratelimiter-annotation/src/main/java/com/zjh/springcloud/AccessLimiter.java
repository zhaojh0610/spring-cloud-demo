package com.zjh.springcloud;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

/**
 * @author zhaojh
 * @date 2020/11/13 22:00
 */
@Service
@Slf4j
@Deprecated
public class AccessLimiter {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedisScript<Boolean> rateLimiterLua;

    public void accessLimiter(String key, Integer limit) {
        boolean acquire = redisTemplate.execute(
                rateLimiterLua, //   Lua script 真身
                Lists.<String>newArrayList(key),    //  Lua脚本中的可以列表
                limit.toString()    //  Lua脚本value列表
        );
        if (!acquire) {
            log.error("your access is blocked!");
        }
    }
}
