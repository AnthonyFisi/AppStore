package com.mimiperla.empresayego.Login.Repositorio;

import com.mimiperla.empresayego.Login.Service.Empresa_biService;
import com.mimiperla.empresayego.Repository.Modelo.Empresa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class Empresa_biRepository {

    private static final String EMPRESA_SERVICE_URL_BASE= "http://192.168.100.34:8080/";
            //"https://backend-tiend-go.herokuapp.com/";

    private Empresa_biService empresaService;
  

    private MutableLiveData<Empresa> mEmpresaMutableLiveData;


    public Empresa_biRepository(){

        
        mEmpresaMutableLiveData= new MutableLiveData<>();


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        empresaService = new retrofit2.Retrofit.Builder()
                .baseUrl(EMPRESA_SERVICE_URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Empresa_biService.class);
    }
    

    public void getEmpresaById(String token, int idempresa){
        empresaService.getEmpresaBiById(token,idempresa).enqueue(new Callback<Empresa>() {
            @Override
            public void onResponse(Call<Empresa> call, Response<Empresa> response) {
                if(response.code()==200 && response.body()!=null){
                    mEmpresaMutableLiveData.postValue(response.body());
                }else{
                    mEmpresaMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<Empresa> call, Throwable t) {
                mEmpresaMutableLiveData.postValue(null);

            }
        });
    }


    public LiveData<Empresa> getEmpresaMutableLivedata(){
        return mEmpresaMutableLiveData;
    }


}
