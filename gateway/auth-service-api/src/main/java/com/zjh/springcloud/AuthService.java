package com.zjh.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhaojh
 * @date 2020/11/24 15:58
 * @description
 */
@FeignClient("auth-service")
public interface AuthService {

    @PostMapping("/login")
    @ResponseBody
    AuthResponse login(@RequestParam("username") String username, @RequestParam("password") String password);

    @GetMapping("/verify")
    AuthResponse verify(@RequestParam("token") String token, @RequestParam("username") String username);

    @PostMapping("/refresh")
    @ResponseBody
    AuthResponse refresh(@RequestParam("refresh") String refresh);

}
