package com.citizen.userserviceapi.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class UserUpdateDto {
    @NotNull
    private Long id;
    private String firstName;
    private String lastName;
    private Set<Long> dogIds;
    private Long organizationId;
}
