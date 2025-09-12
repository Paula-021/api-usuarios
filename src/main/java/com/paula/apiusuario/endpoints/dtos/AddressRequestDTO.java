package com.paula.apiusuario.endpoints.dtos;

public class AddressRequestDTO {
    private String logradouro;
    private String complemento;
    private String bairro;

    //se null => user nao digitou
    // "" => vazio

    public String getLogradouro() {
        if(logradouro == null){
            logradouro = "";
        }else{
            String logradouroTrim = logradouro.trim();
            if(logradouroTrim.isEmpty()){
                return "";
            }else{
                return logradouro; //seria realmente o nome de uma rua
            }
        }
        return logradouro; // ""
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        if(complemento == null){
            complemento = "";
        }else{
            String complementoTrim = complemento.trim();
            if(complementoTrim.isEmpty()){
                return "";
            }else{
                return complemento; //seria realmente um complemento
            }
        }
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        if(bairro == null){
            bairro = "";
        }else {
            String bairroTrim = bairro.trim();
            if (bairroTrim.isEmpty()) {
                return "";
            } else {
                return bairro; //seria realmente o nome de um bairro
            }
        }
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
