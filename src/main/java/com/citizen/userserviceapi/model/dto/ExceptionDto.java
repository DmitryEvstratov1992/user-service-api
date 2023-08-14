package com.citizen.userserviceapi.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;

@Accessors(chain = true)
@Data
public class ExceptionDto {

    private ZonedDateTime time;
    private Integer status;
    private String description;

}
