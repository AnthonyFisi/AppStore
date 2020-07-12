package com.example.empresayego.ViewModel;

import android.app.Application;

import com.example.empresayego.Repository.Modelo.Repartidor;
import com.example.empresayego.Repository.Repositorio.RepartidorRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class RepartidorViewModel extends AndroidViewModel {

    private RepartidorRepository mRepartidorRepository;
    private LiveData<Repartidor> gsonRepartidorLiveData;


    public RepartidorViewModel(@NonNull Application application) {
        super(application);
    }


    public void init(){
        mRepartidorRepository= new RepartidorRepository();
        gsonRepartidorLiveData=mRepartidorRepository.getRepartidorDataLiveData();
    }

    public void searchUbicacionById(int idUsuario){
       mRepartidorRepository.searchUbicacionById(idUsuario);
    }
    public  LiveData<Repartidor> getRepartidorLiveData(){
        return  gsonRepartidorLiveData;
    }

}
