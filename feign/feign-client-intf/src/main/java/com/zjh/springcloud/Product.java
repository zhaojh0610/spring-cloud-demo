package com.zjh.springcloud;

import lombok.Builder;
import lombok.Data;

/**
 * @author zhaojh
 * @date 2020/11/23 21:47
 */
@Builder
@Data
public class Product {

    private Long productId;
    private String descript;
    private Long stock;
}
