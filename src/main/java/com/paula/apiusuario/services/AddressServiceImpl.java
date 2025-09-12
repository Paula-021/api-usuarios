package com.paula.apiusuario.services;

import com.paula.apiusuario.domain.Address;
import com.paula.apiusuario.endpoints.dtos.AddressRequestDTO;
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

    @Override
    public void updateAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void updateAddressComplementary(Long id, AddressRequestDTO addressRequestDTO) throws AddressNotFoundException {
        //Address ids: 1, 2, 3, 4, 5
        //busca o id do endereço informado e valida se ele existe
        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException("Address not found with ID: " + id));
        //validar se o usuário digitou informações para os campos complementares
        //atualiza os campos complementares do endereço
        if(!addressRequestDTO.getLogradouro().isEmpty()){
            address.setLogradouro(addressRequestDTO.getLogradouro());
        }
        if(!addressRequestDTO.getComplemento().isEmpty()){
            address.setComplemento(addressRequestDTO.getComplemento());
        }
        if(!addressRequestDTO.getBairro().isEmpty()){
            address.setBairro(addressRequestDTO.getBairro());
        }


        addressRepository.save(address);
    }

}
