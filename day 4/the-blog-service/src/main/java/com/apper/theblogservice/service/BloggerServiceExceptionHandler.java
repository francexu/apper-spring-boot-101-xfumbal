package com.apper.theblogservice.service;

import com.apper.theblogservice.exception.EmailIsRegisteredException;
import com.apper.theblogservice.exception.InputIsRequiredException;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BloggerServiceExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InputIsRequiredException.class)
    @ResponseBody
    public InputIsRequiredException handleInputIsRequiredException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(objectError -> new InputIsRequiredException(objectError.getDefaultMessage()))
                .orElse(new InputIsRequiredException("Unknown invalid argument encountered"));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailIsRegisteredException.class)
    @ResponseBody
    public EmailIsRegisteredException handleEmailIsRegisteredException(EmailIsRegisteredException ex) {
        return new EmailIsRegisteredException(ex.getMessage());
    }
}
