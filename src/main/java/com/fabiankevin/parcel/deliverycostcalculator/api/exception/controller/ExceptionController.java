package com.fabiankevin.parcel.deliverycostcalculator.api.exception.controller;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.exception.ApiExceptionResource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> apiException(RuntimeException e) {
        String message = StringUtils.isEmpty(e.getMessage()) ? "Oops, Something went wrong" : e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ApiExceptionResource.builder()
                        .message(message)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build()
        );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> formValidationException(MethodArgumentNotValidException e) {
        String message = "Please provide complete and valid form values.";
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        if (!errors.isEmpty()) {
            message = errors.get(0).getDefaultMessage();

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ApiExceptionResource.builder()
                        .message(message)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build()
        );
    }
}