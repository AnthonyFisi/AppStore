package com.example.empresayego.Repository.Repositorio;

import com.example.empresayego.Repository.Modelo.Gson.GsonProducto;
import com.example.empresayego.Repository.Service.ProductoService;
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

public class ProductoRepository {


    private static final String RESTAURANTE_PEDIDO_SERVICE_URL_BASE="https://backend-tiend-go.herokuapp.com/";

    private ProductoService mProductoService;
    private MutableLiveData<GsonProducto> mGsonProductoMutableLiveData;

    public ProductoRepository(){
        mGsonProductoMutableLiveData=new MutableLiveData<>();
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(RESTAURANTE_PEDIDO_SERVICE_URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mProductoService= retrofit.create(ProductoService.class);
    }

    public void searchProductoById(int idEmpresa){
        mProductoService.searchProductoById(idEmpresa).enqueue(new Callback<GsonProducto>() {
            @Override
            public void onResponse(Call<GsonProducto> call, Response<GsonProducto> response) {
                if(response.code()==200 && response.body()!=null){

                    mGsonProductoMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GsonProducto> call, Throwable t) {
                mGsonProductoMutableLiveData.setValue(null);



            }
        });
    }

    public LiveData<GsonProducto> getGsonProductoDataLiveData(){
        return  getGsonProductoDataLiveData();
    }
}
