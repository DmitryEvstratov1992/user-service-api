package com.citizen.userserviceapi.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Schema(description = "DTO for creating user")
@Accessors(chain = true)
@Data
public class UserCreateDto {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

}
