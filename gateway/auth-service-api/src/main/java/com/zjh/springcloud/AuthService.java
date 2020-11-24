package com.zjh.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhaojh
 * @date 2020/11/24 15:58
 * @description
 */
@FeignClient("auth-service")
public interface AuthService {

    @RequestMapping("/login")
    @ResponseBody
    public AuthResponse login(@RequestParam("username") String username, @RequestParam("password") String password);

    @RequestMapping("/verify")
    public AuthResponse verify(@RequestParam("token") String token, @RequestParam("username") String username);

    @RequestMapping("/refresh")
    @ResponseBody
    public AuthResponse refresh(@RequestParam("refresh") String refresh);

}
