package com.paula.apiusuario.exceptions;

public class UserExistsValidationException extends Exception {
    public UserExistsValidationException(String message) {
        super(message);
    }
}
