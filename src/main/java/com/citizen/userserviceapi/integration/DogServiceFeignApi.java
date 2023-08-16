package com.citizen.userserviceapi.integration;

import com.citizen.userserviceapi.config.feign.FeignClientConfig;
import com.citizen.userserviceapi.model.dto.DogDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.citizen.userserviceapi.controller.UserController.URL_ID;

@FeignClient(name = "dog-service", url = "${client.dog-service-api-url}", configuration = FeignClientConfig.class)
public interface DogServiceFeignApi {

    @GetMapping(URL_ID)
    ResponseEntity<DogDto> findDogById(@PathVariable("id") Long id);
}
