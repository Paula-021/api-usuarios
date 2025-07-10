package com.paula.apiusuario.services;

import com.paula.apiusuario.domain.User;
import com.paula.apiusuario.exceptions.UserEmailExistsException;
import com.paula.apiusuario.exceptions.UserExistsValidationException;
import com.paula.apiusuario.exceptions.UserFieldsValidationException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void addUser(User user) throws UserFieldsValidationException, UserExistsValidationException;

    void updateUser(User user) throws UserFieldsValidationException, UserEmailExistsException;

    void deleteUser(Long id);

    User getUserById(Long id);
}
