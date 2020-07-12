package com.example.empresayego.ViewModel;

import android.app.Application;

import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Repositorio.EmpresaRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class EmpresaViewModel extends AndroidViewModel {

    private EmpresaRepository mEmpresaRepository;
    private LiveData<Empresa> gsonEmpresaLiveData;

    public EmpresaViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        mEmpresaRepository= new EmpresaRepository();
        gsonEmpresaLiveData=mEmpresaRepository.getEmpresaDataLiveData();
    }

    public void searchEmpresaById(int idEmpresa){
        mEmpresaRepository.searchEmpresaById(idEmpresa);
    }

    public void updateEmpresaDisponiblidad(int idEmpresa,boolean disponibilidad){
        mEmpresaRepository.updateEmpresaDisponibilidad(idEmpresa,disponibilidad);
    }


    public  LiveData<Empresa> getEmpresaLiveData(){
        return gsonEmpresaLiveData;
    }

}
