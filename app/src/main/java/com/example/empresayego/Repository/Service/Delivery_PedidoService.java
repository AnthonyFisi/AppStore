package com.example.empresayego.Repository.Service;

import com.example.empresayego.Repository.Modelo.Gson.GsonDelivery_Pedido;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Delivery_PedidoService {


    @POST("/delivery/v/lista/v/total")
    Call<GsonDelivery_Pedido> searchRepartidor(@Header("authorization") String auth,@Body GsonDelivery_Pedido pedido);


}
