package com.paula.apiusuario.services;

import com.paula.apiusuario.domain.Address;
import org.springframework.web.client.RestTemplate;

public class AddressServiceImpl implements AddressService {


    @Override
    public Address findAddress(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://viacep.com.br/ws/" + cep + "/json";
        Address address = restTemplate.getForObject(url, Address.class); // Jackson converte o JSON para o objeto Address
        return address;
    }

    @Override
    public void addAddress(Address address) {

    }
}
