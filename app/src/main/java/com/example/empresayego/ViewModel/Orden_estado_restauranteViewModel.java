package com.example.empresayego.ViewModel;

import android.app.Application;

import com.example.empresayego.Repository.Modelo.Orden_estado_restaurante;
import com.example.empresayego.Repository.Repositorio.Orden_estado_restauranteRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class Orden_estado_restauranteViewModel extends AndroidViewModel {

    private Orden_estado_restauranteRepository orden_estado_restauranteRepository;

    private LiveData<Orden_estado_restaurante> gsonOrden_estado_restauranteLiveData;

    public void init(){
        orden_estado_restauranteRepository= new Orden_estado_restauranteRepository();
        gsonOrden_estado_restauranteLiveData=orden_estado_restauranteRepository.getListOrden_estado_restauranteLiveData();

    }

    public Orden_estado_restauranteViewModel(@NonNull Application application) {
        super(application);
    }

    public void   updateEstado(Orden_estado_restaurante estado,String tiempo_espera,int idUsuario){
        orden_estado_restauranteRepository.updateEstado(estado,tiempo_espera,idUsuario);
    }

    public void   updateEstadoProces(Orden_estado_restaurante estado,int idUsuario){
        orden_estado_restauranteRepository.updateEstadoProces(estado,idUsuario);
    }

    public void   updateEstadoReady(Orden_estado_restaurante estado,int idUsuario){
        orden_estado_restauranteRepository.updateEstadoReady(estado,idUsuario);
    }


    public  LiveData<Orden_estado_restaurante> getOrden_estado_restauranteLiveData(){
        return  gsonOrden_estado_restauranteLiveData;
    }
}
