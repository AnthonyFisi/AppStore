package com.example.empresayego.Repository.Service;

import com.example.empresayego.Repository.Modelo.Repartidor;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepartidorService {

    @GET("/repartidorController/findByRepartidor/{idUsuario}")
    Call<Repartidor> searchUbicacionById(@Path("idUsuario")int idUsuario);
}
