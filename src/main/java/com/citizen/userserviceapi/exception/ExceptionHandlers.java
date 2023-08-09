package com.citizen.userserviceapi.exception;

import com.citizen.userserviceapi.model.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

import static com.citizen.userserviceapi.model.ExceptionCodes.INVALID_PARAMS;
import static com.citizen.userserviceapi.model.ExceptionCodes.USER_NOT_FOUND;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handler(MethodArgumentNotValidException exception) {
        return ResponseEntity.ok(
                new ExceptionDto(
                        ZonedDateTime.now(),
                        INVALID_PARAMS.getStatus(),
                        INVALID_PARAMS.getDescription()
                )
        );
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handler(BusinessException exception) {
        return ResponseEntity.ok(
                new ExceptionDto(
                        ZonedDateTime.now(),
                        exception.getStatus(),
                        exception.getMessage()
                )
        );
    }

}
