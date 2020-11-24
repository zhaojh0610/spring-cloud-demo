package com.zjh.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author zhaojh
 * @date 2020/11/24 19:59
 * @description
 */
@RestController
@Slf4j
public class Controller {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    @ResponseBody
    public AuthResponse login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Account account = Account.builder().username(username).build();
        //  TODO 验证username + password
        String token = jwtService.token(account);
        account.setToken(token);
        account.setRefreshToken(UUID.randomUUID().toString());
        //  将token放入redis中
        redisTemplate.opsForValue().set(account.getRefreshToken(), account);
        AuthResponse authresponse = AuthResponse.builder().account(account).code(ResponseCode.SUCCESS).build();
        return authresponse;
    }

    @GetMapping("refresh")
    @ResponseBody
    public AuthResponse refresh(@RequestParam("refresh") String refreshToken) {
        Account account = (Account) redisTemplate.opsForValue().get(refreshToken);
        if (account == null) {
            return AuthResponse.builder().code(ResponseCode.USER_NOT_FOUND).build();
        }
        String token = jwtService.token(account);
        account.setToken(token);
        account.setRefreshToken(UUID.randomUUID().toString());
        redisTemplate.delete(refreshToken);
        redisTemplate.opsForValue().set(account.getRefreshToken(), account);
        return AuthResponse.builder().account(account).code(ResponseCode.SUCCESS).build();
    }

    @GetMapping("verify")
    public AuthResponse verify(@RequestParam("username") String username, @RequestParam("token") String token) {
        boolean isPass = jwtService.verify(token, username);
        return AuthResponse.builder()
                //  TODO 此处最好使用invalid token之类的错误信息
                .code(isPass ? ResponseCode.SUCCESS : ResponseCode.USER_NOT_FOUND)
                .build();
    }
}
