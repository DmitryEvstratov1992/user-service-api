package com.citizen.userserviceapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {

    @Primary
    @Bean
    public OpenAPI customOpenAPI(SwaggerProperties properties) {

        Contact contact = new Contact();
        contact.setEmail(properties.getContact().getEmail());
        contact.setName(properties.getContact().getName());

        return new OpenAPI()
                .info(properties.getInfo()
                        .contact(contact)
                        .title(properties.getInfo().getTitle())
                        .version(properties.getInfo().getVersion())
                        .description(properties.getInfo().getDescription()))
                .servers(new ArrayList<>(properties.getServers().values()));

    }

}
