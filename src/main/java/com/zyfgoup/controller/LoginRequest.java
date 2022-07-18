package com.zyfgoup.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Zyfgoup
 * @Date 2022/5/23 13:45
 * @Description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    String username;

    String client_id;

    String client_secret;

    String grant_type;

}
