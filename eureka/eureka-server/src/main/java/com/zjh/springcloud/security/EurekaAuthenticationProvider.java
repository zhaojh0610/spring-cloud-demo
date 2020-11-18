package com.zjh.springcloud.security;

import com.zjh.springcloud.utils.EncryptUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
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

        /**
         * 密码校验
         */
        if (StringUtils.isBlank(password)) {
            throw new BadCredentialsException("AuthenticateFail");
        }
        /**
         * 传过的密码对其解密，如果报错，则认为是传入的不对或是铭文密码
         */
        try {
            password = EncryptUtils.aesDecrypt(password, key);
        } catch (Exception exception) {

        }
        UserDetails userDetails = eurekaUserDetailService.loadUserByUsername(userName);
        /**
         * 对配置端的密文进行解密，然后在比较两端的密码是否匹配
         */
        String suerDetailsPassword = "";
        try {
            suerDetailsPassword = EncryptUtils.aesDecrypt(userDetails.getPassword(), key);
        } catch (Exception exception) {
            suerDetailsPassword = userDetails.getPassword();
        }
        if (!userName.equals(userDetails.getUsername()) || !password.equals(suerDetailsPassword)) {
            throw new BadCredentialsException("AuthenticateFail");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
