package com.example.empresayego.ViewModel;

import android.app.Application;

import com.example.empresayego.Login.SessionPrefs;
import com.example.empresayego.Repository.Modelo.Gson.GsonEmpresa_historialDetail;
import com.example.empresayego.Repository.Repositorio.Empresa_historialDetailRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class Empresa_historialDetailViewModel extends AndroidViewModel {

    private Empresa_historialDetailRepository mEmpresa_historialDetailRepository;

    private LiveData<GsonEmpresa_historialDetail> mEmpresa_historialDetailLiveData;

    private String token;

    public Empresa_historialDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        token= SessionPrefs.get(getApplication()).getTokenPrefs();
        mEmpresa_historialDetailRepository=new Empresa_historialDetailRepository();
        mEmpresa_historialDetailLiveData=mEmpresa_historialDetailRepository.getEmpresa_historialDetailMutableLiveData();
    }

    public void searchEmpresaHistorialDetailById( int idEmpresa, int idPedido,int idRepartidor){
        mEmpresa_historialDetailRepository.searchEmpresaHistorialDetailById(token,idEmpresa,idPedido,idRepartidor);
    }

    public LiveData<GsonEmpresa_historialDetail> getEmpresa_historialDetailLiveData(){
        return mEmpresa_historialDetailLiveData;
    }
}
