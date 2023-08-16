package com.citizen.userserviceapi.config.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public Logger logger() {
        return new FeignClientLogger();
    }

}
