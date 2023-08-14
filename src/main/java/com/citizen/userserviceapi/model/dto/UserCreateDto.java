package com.citizen.userserviceapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Accessors(chain = true)
@Data
public class UserCreateDto {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

}
