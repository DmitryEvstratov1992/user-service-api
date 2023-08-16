package com.citizen.userserviceapi.service;

import com.citizen.userserviceapi.exception.BusinessException;
import com.citizen.userserviceapi.integration.DogServiceApi;
import com.citizen.userserviceapi.model.dto.DogDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

import static com.citizen.userserviceapi.model.ExceptionCodes.INVALID_PARAMS;

@Slf4j
@RequiredArgsConstructor
@Service
public class DogServiceImpl implements DogService {

    private final DogServiceApi dogServiceApi;

    private void findDogById(Long id) {
        log.info("Start method findUserById id = {}", id);

        if (id == null) throw new BusinessException(INVALID_PARAMS);

        DogDto dog = dogServiceApi.findDogById(id);

        log.info("Found dog {} by id = {}", dog, id);
    }

    @Override
    public void checkDogById(Set<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            ids.forEach(this::findDogById);
        }
    }

}
