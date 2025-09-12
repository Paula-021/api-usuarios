package com.paula.apiusuario.services;

import com.paula.apiusuario.domain.Address;
import com.paula.apiusuario.endpoints.dtos.AddressRequestDTO;
import com.paula.apiusuario.exceptions.AddressNotFoundException;

public interface AddressService {
    Address findAddress(String cep) throws AddressNotFoundException;

    void addAddress(Address address);

    void updateAddress(Address address);

    void updateAddressComplementary(Long id, AddressRequestDTO addressRequestDTO) throws AddressNotFoundException;
}
