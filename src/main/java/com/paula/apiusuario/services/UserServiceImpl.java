package com.paula.apiusuario.services;

import com.paula.apiusuario.domain.User;
import com.paula.apiusuario.exceptions.ExistsUserValidationException;
import com.paula.apiusuario.exceptions.UserFieldsValidationException;
import com.paula.apiusuario.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository usuerRepository;

    @Override
    public void addUser(User user) throws UserFieldsValidationException, ExistsUserValidationException {
    if(user.getName() == null || user.getName().isEmpty() ||
       user.getAddress() == null || user.getEmail() == null || user.getEmail().isEmpty()) {
        throw new UserFieldsValidationException("Some field is empty.");

    }
    Optional<User> existingUser = usuerRepository.findByEmail(user.getEmail());
    if (existingUser.isPresent()) {
        throw new ExistsUserValidationException("User already added with email: " + user.getEmail());
    }

    usuerRepository.save(user);
    }
}
