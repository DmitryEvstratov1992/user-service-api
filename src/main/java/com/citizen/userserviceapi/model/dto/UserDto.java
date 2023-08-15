package com.citizen.userserviceapi.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

@Schema(description = "DTO for user")
@Accessors(chain = true)
@Data
public class UserDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Set<Long> dogIds;
    private Long organizationId;
}