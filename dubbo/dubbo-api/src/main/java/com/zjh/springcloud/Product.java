package com.zjh.springcloud;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : zhaojh
 * @date : 2020/11/29 14:33
 * @description :
 */
@Data
public class Product implements Serializable {
    private String name;
    private BigDecimal price;
}
