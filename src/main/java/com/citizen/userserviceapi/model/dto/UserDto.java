package com.citizen.userserviceapi.model.dto;

import java.util.Set;

public record UserDto(
        Long id,
        String firstName,
        String lastName,
        Set<Long> dogIds,
        Long organizationId
) {

}