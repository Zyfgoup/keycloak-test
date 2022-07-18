package com.zyfgoup.adapter;

import com.zyfgoup.controller.ApiRespJsonObj;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Zyfgoup
 * @Date 2022/5/17 09:08
 * @Description
 **/
@FeignClient(name="KeyCloakTest",url = "http://localhost:11200",configuration = ServiceFeignConfiguration.class)
public interface AdapterService {


    @PostMapping("/mall/test")
    ApiRespJsonObj mallTest(@RequestHeader("Authorization") String token);


    @PostMapping("/test/data/auth/org")
    ApiRespJsonObj dataAuthOrg(@RequestHeader("Authorization") String token, @RequestParam("mallCode") String mallCode);

    @PostMapping("/test/data/auth/value")
    ApiRespJsonObj dataAuthValue(@RequestHeader("Authorization") String token, @RequestParam("value") String value);

    @PostMapping("/menu")
    ApiRespJsonObj testMenu(@RequestHeader("Authorization") String token);


    @PostMapping("/index")
    ApiRespJsonObj index(@RequestHeader("Authorization") String token);


}
