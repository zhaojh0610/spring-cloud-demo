package com.zjh.springcloud;

/**
 * @author : zhaojh
 * @date : 2020/11/29 14:34
 * @description :
 */
public interface DubboService {
    /**
     * 商品发布
     * @param product
     * @return
     */
    Product publish(Product product);
}
