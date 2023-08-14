package com.citizen.userserviceapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionDto {

    private ZonedDateTime time;
    private Integer status;
    private String description;

}
