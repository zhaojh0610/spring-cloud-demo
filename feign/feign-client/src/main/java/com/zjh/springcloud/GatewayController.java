package com.zjh.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaojh
 * @date 2020/11/23 21:45
 */
@RestController
@RequestMapping("gateway")
@Slf4j
public class GatewayController {

    private static final Map<Long, Product> items = new ConcurrentHashMap<>();

    @GetMapping("details")
    @ResponseBody
    public Product getDetails(@RequestParam("pid") Long pid) {
        if (!items.containsKey(pid)) {
            Product product = Product.builder()
                    .productId(pid)
                    .descript("好吃不贵")
                    .stock((long) 100)
                    .build();
            items.putIfAbsent(pid, product);
        }
        return items.get(pid);
    }

    @PostMapping("placeOrder")
    @ResponseBody
    public String buy(@RequestParam("pid") Long pid) {
        Product prod = items.get(pid);
        if (prod == null) {
            return "product not found";
        }
        if (prod.getStock() <= 0) {
            return "Sold out";
        }
        synchronized (prod) {
            if (prod.getStock() <= 0) {
                return "Sold out";
            }
            prod.setStock(prod.getStock() - 1);
        }
        return "Order placed";

    }
}


