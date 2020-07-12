package com.example.empresayego.Repository.Service;

import com.example.empresayego.Repository.Modelo.Gson.GsonProducto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductoService {

    @GET("/productoController/listaProductosEmpresa/{idEmpresa}")
    Call<GsonProducto> searchProductoById(@Path("idEmpresa")int idEmpresa);
}
