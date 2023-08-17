package com.citizen.userserviceapi.model;

import lombok.Getter;

@Getter
public enum ExceptionCodes {

    INVALID_PARAMS("Invalid request parameters", 400),
    USER_NOT_FOUND("Citizen not found", 442),
    DOG_NOT_FOUND("Dog not found", 443),
    USER_EXIST_WITH_EMAIL("User with email exist", 510);

    private final String description;
    private final Integer status;

    ExceptionCodes(String description, Integer status) {
        this.description = description;
        this.status = status;
    }
}
