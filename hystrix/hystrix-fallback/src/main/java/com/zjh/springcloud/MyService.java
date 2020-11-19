package com.zjh.springcloud;

import com.zjh.springcloud.hystrix.Fallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhaojh
 * @date 2020/11/19 15:29
 */
@FeignClient(value = "feign-client", fallback = Fallback.class)
public interface MyService extends IService {
}
