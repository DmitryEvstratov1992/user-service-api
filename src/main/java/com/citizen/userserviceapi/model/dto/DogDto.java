package com.citizen.userserviceapi.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class DogDto {

    private Long id;
    private String name;
    private Long masterId;

}
