package com.zyfgoup.adapter;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AuthTokenRequest {
    private String token;

    private String client_secret;

    private String client_id;

    private String grant_type;

    private String permission;

    private String audience;

}