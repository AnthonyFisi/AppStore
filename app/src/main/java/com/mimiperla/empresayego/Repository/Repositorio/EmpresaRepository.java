package com.mimiperla.empresayego.Repository.Repositorio;

import com.mimiperla.empresayego.Repository.Modelo.Empresa;
import com.mimiperla.empresayego.Repository.Service.EmpresaService;
import com.mimiperla.empresayego.Repository.UrlBase;

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



    private EmpresaService empresaService;

    private MutableLiveData<Empresa> gsonEmpresaLiveData;

    public EmpresaRepository(){
        gsonEmpresaLiveData=new MutableLiveData<>();
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(UrlBase.URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        empresaService = retrofit.create(EmpresaService.class);
    }

    public void searchEmpresaById(String token,int idEmpresa){
        empresaService.searchEmpresaById(token,idEmpresa).enqueue(new Callback<Empresa>() {
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

    public void updateEmpresaDisponibilidad(String token,int idEmpresa,boolean disponiblidad){
        empresaService.updateEmpresaState(token,idEmpresa,disponiblidad).enqueue(new Callback<Empresa>() {
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


    public void updateNumeroTelefono(String token,int idEmpresa,String numTelefono){
        empresaService.updateTelefonoState(token,idEmpresa,numTelefono).enqueue(new Callback<Empresa>() {
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

    public void updateNumeroCelular(String token,int idEmpresa,String numCelular){
        empresaService.updateCelularState(token,idEmpresa,numCelular).enqueue(new Callback<Empresa>() {
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

    public void updateHorarioInicioFin(String token,int idEmpresa,int horarioInicio,int horarioFin){
        empresaService.updateHorarioState(token,idEmpresa,horarioInicio,horarioFin).enqueue(new Callback<Empresa>() {
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

    public void updateTiempoAproximado(String token,int idEmpresa,String tiempo){
        empresaService.updateTiempoState(token,idEmpresa,tiempo).enqueue(new Callback<Empresa>() {
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

    public void  updateDescripcion(String token,Empresa empresa){
        empresaService.updateDescripcionState(token,empresa).enqueue(new Callback<Empresa>() {
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

    public void updatePrecioMenu(String token,int idempresa,float precio){
        empresaService.updatePrecioMenu(token,idempresa,precio).enqueue(new Callback<Empresa>() {
            @Override
            public void onResponse(Call<Empresa> call, Response<Empresa> response) {
                if(response.code()==200 && response.body()!=null){

                    gsonEmpresaLiveData.postValue(response.body());
                }else{
                    gsonEmpresaLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<Empresa> call, Throwable t) {

                gsonEmpresaLiveData.postValue(null);

            }
        });
    }

    public LiveData<Empresa> getEmpresaDataLiveData(){
        return gsonEmpresaLiveData;
    }

}


