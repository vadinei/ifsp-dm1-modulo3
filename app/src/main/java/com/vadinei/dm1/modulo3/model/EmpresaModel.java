package com.vadinei.dm1.modulo3.model;

import com.google.gson.annotations.SerializedName;

public class EmpresaModel {
    private String cnpj;
    @SerializedName("razao_social")
    private String razaoSocial;
    @SerializedName("nome_fantasia")
    private String nomeFantasia;
    @SerializedName("data_inicio")
    private String dataInicio;

    public String getCnpj() {
        return cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public String format() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CNPJ: ").append(cnpj).append("\n");
        sb.append("Razão Social: ").append(razaoSocial).append("\n");
        sb.append("Nome Fantasia: ").append(nomeFantasia).append("\n");
        sb.append("Data de Início: ").append(dataInicio);
        return sb.toString();
    }
}
