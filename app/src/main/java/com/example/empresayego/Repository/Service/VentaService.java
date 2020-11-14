package com.example.empresayego.Repository.Service;

import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.example.empresayego.Repository.Modelo.Venta;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface VentaService {

    @POST("/VentaController/updateTiempo/{idVenta}/{tiempo}")
    Call<Venta> updateEstadoVentaTiempo(@Header("Authorization")String auth, @Path("idVenta")int idVenta, @Path("tiempo")int tiempo);

    @POST("/VentaController/updateCosto/{idVenta}/{costoTotal}")
    Call<Venta> updateEstadoVentaCosto(@Header("Authorization")String auth, @Path("idVenta")int idVenta,@Path("costoTotal")float costoTotal);

    @POST("/VentaController/cancelar")
    Call<Venta> updateEstadoVentaCancelar(@Header("Authorization")String auth, @Body Venta venta);

}
