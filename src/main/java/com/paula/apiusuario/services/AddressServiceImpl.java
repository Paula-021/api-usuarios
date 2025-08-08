package com.paula.apiusuario.services;

import com.paula.apiusuario.domain.Address;
import com.paula.apiusuario.exceptions.AddressNotFoundException;
import com.paula.apiusuario.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address findAddress(String cep) throws AddressNotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://viacep.com.br/ws/" + cep + "/json";
        Address address = null;
        try{
            address = restTemplate.getForObject(url, Address.class); // Verifica se o CEP é válido
        } catch (Exception e) {
            throw new AddressNotFoundException("CEP inválido ou não encontrado: " + cep);
        }
        System.out.println("Endereço encontrado: " + address);
        return address;
    }

    @Override
    public void addAddress(Address address) {
        addressRepository.save(address);
    }
}
