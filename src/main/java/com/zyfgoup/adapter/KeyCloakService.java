package com.zyfgoup.adapter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @Author Zyfgoup
 * @Date 2022/5/19 09:24
 * @Description
 **/
@FeignClient(name="KeyCloakService",url = "http://47.102.192.4:8080",configuration = ServiceFeignConfiguration.class)
public interface KeyCloakService {


    @PostMapping(value = "/realms/SCPG/protocol/openid-connect/token",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    JSONObject getRPT(@RequestHeader("Authorization") String token, @RequestBody AuthTokenRequest request);


    @PostMapping(value = "/realms/SCPG/protocol/openid-connect/token/introspect",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    JSONObject instrspect( @RequestBody IntrospectRequest request);
}
