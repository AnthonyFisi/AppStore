package com.example.empresayego.Repository.Repositorio;

import com.example.empresayego.Repository.Modelo.Gson.GsonRestaurante_Pedido;
import com.example.empresayego.Repository.Service.Restaurante_PedidoService;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Restaurante_PedidoRepository {

    private static final String RESTAURANTE_PEDIDO_SERVICE_URL_BASE="https://backend-tiend-go.herokuapp.com/";

    private Restaurante_PedidoService restaurante_PedidoService;
    private MutableLiveData<GsonRestaurante_Pedido> gsonRestaurante_PedidoMutableLiveData;

    public Restaurante_PedidoRepository(){
        gsonRestaurante_PedidoMutableLiveData=new MutableLiveData<>();
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(RESTAURANTE_PEDIDO_SERVICE_URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restaurante_PedidoService = retrofit.create(Restaurante_PedidoService.class);
    }

    public void searchRestaurantePedidoByEmpresa(int idEmpresa){
        restaurante_PedidoService.searchRestaurantePedidoByEmpresa(idEmpresa).enqueue(new Callback<GsonRestaurante_Pedido>() {
            @Override
            public void onResponse(Call<GsonRestaurante_Pedido> call, Response<GsonRestaurante_Pedido> response) {
                if(response.code()==200 && response.body()!=null){

                    gsonRestaurante_PedidoMutableLiveData.postValue(response.body());

                }else{

                    gsonRestaurante_PedidoMutableLiveData.postValue(new GsonRestaurante_Pedido());

                }


            }

            @Override
            public void onFailure(Call<GsonRestaurante_Pedido> call, Throwable t) {


                gsonRestaurante_PedidoMutableLiveData.postValue(null);
            }
        });

    }

    public void searchRestaurantePedidoByEmpresaProces(int idEmpresa){
        restaurante_PedidoService.searchRestaurantePedidoByEmpresaProces(idEmpresa).enqueue(new Callback<GsonRestaurante_Pedido>() {
            @Override
            public void onResponse(Call<GsonRestaurante_Pedido> call, Response<GsonRestaurante_Pedido> response) {
                if(response.code()==200 && response.body()!=null){

                    gsonRestaurante_PedidoMutableLiveData.postValue(response.body());
                }else{

                    gsonRestaurante_PedidoMutableLiveData.postValue(new GsonRestaurante_Pedido());

                }
            }

            @Override
            public void onFailure(Call<GsonRestaurante_Pedido> call, Throwable t) {

                gsonRestaurante_PedidoMutableLiveData.postValue(null);
            }
        });

    }

    public void searchRestaurantePedidoByEmpresaReady(int idEmpresa){
        restaurante_PedidoService.searchRestaurantePedidoByEmpresaReady(idEmpresa).enqueue(new Callback<GsonRestaurante_Pedido>() {
            @Override
            public void onResponse(Call<GsonRestaurante_Pedido> call, Response<GsonRestaurante_Pedido> response) {
                if(response.code()==200 && response.body()!=null){

                    gsonRestaurante_PedidoMutableLiveData.setValue(response.body());
                }else{

                    gsonRestaurante_PedidoMutableLiveData.postValue(new GsonRestaurante_Pedido());

                }
            }

            @Override
            public void onFailure(Call<GsonRestaurante_Pedido> call, Throwable t) {

                gsonRestaurante_PedidoMutableLiveData.setValue(null);
            }
        });

    }

    public void searchRestaurantePedidoByEmpresaHistorial(int idEmpresa){
        restaurante_PedidoService.searchRestaurantePedidoByEmpresaHistorial(idEmpresa).enqueue(new Callback<GsonRestaurante_Pedido>() {
            @Override
            public void onResponse(Call<GsonRestaurante_Pedido> call, Response<GsonRestaurante_Pedido> response) {
                if(response.code()==200 && response.body()!=null){

                    gsonRestaurante_PedidoMutableLiveData.setValue(response.body());
                }else{

                    gsonRestaurante_PedidoMutableLiveData.postValue(new GsonRestaurante_Pedido());

                }
            }

            @Override
            public void onFailure(Call<GsonRestaurante_Pedido> call, Throwable t) {

                gsonRestaurante_PedidoMutableLiveData.setValue(null);
            }
        });

    }

    public LiveData<GsonRestaurante_Pedido> getListRestaurante_PedidoLiveData(){
      return gsonRestaurante_PedidoMutableLiveData;
    }

}
