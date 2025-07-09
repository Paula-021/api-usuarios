package com.paula.apiusuario.endpoints;

import com.paula.apiusuario.domain.User;
import com.paula.apiusuario.exceptions.ExistsUserValidationException;
import com.paula.apiusuario.exceptions.UserFieldsValidationException;
import com.paula.apiusuario.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController //Bean -> Uma anotação que indica que a classe é um controlador REST do Spring
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try{
            userService.addUser(user);
        } catch (ExistsUserValidationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()); //409 Conflict
        } catch (UserFieldsValidationException e) {
            return ResponseEntity.badRequest().body("Error adding user: " + e.getMessage()); //400 Bad Request
        }

        return ResponseEntity.created(URI.create("/users/" + user.getId())).body("User added successfully!");//201 Created
    }
}
