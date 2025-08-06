package com.paula.apiusuario.endpoints;

import com.paula.apiusuario.domain.Address;
import com.paula.apiusuario.domain.User;
import com.paula.apiusuario.endpoints.dtos.UserRequestDTO;
import com.paula.apiusuario.exceptions.*;
import com.paula.apiusuario.services.AddressService;
import com.paula.apiusuario.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController //Bean -> Uma anotação que indica que a classe é um controlador REST do Spring
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserRequestDTO userRequestDTO) {
        try{
            Address address = addressService.findAddress(userRequestDTO.getCep());
            addressService.addAddress(address);
            userService.addUser(userRequestDTO.toEntity(address));
        } catch (UserExistsValidationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()); //409 Conflict
        } catch (UserFieldsValidationException e) {
            return ResponseEntity.badRequest().body("Error adding user: " + e.getMessage()); //400 Bad Request
        } catch (AddressNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); //404 Not Found
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error adding user"); //400 Bad Request
        }

        return ResponseEntity.ok().body("User added successfully!");//200 OK
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
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = null;
        try {
            user = userService.getUserById(id);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build(); //404 Not Found
        }
        return ResponseEntity.ok(user); //200 OK
    }
    @GetMapping
    public ResponseEntity<?> getAllUsers () {

        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users); //200 OK
        }catch (UserNotFoundException e) {
            return ResponseEntity.noContent().build(); //204 No Content
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error retrieving users: " + e.getMessage()); //400 Bad Request
        }
    }

}
