package com.mimiperla.empresayego.ViewModel;

import android.app.Application;

import com.mimiperla.empresayego.Login.SessionPrefs;
import com.mimiperla.empresayego.Repository.Modelo.Gson.GsonRepartidor_Bi;
import com.mimiperla.empresayego.Repository.Repositorio.Repartidor_BiRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class Repartidor_BiViewModel extends AndroidViewModel {

    private Repartidor_BiRepository mRepartidorBiRepository;
    private LiveData<GsonRepartidor_Bi> gsonRepartidorLiveData;
    private String token;

    public Repartidor_BiViewModel(@NonNull Application application) {
        super(application);
    }


    public void init(){
        token= SessionPrefs.get(getApplication()).getTokenPrefs();

        mRepartidorBiRepository = new Repartidor_BiRepository();
        gsonRepartidorLiveData= mRepartidorBiRepository.getGsonRepartidorDataLiveData();
    }

    public void listaRepartidor(int idempresa){
       mRepartidorBiRepository.listaRepartidores(token,idempresa);
    }
    public  LiveData<GsonRepartidor_Bi> getGsonRepartidorLiveData(){
        return  gsonRepartidorLiveData;
    }

}
