package com.apper.estore;

public class InvalidUserAgeException extends Exception {
    public InvalidUserAgeException(String errorMessage) {
        super(errorMessage);
    }
}
