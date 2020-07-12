package com.example.empresayego.Repository.Service;

import com.example.empresayego.Repository.Modelo.Orden_estado_restaurante;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Orden_estado_restauranteService {

    @POST("/Orden_Estado_RestauranteController/registrarOrden/{tiempo_espera}/{idUsuario}")
    Call<Orden_estado_restaurante> updateEstado(@Body Orden_estado_restaurante estado,@Path("tiempo_espera")String tiempo_espera,@Path("idUsuario")int idUsuario);


    @POST("/Orden_Estado_RestauranteController/updateOrdenProces/{idUsuario}")
    Call<Orden_estado_restaurante> updateEstadoProces(@Body Orden_estado_restaurante estado,@Path("idUsuario")int idUsuario);

    @POST("/Orden_Estado_RestauranteController/updateOrdenReady/{idUsuario}")
    Call<Orden_estado_restaurante> updateEstadoReady(@Body Orden_estado_restaurante estado,@Path("idUsuario")int idUsuario);
}
