package com.paula.apiusuario.services;

import com.paula.apiusuario.domain.Address;

public interface AddressService {
    Address findAddress(String cep);

    void addAddress(Address address);
}
