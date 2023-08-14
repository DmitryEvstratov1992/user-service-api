package com.citizen.userserviceapi.exception;

import com.citizen.userserviceapi.model.dto.ExceptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

import static com.citizen.userserviceapi.model.ExceptionCodes.INVALID_PARAMS;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handler(MethodArgumentNotValidException exception) {
        ExceptionDto exceptionDto = new ExceptionDto()
                .setTime(ZonedDateTime.now())
                .setStatus(INVALID_PARAMS.getStatus())
                .setDescription(INVALID_PARAMS.getDescription());

        return ResponseEntity.status(INVALID_PARAMS.getStatus()).body(exceptionDto);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handler(BusinessException exception) {
        ExceptionDto exceptionDto = new ExceptionDto()
                .setTime(ZonedDateTime.now())
                .setStatus(exception.getStatus())
                .setDescription(exception.getMessage());

        return ResponseEntity.status(exception.getStatus()).body(exceptionDto);
    }

}
