package com.paula.apiusuario.endpoints.dtos;

public class UserResponseDTO {
    private String name;
    private String email;
    private AddressResponseDTO addressResponseDTO;

    public UserResponseDTO(String name, String email, AddressResponseDTO addressResponseDTO) {
        this.name = name;
        this.email = email;
        this.addressResponseDTO = addressResponseDTO;
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

    public AddressResponseDTO getAddressResponseDTO() {
        return addressResponseDTO;
    }

    public void setAddressResponseDTO(AddressResponseDTO addressResponseDTO) {
        this.addressResponseDTO = addressResponseDTO;
    }
}
