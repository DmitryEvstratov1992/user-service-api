package com.citizen.userserviceapi.integration;

import com.citizen.userserviceapi.exception.BusinessException;
import com.citizen.userserviceapi.model.dto.DogDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.citizen.userserviceapi.model.ExceptionCodes.DOG_NOT_FOUND;

@Profile("default")
@Slf4j
@RequiredArgsConstructor
@Service
public class DogServiceApiImpl implements DogServiceApi {

    public static final String SERVICE_NAME = "dog-service-api";

    private final DogServiceFeignApi dogServiceFeignApi;

    @Override
    public DogDto findDogById(Long id) {
        log.info("Request to {} to method findDogById with id = {}", SERVICE_NAME, id);

        ResponseEntity<DogDto> response = dogServiceFeignApi.findDogById(id);

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("Successful response from findDogById with id = {}", id);
            return response.getBody();
        }

        throw new BusinessException(DOG_NOT_FOUND);
    }

}
