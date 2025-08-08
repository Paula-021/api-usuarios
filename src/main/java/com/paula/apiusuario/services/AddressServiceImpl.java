package com.paula.apiusuario.services;

import com.paula.apiusuario.domain.Address;
import com.paula.apiusuario.exceptions.AddressNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressServiceImpl implements AddressService {


    @Override
    public Address findAddress(String cep) throws AddressNotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://viacep.com.br/ws/" + cep + "/json";
        Address address = restTemplate.getForObject(url, Address.class); // Jackson converte o JSON para o objeto Address
        if(address == null || address.getCep() == null || address.getCep().isEmpty()) {
            throw new AddressNotFoundException("Endereço não encontrado para o CEP: " + cep);
        }
        return address;
    }

    @Override
    public void addAddress(Address address) {

    }
}
