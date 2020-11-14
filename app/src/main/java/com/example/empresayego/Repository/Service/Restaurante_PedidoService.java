package com.example.empresayego.Repository.Service;

import com.example.empresayego.Repository.Modelo.Gson.GsonRestaurante_Pedido;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface Restaurante_PedidoService {

    @GET("/RestaurantePedido/listaPedidosDistinct/{idEmpresa}")
    Call<GsonRestaurante_Pedido> searchRestaurantePedidoByEmpresa(@Header("Authorization")String auth, @Path("idEmpresa")int idEmpresa);

    @GET("/RestaurantePedido/listaPedidosProces/{idEmpresa}")
    Call<GsonRestaurante_Pedido> searchRestaurantePedidoByEmpresaProces(@Header("Authorization")String auth, @Path("idEmpresa")int idEmpresa);

    @GET("/RestaurantePedido/listaPedidosReady/{idEmpresa}")
    Call<GsonRestaurante_Pedido> searchRestaurantePedidoByEmpresaReady(@Header("Authorization")String auth, @Path("idEmpresa")int idEmpresa);

    @GET("/RestaurantePedido/listaPedidosHistorial/{idEmpresa}")
    Call<GsonRestaurante_Pedido> searchRestaurantePedidoByEmpresaHistorial(@Header("Authorization")String auth, @Path("idEmpresa")int idEmpresa);
}
