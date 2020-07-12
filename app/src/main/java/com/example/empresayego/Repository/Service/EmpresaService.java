package com.example.empresayego.Repository.Service;

import com.example.empresayego.Repository.Modelo.Empresa;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmpresaService {

    @GET("/EmpresaController/findByEmpresa/{idEmpresa}")
    Call<Empresa> searchEmpresaById(@Path("idEmpresa")int idEmpresa);


    @POST("/EmpresaController/updateDisponibilidad/{idEmpresa}/{disponibilidad}")
    Call<Empresa> updateEmpresaState(@Path("idEmpresa")int idEmpresa,@Path("disponibilidad") boolean disponibilidad);

}
