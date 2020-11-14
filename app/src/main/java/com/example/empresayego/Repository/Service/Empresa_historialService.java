package com.example.empresayego.Repository.Service;

import com.example.empresayego.Repository.Modelo.Gson.Empresa_historialGson;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface Empresa_historialService {

    @GET("/EmpresaHistorialController/listaPedidosHistorial/{idEmpresa}")
    Call<Empresa_historialGson> searchEmpresaHistorialById(@Header("Authorization")String token, @Path("idEmpresa")int idEmpresa);

}
