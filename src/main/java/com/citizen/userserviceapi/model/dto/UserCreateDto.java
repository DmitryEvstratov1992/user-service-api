package com.citizen.userserviceapi.model.dto;

import jakarta.validation.constraints.NotBlank;

public record UserCreateDto(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName
) {

}
