package com.vadinei.dm1.modulo3.model;

public class LogradouroModel {
    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String complement;
    private String ibge;

    public String getCep() {
        return cep;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public String getComplement() {
        return complement;
    }

    public String getIbge() {
        return ibge;
    }

    public String format() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CEP: ").append(cep).append("\n");
        sb.append("Logradouro: ").append(street).append("\n");
        sb.append("Bairro: ").append(neighborhood).append("\n");
        sb.append("Cidade: ").append(city).append("\n");
        sb.append("Estado: ").append(state).append("\n");
        sb.append("Código IBGE: ").append(ibge).append("\n");
        return sb.toString();
    }
}
