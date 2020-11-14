package com.example.empresayego.Repository.Repositorio;

import com.example.empresayego.Repository.Modelo.Gson.GsonCategoria_producto_empresa;
import com.example.empresayego.Repository.Service.Categoria_producto_empresaService;


import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class Categoria_producto_empresaRepository {

    private static final String CATEGORIA_SEARCH_SERVICE_URL_BASE="https://backend-tiend-go.herokuapp.com/";

    private Categoria_producto_empresaService mCategoria_producto_empresaService;

    private MutableLiveData<GsonCategoria_producto_empresa> mGsonCategoria_producto_empresaMutableLiveData;

    public Categoria_producto_empresaRepository() {
        mGsonCategoria_producto_empresaMutableLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        mCategoria_producto_empresaService = new retrofit2.Retrofit.Builder()
                .baseUrl(CATEGORIA_SEARCH_SERVICE_URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Categoria_producto_empresaService.class);

    }


    public  void searchCategoriaProductoEmpresa(String auth,int idempresa){
        mCategoria_producto_empresaService.searchCategoriaProductoEmpresa(auth,idempresa).enqueue(new Callback<GsonCategoria_producto_empresa>() {
            @Override
            public void onResponse(Call<GsonCategoria_producto_empresa> call, Response<GsonCategoria_producto_empresa> response) {
                if (response.body() != null) {
                    mGsonCategoria_producto_empresaMutableLiveData.postValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<GsonCategoria_producto_empresa> call, Throwable t) {
                mGsonCategoria_producto_empresaMutableLiveData.postValue(null);

            }
        });
    }


    public MutableLiveData<GsonCategoria_producto_empresa> getGsonCategoria_producto_empresaMutableLiveData(){
        return mGsonCategoria_producto_empresaMutableLiveData;
    }


}
