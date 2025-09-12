package com.paula.apiusuario.endpoints.dtos;

import com.paula.apiusuario.domain.Address;

public class AddressResponseDTO {

    private Long id;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    public AddressResponseDTO(Address address) {
        this.id = address.getId();
        this.logradouro = address.getLogradouro();
        this.complemento = address.getComplemento();
        this.bairro = address.getBairro();
        this.localidade = address.getLocalidade();
        this.uf = address.getUf();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
