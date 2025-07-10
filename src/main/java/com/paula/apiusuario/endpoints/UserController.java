package com.paula.apiusuario.endpoints;

import com.paula.apiusuario.domain.User;
import com.paula.apiusuario.exceptions.UserEmailExistsException;
import com.paula.apiusuario.exceptions.UserExistsValidationException;
import com.paula.apiusuario.exceptions.UserFieldsValidationException;
import com.paula.apiusuario.exceptions.UserNotFoundException;
import com.paula.apiusuario.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        } catch (UserExistsValidationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()); //409 Conflict
        } catch (UserFieldsValidationException e) {
            return ResponseEntity.badRequest().body("Error adding user: " + e.getMessage()); //400 Bad Request
        }

        return ResponseEntity.created(URI.create("/users/" + user.getId())).body("User added successfully!");//201 Created
    }
    //testar
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        try {
            userService.updateUser(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build(); //404 Not Found
        } catch (UserEmailExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()); //409 Conflict
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating user: " + e.getMessage()); //400 Bad Request
        }
        return ResponseEntity.ok("User updated successfully!"); //200 OK
    }
    //testar métodos

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build(); //404 Not Found
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting user: " + e.getMessage()); //400 Bad Request
        }
        return ResponseEntity.ok("User deleted successfully!"); //200 OK
    }

}
