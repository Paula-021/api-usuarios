package com.paula.apiusuario.exceptions;

public class UserEmailExistsException extends Exception {
    public UserEmailExistsException(String message) {
        super(message);
    }
}
