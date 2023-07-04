package com.apper.estore;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ServiceError handleInvalidInputFields(MethodArgumentNotValidException ex) {
        // kinukuha yung pinaka-unang error
        return ex.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(objectError -> new ServiceError(objectError.getDefaultMessage()))
                .orElse(new ServiceError("Unknown invalid argument encountered"));
    }

    @ExceptionHandler(InvalidUserAgeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ServiceError handleInvalidUserAge(InvalidUserAgeException e) {
        return new ServiceError(e.getMessage());
    }

}
