package com.citizen.userserviceapi.model.dto;

import java.time.ZonedDateTime;

public record ExceptionDto(
        ZonedDateTime time,
        Integer status,
        String description
) {
}
