package com.citizen.userserviceapi.exception;

import com.citizen.userserviceapi.model.ExceptionCodes;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final Integer status;

    public BusinessException(ExceptionCodes exceptionCode) {
        super(exceptionCode.getDescription());
        this.status = exceptionCode.getStatus();
    }
}
