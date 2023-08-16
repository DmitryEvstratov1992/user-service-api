package com.citizen.userserviceapi.integration;

import com.citizen.userserviceapi.model.dto.DogDto;

public interface DogServiceApi {

    DogDto findDogById(Long id);

}
