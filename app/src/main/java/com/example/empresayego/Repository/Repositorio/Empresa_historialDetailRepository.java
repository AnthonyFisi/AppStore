package com.example.empresayego.Repository.Repositorio;

import com.example.empresayego.Repository.Modelo.Gson.GsonEmpresa_historialDetail;
import com.example.empresayego.Repository.Service.Empresa_historialDetailService;

import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Empresa_historialDetailRepository {


    private static final String RESTAURANTE_PEDIDO_SERVICE_URL_BASE="https://backend-tiend-go.herokuapp.com/";

    private Empresa_historialDetailService mEmpresa_historialDetailService;
    private MutableLiveData<GsonEmpresa_historialDetail> mEmpresa_historialDetailMutableLiveData;

    public Empresa_historialDetailRepository(){
        mEmpresa_historialDetailMutableLiveData=new MutableLiveData<>();
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(RESTAURANTE_PEDIDO_SERVICE_URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

       mEmpresa_historialDetailService = retrofit.create(Empresa_historialDetailService.class);
    }

    public void searchEmpresaHistorialDetailById(String token, int idEmpresa, int idPedido,int idRepartidor){
        mEmpresa_historialDetailService.searchEmpresaHistorialDetailById(token, idEmpresa, idPedido, idRepartidor).enqueue(new Callback<GsonEmpresa_historialDetail>() {
            @Override
            public void onResponse(Call<GsonEmpresa_historialDetail> call, Response<GsonEmpresa_historialDetail> response) {
                if(response.code()==200 && response.body()!=null){

                   mEmpresa_historialDetailMutableLiveData.postValue(response.body());

                }else{

                    mEmpresa_historialDetailMutableLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<GsonEmpresa_historialDetail> call, Throwable t) {
                mEmpresa_historialDetailMutableLiveData.postValue(null);

            }
        });
    }

    public MutableLiveData<GsonEmpresa_historialDetail> getEmpresa_historialDetailMutableLiveData(){
        return mEmpresa_historialDetailMutableLiveData;
    }
}
