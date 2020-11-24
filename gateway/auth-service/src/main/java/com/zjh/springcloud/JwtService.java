package com.zjh.springcloud;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zhaojh
 * @date 2020/11/24 16:30
 * @description
 */
@Service
@Slf4j
public class JwtService {

    //  生产环境是不能这么写的
    private static final String key = "changeIt";
    private static final String ISSUER = "zhaojh";
    private static final long TOKEN_EXP_TIME = 60000;
    private static final String USERNAME = "username";

    /**
     * 生成token
     * @param account
     * @return
     */
    public String token(Account account) {
        Date now = new Date();
        Algorithm algorithm = Algorithm.HMAC256(key);
        String token = JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(new Date(now.getTime() + TOKEN_EXP_TIME))
                //  withClaim可以添加自定义属性值
                .withClaim(USERNAME, account.getUsername())
                .sign(algorithm);
        log.info("jwt generated user={}", account.getUsername());
        return token;
    }

    /**
     * 校验token
     * @param token
     * @param username
     * @return
     */
    public boolean verify(String token, String username) {
        Algorithm algorithm = Algorithm.HMAC256(key);
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .withClaim(USERNAME, username)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("auth failed ", e);
            return false;
        }
    }
}
