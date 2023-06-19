package com.mimiperla.empresayego.Repository.Service;

import com.mimiperla.empresayego.Repository.Modelo.Gson.GsonDelivery_Pedido;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Delivery_PedidoService {


    @POST("/delivery/v/lista/v/total")
    Call<GsonDelivery_Pedido> searchRepartidor(@Header("authorization") String auth,@Body GsonDelivery_Pedido pedido);


}
