package com.zyfgoup.adapter;

import lombok.Data;

/**
 * @Author Zyfgoup
 * @Date 2022/5/19 10:15
 * @Description
 **/
@Data
public class IntrospectRequest {
    private String token;

    private String client_secret;

    private String client_id;

    private String token_type_hint;
}
