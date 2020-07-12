package com.example.empresayego.Repository.Repositorio;

import com.example.empresayego.Repository.Modelo.Repartidor;
import com.example.empresayego.Repository.Service.RepartidorService;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

public class RepartidorRepository {


    private static final String RESTAURANTE_PEDIDO_SERVICE_URL_BASE="https://backend-tiend-go.herokuapp.com/";

    private RepartidorService mRepartidorService;
    private MutableLiveData<Repartidor> gsonRepartidorMutableLiveData;

    public RepartidorRepository(){
       gsonRepartidorMutableLiveData=new MutableLiveData<>();
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(RESTAURANTE_PEDIDO_SERVICE_URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRepartidorService = retrofit.create(RepartidorService.class);
    }

    public void searchUbicacionById(int idUsuario){
        mRepartidorService.searchUbicacionById(idUsuario).enqueue(new Callback<Repartidor>() {
            @Override
            public void onResponse(Call<Repartidor> call, Response<Repartidor> response) {


                if(response.code()==200 && response.body()!=null){

                   gsonRepartidorMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Repartidor> call, Throwable t) {

                gsonRepartidorMutableLiveData.postValue(null);
            }
        });
    }


    public LiveData<Repartidor> getRepartidorDataLiveData(){
        return gsonRepartidorMutableLiveData;
    }
}
