package com.paula.apiusuario.endpoints.dtos;

import com.paula.apiusuario.domain.Address;
import com.paula.apiusuario.domain.User;

public class UserRequestDTO {
    private String name;
    private String email;
    private String cep;

    public User toEntity(Address address) {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setAddress(address);

        return user;
    }
    public User toEntity() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);

        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
