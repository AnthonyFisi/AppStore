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

   // private MutableLiveData<Orden_estado_restaurante> gsonOrden_estado_restauranteCancelarMutableLiveData;

    public Orden_estado_restauranteRepository(){
        gsonOrden_estado_restauranteMutableLiveData=new MutableLiveData<>();
       // gsonOrden_estado_restauranteCancelarMutableLiveData=new MutableLiveData<>();

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



    public void  updateEstado(String token,Orden_estado_restaurante estado,String tiempo_espera,int idUsuario,String horario,String fechaentrega){
        orden_estado_restauranteService.updateEstado(token,estado,tiempo_espera,idUsuario,horario,fechaentrega).enqueue(new Callback<Orden_estado_restaurante>() {
            @Override
            public void onResponse(Call<Orden_estado_restaurante> call, Response<Orden_estado_restaurante> response) {
                if(response.body()!=null && response.code()==200){
                    gsonOrden_estado_restauranteMutableLiveData.postValue(response.body());
                }else {
                    gsonOrden_estado_restauranteMutableLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<Orden_estado_restaurante> call, Throwable t) {
                System.out.println(t.getCause()+"/"+t.getStackTrace()+"/"+t.getLocalizedMessage());
                gsonOrden_estado_restauranteMutableLiveData.postValue(null);

            }
        });
    }




    public void  updateEstadoProces(String token,Orden_estado_restaurante estado,int idUsuario){
        orden_estado_restauranteService.updateEstadoProces(token,estado,idUsuario).enqueue(new Callback<Orden_estado_restaurante>() {
            @Override
            public void onResponse(Call<Orden_estado_restaurante> call, Response<Orden_estado_restaurante> response) {
                if(response.body()!=null && response.code()==200){

                    gsonOrden_estado_restauranteMutableLiveData.postValue(response.body());
                }else {
                    gsonOrden_estado_restauranteMutableLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<Orden_estado_restaurante> call, Throwable t) {
                gsonOrden_estado_restauranteMutableLiveData.postValue(null);

            }
        });
    }

    public void  updateEstadoProcesMarketPlace(String token,Orden_estado_restaurante estado,int idUsuario,int idrepartidor){
        orden_estado_restauranteService.updateEstadoProcesMarketPlace(token,estado,idUsuario,idrepartidor).enqueue(new Callback<Orden_estado_restaurante>() {
            @Override
            public void onResponse(Call<Orden_estado_restaurante> call, Response<Orden_estado_restaurante> response) {
                if(response.body()!=null && response.code()==200){

                    gsonOrden_estado_restauranteMutableLiveData.postValue(response.body());
                }
                else {
                    gsonOrden_estado_restauranteMutableLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<Orden_estado_restaurante> call, Throwable t) {
                gsonOrden_estado_restauranteMutableLiveData.postValue(null);

            }
        });
    }


    public void  updateEstadoReady(String token,Orden_estado_restaurante estado,int idUsuario){
        orden_estado_restauranteService.updateEstadoReady(token,estado,idUsuario).enqueue(new Callback<Orden_estado_restaurante>() {
            @Override
            public void onResponse(Call<Orden_estado_restaurante> call, Response<Orden_estado_restaurante> response) {
                if(response.body()!=null && response.code()==200){

                    gsonOrden_estado_restauranteMutableLiveData.postValue(response.body());
                }else {
                    gsonOrden_estado_restauranteMutableLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<Orden_estado_restaurante> call, Throwable t) {
                gsonOrden_estado_restauranteMutableLiveData.postValue(null);

            }
        });
    }

    public void  updateEstadoCancelar(String token,Orden_estado_restaurante estado,int idUsuario){
        orden_estado_restauranteService.updateEstadoCancelar(token,estado,idUsuario).enqueue(new Callback<Orden_estado_restaurante>() {
            @Override
            public void onResponse(Call<Orden_estado_restaurante> call, Response<Orden_estado_restaurante> response) {
                if(response.body()!=null && response.code()==200){

                    gsonOrden_estado_restauranteMutableLiveData.postValue(response.body());
                }else {
                    gsonOrden_estado_restauranteMutableLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<Orden_estado_restaurante> call, Throwable t) {
                gsonOrden_estado_restauranteMutableLiveData.postValue(null);

            }
        });
    }




    public LiveData<Orden_estado_restaurante> getListOrden_estado_restauranteLiveData(){
        return gsonOrden_estado_restauranteMutableLiveData;
    }
/*
    public LiveData<Orden_estado_restaurante> getListOrden_estado_restauranteCancelarLiveData(){
        return gsonOrden_estado_restauranteCancelarMutableLiveData;
    }*/

}
