package com.citizen.userserviceapi.config.swagger;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    private Contact contact;
    private Info info;
    private Map<String, Server> servers;

}
