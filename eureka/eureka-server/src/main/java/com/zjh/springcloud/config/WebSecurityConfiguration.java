package com.zjh.springcloud.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author zhaojh
 * @date 2020/11/16 18:55
 */
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private EurekaUserDetailService eurekaUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(eurekaUserDetailService).passwordEncoder(new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                System.out.println("rawPassword---->" + rawPassword);
//                return null;
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                System.out.println("rawPassword------>" + rawPassword);
//                System.out.println("encodedPassword------>" + encodedPassword);
//                return false;
//            }
//        });
//        super.configure(auth);
//    }
}
