package com.citizen.userserviceapi.integration.mock;

import com.citizen.userserviceapi.integration.DogServiceApi;
import com.citizen.userserviceapi.model.dto.DogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import static com.citizen.userserviceapi.integration.DogServiceApiImpl.SERVICE_NAME;

@Profile(value = {"mock-integration", "test"})
@Slf4j
@Service
public class DogServiceApiMockImpl implements DogServiceApi {

    @Override
    public DogDto findDogById(Long id) {
        log.info("Request to {} mock to method findDogById with id = {}", SERVICE_NAME, id);

        return new DogDto()
                .setId(100L)
                .setName("Kex")
                .setMasterId(1L);
    }
}
