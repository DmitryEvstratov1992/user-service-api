package com.citizen.userserviceapi.model;

import lombok.Getter;

@Getter
public enum ExceptionCodes {

    INVALID_PARAMS("Неверные параметры запроса", 500),
    USER_NOT_FOUND("Не найден гражданин", 502);

    private final String description;
    private final Integer status;

    ExceptionCodes(String description, Integer status) {
        this.description = description;
        this.status = status;
    }
}
