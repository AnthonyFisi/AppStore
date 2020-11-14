package com.example.empresayego.Login.ViewModel;

import android.app.Application;

import com.example.empresayego.Login.Modelo.Empresa_bi;
import com.example.empresayego.Login.Repositorio.Empresa_biRepository;
import com.example.empresayego.Login.SessionPrefs;
import com.example.empresayego.Repository.Modelo.Empresa;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class Empresa_biViewModel extends AndroidViewModel {

    private Empresa_biRepository mEmpresa_biRepository;

    private LiveData<Empresa> mEmpresa_biLiveData;

   // private String token;

    public Empresa_biViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        //token= SessionPrefs.get(getApplication()).getTokenPrefs();
        mEmpresa_biRepository= new Empresa_biRepository();
        mEmpresa_biLiveData=mEmpresa_biRepository.getEmpresaMutableLivedata();
    }

    public void getEmpresaById(String token,int idempresa){
        mEmpresa_biRepository.getEmpresaById(token, idempresa);
    }

    public LiveData<Empresa> getEmpresa_biLiveData(){
        return mEmpresa_biLiveData;
    }
}
