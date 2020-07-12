package com.example.empresayego.Repository.Repositorio;

import com.example.empresayego.Repository.Modelo.Orden_estado_restaurante;
import com.example.empresayego.Repository.Service.Orden_estado_restauranteService;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

public class Orden_estado_restauranteRepository {


    private static final String ESTADO_ORDEN_SERVICE_URL_BASE="https://backend-tiend-go.herokuapp.com/";

    private Orden_estado_restauranteService orden_estado_restauranteService;

    private MutableLiveData<Orden_estado_restaurante> gsonOrden_estado_restauranteMutableLiveData;

    public Orden_estado_restauranteRepository(){
        gsonOrden_estado_restauranteMutableLiveData=new MutableLiveData<>();
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(ESTADO_ORDEN_SERVICE_URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        orden_estado_restauranteService = retrofit.create(Orden_estado_restauranteService.class);
    }


    public void  updateEstado(Orden_estado_restaurante estado,String tiempo_espera,int idUsuario){
        orden_estado_restauranteService.updateEstado(estado,tiempo_espera,idUsuario).enqueue(new Callback<Orden_estado_restaurante>() {
            @Override
            public void onResponse(Call<Orden_estado_restaurante> call, Response<Orden_estado_restaurante> response) {
                if(response.body()!=null && response.code()==200){

                    gsonOrden_estado_restauranteMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Orden_estado_restaurante> call, Throwable t) {

            }
        });
    }




    public void  updateEstadoProces(Orden_estado_restaurante estado,int idUsuario){
        orden_estado_restauranteService.updateEstadoProces(estado,idUsuario).enqueue(new Callback<Orden_estado_restaurante>() {
            @Override
            public void onResponse(Call<Orden_estado_restaurante> call, Response<Orden_estado_restaurante> response) {
                if(response.body()!=null && response.code()==200){

                    gsonOrden_estado_restauranteMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Orden_estado_restaurante> call, Throwable t) {

            }
        });
    }



    public void  updateEstadoReady(Orden_estado_restaurante estado,int idUsuario){
        orden_estado_restauranteService.updateEstadoReady(estado,idUsuario).enqueue(new Callback<Orden_estado_restaurante>() {
            @Override
            public void onResponse(Call<Orden_estado_restaurante> call, Response<Orden_estado_restaurante> response) {
                if(response.body()!=null && response.code()==200){

                    gsonOrden_estado_restauranteMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Orden_estado_restaurante> call, Throwable t) {

            }
        });
    }


    public LiveData<Orden_estado_restaurante> getListOrden_estado_restauranteLiveData(){
        return gsonOrden_estado_restauranteMutableLiveData;
    }

}
