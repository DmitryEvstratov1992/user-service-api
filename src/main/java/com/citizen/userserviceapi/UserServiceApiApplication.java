package com.citizen.userserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableFeignClients
@EnableCaching
@SpringBootApplication
public class UserServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApiApplication.class, args);
    }

}
