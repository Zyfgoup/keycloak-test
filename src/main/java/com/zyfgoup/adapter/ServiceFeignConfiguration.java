package com.zyfgoup.adapter;

import feign.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName ServiceFeignConfiguration
 * @Description feign调用配置类
 * @Author 邱胜
 * @Date 2020/5/15
 */
public class ServiceFeignConfiguration {
    @Value("${service.feign.connectTimeout:60000}")
    private int connectTimeout;

    @Value("${service.feign.readTimeOut:60000}")
    private int readTimeout;

    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeout, readTimeout);
    }
}
