package com.Ishop.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableFeignClients
@EnableResourceServer
public class IshopStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(IshopStoreApplication.class,args);
    }
}