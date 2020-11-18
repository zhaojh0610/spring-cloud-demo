package com.zjh.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaojh
 * @date 2020/11/17 23:14
 */
@RestController
@Slf4j
public class Controller {

    @Autowired
    private IService iService;


    @GetMapping("sayHi")
    public String sayHi() {
        return iService.sayHi();
    }}
