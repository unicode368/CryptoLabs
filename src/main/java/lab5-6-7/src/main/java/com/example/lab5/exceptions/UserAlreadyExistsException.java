package com.example.lab5.exceptions;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException() {
        super();
    }
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
