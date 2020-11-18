package com.zjh.springcloud.security;

import com.alibaba.fastjson.JSONObject;
import com.zjh.springcloud.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * @author zhaojh
 * @date 2020/11/18 14:03
 */
@Component
public class EurekaAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private EurekaUserDetailService eurekaUserDetailService;

    @Value("${encrypt.key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        /**
         * sessionId == null 表示通过eureka客户端注册认证
         * session ！= null 表示通过浏览器登录认证
         */
        String sessionId = details.getSessionId();
        if (sessionId != null) {
            try {
                password = EncryptUtils.aesEncrypt(password, key);
            } catch (Exception exception) {

            }
        }

        UserDetails userDetails = eurekaUserDetailService.loadUserByUsername(userName);

        if (!userName.equals(userDetails.getUsername()) || !password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("AuthenticateFail");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        String json = JSONObject.toJSONString(authentication);
        System.out.println(" json--->" + json);
        return true;
    }
}
