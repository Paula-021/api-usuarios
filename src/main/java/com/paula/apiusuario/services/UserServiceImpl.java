package com.paula.apiusuario.services;

import com.paula.apiusuario.domain.User;
import com.paula.apiusuario.exceptions.UserEmailExistsException;
import com.paula.apiusuario.exceptions.UserExistsValidationException;
import com.paula.apiusuario.exceptions.UserFieldsValidationException;
import com.paula.apiusuario.exceptions.UserNotFoundException;
import com.paula.apiusuario.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) throws UserFieldsValidationException, UserExistsValidationException {
    if(user.getName() == null || user.getName().isEmpty() ||
        user.getEmail() == null || user.getEmail().isEmpty()) {
        throw new UserFieldsValidationException("Some field is empty.");

    }
    Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
    if (existingUser.isPresent()) {
        throw new UserExistsValidationException("User already added with email: " + user.getEmail());
    }

    userRepository.save(user);
    }

    @Override
    public void updateUser(User user) throws UserFieldsValidationException, UserEmailExistsException {
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found with ID: " + user.getId());
        }
        if(user.getName() == null || user.getName().isEmpty() ||
           user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new UserFieldsValidationException("Some field is empty.");
        }
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser.isPresent()) {
            if(!existingUser.get().getId().equals(user.getId())) {
                //Se o usuario já existe e não é o mesmo que está sendo atualizado, lança a exceção.
                   throw new UserEmailExistsException("User already exists with email: " + user.getEmail());

                //o id usuario que existe tem que ser o mesmo do usuário que está sendo editado.
                //Se for o mesmo usuário editando seus dados → tudo bem.
                //Se for outro usuário tentando usar um e-mail que já existe → erro (ids diferentes).

            }
        }
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UserNotFoundException("User not found with ID: " + id);

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found.");
        }
        return users;
    }


}
