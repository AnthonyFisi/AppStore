package com.example.empresayego.Repository.Repositorio;

import com.example.empresayego.Repository.Modelo.Empresa_historial;
import com.example.empresayego.Repository.Modelo.Gson.Empresa_historialGson;
import com.example.empresayego.Repository.Service.Empresa_historialService;

import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Empresa_historialRepository  {


    private static final String RESTAURANTE_PEDIDO_SERVICE_URL_BASE="https://backend-tiend-go.herokuapp.com/";

    private Empresa_historialService mEmpresa_historialService;
    private MutableLiveData<Empresa_historialGson> mEmpresa_historialGsonMutableLiveData;

    public Empresa_historialRepository(){
        mEmpresa_historialGsonMutableLiveData=new MutableLiveData<>();
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(RESTAURANTE_PEDIDO_SERVICE_URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mEmpresa_historialService = retrofit.create(Empresa_historialService.class);
    }

    public void searchEmpresaHistorialById(String token,int idempresa){
        mEmpresa_historialService.searchEmpresaHistorialById(token, idempresa).enqueue(new Callback<Empresa_historialGson>() {
            @Override
            public void onResponse(Call<Empresa_historialGson> call, Response<Empresa_historialGson> response) {
                if(response.code()==200 && response.body()!=null){

                    mEmpresa_historialGsonMutableLiveData.postValue(response.body());

                }else{

                    mEmpresa_historialGsonMutableLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<Empresa_historialGson> call, Throwable t) {
                mEmpresa_historialGsonMutableLiveData.postValue(null);

            }
        });
    }

    public MutableLiveData<Empresa_historialGson> getEmpresa_historialGsonMutableLiveData(){
        return mEmpresa_historialGsonMutableLiveData;
    }

}
