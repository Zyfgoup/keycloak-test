package com.zyfgoup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author Zyfgoup
 * @Date 2022/5/16 20:46
 * @Description
 **/

@SpringBootApplication
@EnableFeignClients
public class KeyCloakTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeyCloakTestApplication.class, args);
    }
}
