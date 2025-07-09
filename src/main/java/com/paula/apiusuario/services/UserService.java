package com.paula.apiusuario.services;

import com.paula.apiusuario.domain.User;
import com.paula.apiusuario.exceptions.ExistsUserValidationException;
import com.paula.apiusuario.exceptions.UserFieldsValidationException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void addUser(User user) throws UserFieldsValidationException, ExistsUserValidationException;
}
