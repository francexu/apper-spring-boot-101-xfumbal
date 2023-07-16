package com.apper.theblogservice.exception;

public class EmailIsRegisteredException extends Exception{
    public EmailIsRegisteredException(String message) {
        super(message);
    }
}
