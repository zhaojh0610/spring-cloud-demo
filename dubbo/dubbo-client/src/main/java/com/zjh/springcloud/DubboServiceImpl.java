package com.zjh.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : zhaojh
 * @date : 2020/11/29 15:06
 * @description :
 */
@Service
@Slf4j
public class DubboServiceImpl implements DubboService {

    @Override
    public Product publish(Product product) {
        log.info("publish product,product name={}", product.getName());
        return product;
    }
}
