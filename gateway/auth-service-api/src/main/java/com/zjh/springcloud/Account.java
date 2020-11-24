package com.zjh.springcloud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaojh
 * @date 2020/11/24 15:43
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account{

    private String username;

    private String token;

    private String refreshToken;

}
