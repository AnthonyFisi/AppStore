package com.mimiperla.empresayego.Repository.Service;

import com.mimiperla.empresayego.Repository.Modelo.Gson.GsonEmpresa_historialDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface Empresa_historialDetailService {


    @GET("/EmpresaHistorialController/Detail/{idEmpresa}/{idPedido}/{idRepartidor}")
    Call<GsonEmpresa_historialDetail> searchEmpresaHistorialDetailById(
            @Header("Authorization")String token,
            @Path("idEmpresa")int idEmpresa,
            @Path("idPedido")int idPedido,
            @Path("idRepartidor")int idRepartidor);
}
