package com.citizen.userserviceapi.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Accessors(chain = true)
@Data
public class UserUpdateDto {
    @NotNull
    private Long id;
    private String firstName;
    private String lastName;
    private Set<Long> dogIds;
    private Long organizationId;
}
