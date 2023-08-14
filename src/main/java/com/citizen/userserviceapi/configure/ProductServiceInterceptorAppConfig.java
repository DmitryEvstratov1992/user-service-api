package com.citizen.userserviceapi.configure;

import com.citizen.userserviceapi.interceptor.LoggerControllerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@RequiredArgsConstructor
public class ProductServiceInterceptorAppConfig extends WebMvcConfigurationSupport {

    private final LoggerControllerInterceptor loggerControllerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggerControllerInterceptor);
    }
}