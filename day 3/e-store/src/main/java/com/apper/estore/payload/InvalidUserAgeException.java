package com.apper.estore.payload;

public class InvalidUserAgeException extends Exception {
    public InvalidUserAgeException(String errorMessage) {
        super(errorMessage);
    }
}
