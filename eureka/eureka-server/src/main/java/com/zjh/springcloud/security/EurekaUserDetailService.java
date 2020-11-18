package com.zjh.springcloud.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author zhaojh
 * @date 2020/11/18 14:08
 */
@Component
public class EurekaUserDetailService implements UserDetailsService {

    @Value("${spring.security.user.password}")
    private String password;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername---------->username=" + username);
        System.out.println("loadUserByUsername---------->password=" + password);
        User user = new User(username, password, new ArrayList<>());
        return user;
    }

}
