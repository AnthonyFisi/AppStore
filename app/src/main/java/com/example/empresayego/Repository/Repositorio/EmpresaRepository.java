package com.example.empresayego.Repository.Repositorio;

import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Service.EmpresaService;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmpresaRepository {


    private static final String RESTAURANTE_PEDIDO_SERVICE_URL_BASE="https://backend-tiend-go.herokuapp.com/";

    private EmpresaService empresaService;
    private MutableLiveData<Empresa> gsonEmpresaLiveData;

    public EmpresaRepository(){
        gsonEmpresaLiveData=new MutableLiveData<>();
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(RESTAURANTE_PEDIDO_SERVICE_URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        empresaService = retrofit.create(EmpresaService.class);
    }

    public void searchEmpresaById(int idEmpresa){
        empresaService.searchEmpresaById(idEmpresa).enqueue(new Callback<Empresa>() {
            @Override
            public void onResponse(Call<Empresa> call, Response<Empresa> response) {


                if(response.code()==200 && response.body()!=null){

                    gsonEmpresaLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Empresa> call, Throwable t) {

            }
        });

    }

    public void updateEmpresaDisponibilidad(int idEmpresa,boolean disponiblidad){
        empresaService.updateEmpresaState(idEmpresa,disponiblidad).enqueue(new Callback<Empresa>() {
            @Override
            public void onResponse(Call<Empresa> call, Response<Empresa> response) {

                if(response.code()==200 && response.body()!=null){

                    gsonEmpresaLiveData.setValue(response.body());
                }
            }


            @Override
            public void onFailure(Call<Empresa> call, Throwable t) {

            }
        });
    }

    public LiveData<Empresa> getEmpresaDataLiveData(){
        return gsonEmpresaLiveData;
    }

}
