package com.mimiperla.empresayego.Repository.Service;

import com.mimiperla.empresayego.Repository.Modelo.Gson.GsonProducto;
import com.mimiperla.empresayego.Repository.Modelo.Producto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductoService {


    @GET("/productoController/listaCategoria/{idcategoriaproducto}/{idempresa}")
    Call<GsonProducto> searchListProducto(@Header("Authorization")String auth, @Path("idcategoriaproducto")int idcategoriaproducto, @Path("idempresa")int idempresa);


    @GET("/productoController/listaProductosEmpresa/{idEmpresa}")
    Call<GsonProducto> searchProductoById(@Header("Authorization")String auth, @Path("idEmpresa")int idEmpresa);


    @POST("/productoController/updateDisponibilidad/{idProducto}/{idEmpresa}/{disponibilidad}")
    Call<Producto> updateStateProducto(@Header("Authorization")String auth, @Path("idProducto")int idProducto, @Path("idEmpresa")int idEmpresa, @Path("disponibilidad")boolean disponibilidad);


    @POST("/productoController/insertar")
    Call<Producto> insertProducto(@Header("Authorization")String auth, @Body Producto producto);

}
