package com.zjh.springcloud;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaojh
 * @date 2020/11/24 15:51
 * @description
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String code;
    private Account account;
}
