package com.vadinei.dm1.modulo3.api;

import com.vadinei.dm1.modulo3.model.EmpresaModel;
import com.vadinei.dm1.modulo3.model.LogradouroModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InverTextoApi {
    @GET("/v1/cep/{numeroCep}")
    Call<LogradouroModel> getLogradouro(
            @Path("numeroCep") String numeroCep,
            @Query("token") String token
    );

    @GET("/v1/cnpj/{numeroCnpj}")
    Call<EmpresaModel> getEmpresa(
            @Path("numeroCnpj") String numeroCnpj,
            @Query("token") String token
    );
}
