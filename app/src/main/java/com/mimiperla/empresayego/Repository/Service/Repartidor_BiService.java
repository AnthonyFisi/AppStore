package com.mimiperla.empresayego.Repository.Service;

import com.mimiperla.empresayego.Repository.Modelo.Gson.GsonRepartidor_Bi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface Repartidor_BiService {

    @GET("/RepartidorBiController/lista/{idempresa}")
    Call<GsonRepartidor_Bi> listaRepartidores(@Header("Authorization")String auth, @Path("idempresa")int idempresa);
}
