package com.paula.apiusuario.endpoints;

import com.paula.apiusuario.domain.Address;
import com.paula.apiusuario.endpoints.dtos.AddressRequestDTO;
import com.paula.apiusuario.endpoints.dtos.CepDTO;
import com.paula.apiusuario.exceptions.AddressNotFoundException;
import com.paula.apiusuario.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id, @RequestBody CepDTO cep) {
        //buscar o cep na viacep
        try {
            Address address = addressService.findAddress(cep.getCep());
            System.out.println(id);
            address.setId(id);
            addressService.updateAddress(address);
            return ResponseEntity.ok().body("Address updated successfully!");
        } catch (AddressNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

    }
    @PutMapping("/complementary/{id}")
    public ResponseEntity<?> updateAddressComplementary(@PathVariable Long id, @RequestBody AddressRequestDTO addressRequestDTO) {
        try {
            addressService.updateAddressComplementary(id, addressRequestDTO);
            return ResponseEntity.ok().body("Address updated successfully!");
        } catch (AddressNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }
}
