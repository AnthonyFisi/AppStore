package com.mimiperla.empresayego.Repository.Service;

import com.mimiperla.empresayego.Repository.Modelo.Orden_estado_restaurante;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Orden_estado_restauranteService {

    @POST("/Orden_Estado_RestauranteController/registrarOrden/{tiempo_espera}/{idUsuario}/{horario}/{fechaentrega}")
    Call<Orden_estado_restaurante> updateEstado(@Header("Authorization")String auth,
                                                @Body Orden_estado_restaurante estado,
                                                @Path("tiempo_espera")String tiempo_espera,
                                                @Path("idUsuario")int idUsuario,
                                                @Path ("horario") String horario,
                                                @Path ("fechaentrega") String fechaentrega);




    @POST("/Orden_Estado_RestauranteController/updateOrdenProces/{idUsuario}")
    Call<Orden_estado_restaurante> updateEstadoProces(@Header("Authorization")String auth,
                                                      @Body Orden_estado_restaurante estado,
                                                      @Path("idUsuario")int idUsuario);

    @POST("/Orden_Estado_RestauranteController/updateOrdenProcesAlternative/{idUsuario}/{idrepartidor}")
    Call<Orden_estado_restaurante> updateEstadoProcesMarketPlace(@Header("Authorization")String auth,
                                                      @Body Orden_estado_restaurante estado,
                                                      @Path("idUsuario")int idUsuario,
                                                                 @Path("idrepartidor")int idrepartidor);


    @POST("/Orden_Estado_RestauranteController/updateOrdenReady/{idUsuario}")
    Call<Orden_estado_restaurante> updateEstadoReady(@Header("Authorization")String auth, @Body Orden_estado_restaurante estado,@Path("idUsuario")int idUsuario);

    @POST("/Orden_Estado_RestauranteController/cancelarOrden/{idUsuario}")
    Call<Orden_estado_restaurante> updateEstadoCancelar(@Header("Authorization")String auth, @Body Orden_estado_restaurante estado,@Path("idUsuario")int idUsuario);
}
