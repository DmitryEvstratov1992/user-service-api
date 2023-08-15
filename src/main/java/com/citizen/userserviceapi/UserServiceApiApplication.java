package com.citizen.userserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class UserServiceApiApplication {

    public static void main(String[] args) {
        try{
            SpringApplication.run(UserServiceApiApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
