package com.mimiperla.empresayego.ViewModel;

import android.app.Application;

import com.mimiperla.empresayego.Login.SessionPrefs;
import com.mimiperla.empresayego.Repository.Modelo.Venta;
import com.mimiperla.empresayego.Repository.Repositorio.VentaRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class VentaViewModel extends AndroidViewModel {


    private VentaRepository mVentaRepository;
    private LiveData<Venta> gsonVentaLiveData;
    private String token;
    public VentaViewModel(@NonNull Application application) {
        super(application);
    }


    public void init(){
        token= SessionPrefs.get(getApplication()).getTokenPrefs();

        mVentaRepository = new VentaRepository();
        gsonVentaLiveData=mVentaRepository.getVentaLiveData();
    }


    public void updateEstadoVentaTiempo(int idVenta,int tiempo){
        mVentaRepository.updateEstadoVentaTiempo(token,idVenta,tiempo);
    }


    public void updateEstadoVentaCostoTotal(int idVenta,float tiempo){
        mVentaRepository.updateEstadoVentaCostoTotal(token,idVenta,tiempo);
    }


    public void updateEstadoVentaCostoCancelar(Venta venta){
        mVentaRepository.updateEstadoVentaCostoCancelar(token,venta);
    }

    public  LiveData<Venta> getVentaLiveData(){
        return gsonVentaLiveData;
    }

}
