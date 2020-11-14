package com.example.empresayego.ViewModel;

import android.app.Application;

import com.example.empresayego.Login.SessionPrefs;
import com.example.empresayego.Repository.Modelo.Gson.Empresa_historialGson;
import com.example.empresayego.Repository.Repositorio.Empresa_historialRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class Empresa_historialViewModel  extends AndroidViewModel {

    private Empresa_historialRepository mEmpresa_historialRepository;

    private LiveData<Empresa_historialGson> mEmpresa_historialGsonLiveData;

    private String token;

    public Empresa_historialViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        token= SessionPrefs.get(getApplication()).getTokenPrefs();
        mEmpresa_historialRepository=new Empresa_historialRepository();
        mEmpresa_historialGsonLiveData=mEmpresa_historialRepository.getEmpresa_historialGsonMutableLiveData();
    }

    public void searchEmpresaHistorialById(int idempresa){
        mEmpresa_historialRepository.searchEmpresaHistorialById(token, idempresa);
    }

    public LiveData<Empresa_historialGson> getEmpresa_historialGsonLiveData(){
        return mEmpresa_historialGsonLiveData;
    }
}
