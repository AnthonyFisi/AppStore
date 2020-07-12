package com.example.empresayego.Repository.Repositorio;

import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.example.empresayego.Repository.Modelo.Venta;
import com.example.empresayego.Repository.Service.VentaService;

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

    private static final String RESTAURANTE_PEDIDO_SERVICE_URL_BASE="https://backend-tiend-go.herokuapp.com/";

    private VentaService ventaService;
    private MutableLiveData<Venta> gsonVentaMutableLiveData;

    public VentaRepository(){
       gsonVentaMutableLiveData=new MutableLiveData<>();
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(RESTAURANTE_PEDIDO_SERVICE_URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ventaService = retrofit.create(VentaService.class);
    }

    public void updateEstadoVentaTiempo(int idVenta,int tiempo){
        ventaService.updateEstadoVentaTiempo(idVenta,tiempo).enqueue(new Callback<Venta>() {
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


    public void updateEstadoVentaCostoTotal(int idVenta,float tiempo){
        ventaService.updateEstadoVentaCosto(idVenta,tiempo).enqueue(new Callback<Venta>() {
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


    public void updateEstadoVentaCostoCancelar(Venta venta){
        ventaService.updateEstadoVentaCancelar(venta).enqueue(new Callback<Venta>() {
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
