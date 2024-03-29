package com.mimiperla.empresayego.Repository.Repositorio;

import com.mimiperla.empresayego.Repository.Modelo.Venta;
import com.mimiperla.empresayego.Repository.Service.VentaService;
import com.mimiperla.empresayego.Repository.UrlBase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VentaRepository {

    private VentaService ventaService;
    private MutableLiveData<Venta> gsonVentaMutableLiveData;

    public VentaRepository(){
       gsonVentaMutableLiveData=new MutableLiveData<>();
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(UrlBase.URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ventaService = retrofit.create(VentaService.class);
    }

    public void updateEstadoVentaTiempo(String token,int idVenta,int tiempo){
        ventaService.updateEstadoVentaTiempo(token,idVenta,tiempo).enqueue(new Callback<Venta>() {
            @Override
            public void onResponse(Call<Venta> call, Response<Venta> response) {

                if(response.code()==200 && response.body()!=null){

                   gsonVentaMutableLiveData.postValue(response.body());

                }else{

                    gsonVentaMutableLiveData.postValue(null);

                }


            }

            @Override
            public void onFailure(Call<Venta> call, Throwable t) {

            }
        });

    }


    public void updateEstadoVentaCostoTotal(String token,int idVenta,float tiempo){
        ventaService.updateEstadoVentaCosto(token,idVenta,tiempo).enqueue(new Callback<Venta>() {
            @Override
            public void onResponse(Call<Venta> call, Response<Venta> response) {

                if(response.code()==200 && response.body()!=null){

                    gsonVentaMutableLiveData.postValue(response.body());

                }else{

                    gsonVentaMutableLiveData.postValue(null);

                }


            }

            @Override
            public void onFailure(Call<Venta> call, Throwable t) {

            }
        });

    }


    public void updateEstadoVentaCostoCancelar(String token,Venta venta){
        ventaService.updateEstadoVentaCancelar(token,venta).enqueue(new Callback<Venta>() {
            @Override
            public void onResponse(Call<Venta> call, Response<Venta> response) {

                if(response.code()==200 && response.body()!=null){

                    gsonVentaMutableLiveData.postValue(response.body());

                }else{

                    gsonVentaMutableLiveData.postValue(null);

                }


            }

            @Override
            public void onFailure(Call<Venta> call, Throwable t) {

            }
        });

    }


    public LiveData<Venta> getVentaLiveData(){
        return gsonVentaMutableLiveData;
    }
}
