package com.example.empresayego.Repository.Repositorio;

import com.example.empresayego.Repository.Modelo.EmpresaOficial;
import com.example.empresayego.Repository.Service.EmpresaOficialService;
import com.example.empresayego.Repository.UrlBase;

import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmpresaOficialRepository {


    private EmpresaOficialService empresaOficialService;

    private MutableLiveData<EmpresaOficial> mEmpresaOficialMutableLiveData;

    public EmpresaOficialRepository(){
        mEmpresaOficialMutableLiveData=new MutableLiveData<>();
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(UrlBase.URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        empresaOficialService = retrofit.create(EmpresaOficialService.class);
    }

    public void updateEmpresaDisponibilidad(String token,int idEmpresa,boolean disponiblidad){
        empresaOficialService.updateEmpresaState(token,idEmpresa,disponiblidad).enqueue(new Callback<EmpresaOficial>() {
            @Override
            public void onResponse(Call<EmpresaOficial> call, Response<EmpresaOficial> response) {

                if(response.code()==200 && response.body()!=null){

                    mEmpresaOficialMutableLiveData.setValue(response.body());

                }else{
                    mEmpresaOficialMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<EmpresaOficial> call, Throwable t) {

                mEmpresaOficialMutableLiveData.postValue(null);
            }
        });
    }


    public void updateNumeroTelefono(String token,int idEmpresa,String numTelefono){
        empresaOficialService.updateTelefonoState(token,idEmpresa,numTelefono).enqueue(new Callback<EmpresaOficial>() {
            @Override
            public void onResponse(Call<EmpresaOficial> call, Response<EmpresaOficial> response) {

                if(response.code()==200 && response.body()!=null){

                    mEmpresaOficialMutableLiveData.setValue(response.body());

                }else{
                    mEmpresaOficialMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<EmpresaOficial> call, Throwable t) {

                mEmpresaOficialMutableLiveData.postValue(null);

            }
        });
    }

    public void updateNumeroCelular(String token,int idEmpresa,String numCelular){
        empresaOficialService.updateCelularState(token,idEmpresa,numCelular).enqueue(new Callback<EmpresaOficial>() {
            @Override
            public void onResponse(Call<EmpresaOficial> call, Response<EmpresaOficial> response) {
                if(response.code()==200 && response.body()!=null){

                    mEmpresaOficialMutableLiveData.setValue(response.body());

                }else{
                    mEmpresaOficialMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<EmpresaOficial> call, Throwable t) {
                mEmpresaOficialMutableLiveData.postValue(null);

            }
        });
    }

    public void updateHorarioInicioFin(String token,int idEmpresa,int horarioInicio,int horarioFin){
        empresaOficialService.updateHorarioState(token,idEmpresa,horarioInicio,horarioFin).enqueue(new Callback<EmpresaOficial>() {
            @Override
            public void onResponse(Call<EmpresaOficial> call, Response<EmpresaOficial> response) {
                if(response.code()==200 && response.body()!=null){

                    mEmpresaOficialMutableLiveData.setValue(response.body());

                }else{
                    mEmpresaOficialMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<EmpresaOficial> call, Throwable t) {
                mEmpresaOficialMutableLiveData.postValue(null);

            }
        });
    }

    public void updateTiempoAproximado(String token,int idEmpresa,String tiempo){
        empresaOficialService.updateTiempoState(token,idEmpresa,tiempo).enqueue(new Callback<EmpresaOficial>() {
            @Override
            public void onResponse(Call<EmpresaOficial> call, Response<EmpresaOficial> response) {
                if(response.code()==200 && response.body()!=null){

                    mEmpresaOficialMutableLiveData.setValue(response.body());

                }else{
                    mEmpresaOficialMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<EmpresaOficial> call, Throwable t) {
                mEmpresaOficialMutableLiveData.postValue(null);

            }
        });
    }

    public void  updateDescripcion(String token,EmpresaOficial empresa){
        empresaOficialService.updateDescripcionState(token,empresa).enqueue(new Callback<EmpresaOficial>() {
            @Override
            public void onResponse(Call<EmpresaOficial> call, Response<EmpresaOficial> response) {
                if(response.code()==200 && response.body()!=null){

                    mEmpresaOficialMutableLiveData.setValue(response.body());

                }else{
                    mEmpresaOficialMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<EmpresaOficial> call, Throwable t) {
                mEmpresaOficialMutableLiveData.postValue(null);

            }
        });
    }

    public void updatePrecioMenu(String token,int idempresa,float precio){
        empresaOficialService.updatePrecioMenu(token,idempresa,precio).enqueue(new Callback<EmpresaOficial>() {
            @Override
            public void onResponse(Call<EmpresaOficial> call, Response<EmpresaOficial> response) {
                if(response.code()==200 && response.body()!=null){

                    mEmpresaOficialMutableLiveData.setValue(response.body());

                }else{
                    mEmpresaOficialMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<EmpresaOficial> call, Throwable t) {
                mEmpresaOficialMutableLiveData.postValue(null);

            }
        });
    }

    public MutableLiveData<EmpresaOficial> getEmpresaOficialDataLiveData(){
        return mEmpresaOficialMutableLiveData;
    }
}
