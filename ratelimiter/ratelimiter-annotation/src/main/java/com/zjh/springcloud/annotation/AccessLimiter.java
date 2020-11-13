package com.zjh.springcloud.annotation;

import java.lang.annotation.*;

/**
 * @author zhaojh
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimiter {

    int limit();

    String methodKey() default "";
}
