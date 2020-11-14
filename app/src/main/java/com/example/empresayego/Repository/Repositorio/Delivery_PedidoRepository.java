package com.example.empresayego.Repository.Repositorio;

import com.example.empresayego.Repository.Modelo.Gson.GsonDelivery_Pedido;
import com.example.empresayego.Repository.Service.Delivery_PedidoService;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Delivery_PedidoRepository {

    private static final String RESTAURANTE_PEDIDO_SERVICE_URL_BASE="https://backend-tiend-go.herokuapp.com/";

    private Delivery_PedidoService mDelivery_pedidoService;
    private MutableLiveData<GsonDelivery_Pedido> mDelivery_pedidoMutableLiveData;

    public Delivery_PedidoRepository(){
        mDelivery_pedidoMutableLiveData= new MutableLiveData<>();
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(RESTAURANTE_PEDIDO_SERVICE_URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mDelivery_pedidoService= retrofit.create(Delivery_PedidoService.class);
    }

    public void searchRepartidor(String token,GsonDelivery_Pedido pedido) {
        mDelivery_pedidoService.searchRepartidor(token,pedido).enqueue(new Callback<GsonDelivery_Pedido>() {
            @Override
            public void onResponse(Call<GsonDelivery_Pedido> call, Response<GsonDelivery_Pedido> response) {

                if (response.code() == 200 && response.body() != null) {
                    System.out.println("code 200");

                    mDelivery_pedidoMutableLiveData.postValue(response.body());
                }else{
                    mDelivery_pedidoMutableLiveData.postValue(null);

                }

            }

            @Override
            public void onFailure(Call<GsonDelivery_Pedido> call, Throwable t) {
                mDelivery_pedidoMutableLiveData.postValue(null);

            }
        });

    }

    public LiveData<GsonDelivery_Pedido> getDelivery_PedidoDataLiveData(){
        return  mDelivery_pedidoMutableLiveData;
    }


}
