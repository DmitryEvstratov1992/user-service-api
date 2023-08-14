package com.citizen.userserviceapi.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateDto {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

}
