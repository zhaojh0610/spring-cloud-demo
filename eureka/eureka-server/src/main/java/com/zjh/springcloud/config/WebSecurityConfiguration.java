//package com.zjh.springcloud.config;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @author zhaojh
// * @date 2020/11/16 18:55
// */
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//    /**
//     * Override this method to configure the {@link HttpSecurity}. Typically subclasses
//     * should not invoke this method by calling super as it may override their
//     * configuration. The default configuration is:
//     *
//     * <pre>
//     * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
//     * </pre>
//     *
//     * @param http the {@link HttpSecurity} to modify
//     * @throws Exception if an error occurs
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().ignoringAntMatchers("/eureka/**");
//        super.configure(http);
//    }
//}
