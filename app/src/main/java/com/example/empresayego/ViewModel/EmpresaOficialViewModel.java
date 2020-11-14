package com.example.empresayego.ViewModel;

import android.app.Application;

import com.example.empresayego.Login.SessionPrefs;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Modelo.EmpresaOficial;
import com.example.empresayego.Repository.Repositorio.EmpresaOficialRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class EmpresaOficialViewModel extends AndroidViewModel {

    private EmpresaOficialRepository mEmpresaOficialRepository;

    private LiveData<EmpresaOficial> mEmpresaOficialLiveData;

    private String token;

    public EmpresaOficialViewModel(@NonNull Application application) {
        super(application);
    }


    public void init(){
        token= SessionPrefs.get(getApplication()).getTokenPrefs();

        mEmpresaOficialRepository= new EmpresaOficialRepository();
        mEmpresaOficialLiveData=mEmpresaOficialRepository.getEmpresaOficialDataLiveData();
    }



    public void updateEmpresaDisponiblidad(int idEmpresa,boolean disponibilidad){
        mEmpresaOficialRepository.updateEmpresaDisponibilidad(token,idEmpresa,disponibilidad);
    }

    public void updateNumeroTelefono(int idEmpresa,String numTelefono){
        mEmpresaOficialRepository.updateNumeroTelefono(token,idEmpresa,numTelefono);
    }

    public void updateNumeroCelular(int idEmpresa,String numCelular) {

        mEmpresaOficialRepository.updateNumeroCelular(token,idEmpresa,numCelular);
    }

    public void updateHorarioInicioFin(int idEmpresa,int horarioInicio,int horarioFin) {

        mEmpresaOficialRepository.updateHorarioInicioFin(token,idEmpresa,horarioInicio,horarioFin);
    }

    public void updateiempoAproximado(int idEmpresa,String tiempo) {

        mEmpresaOficialRepository.updateTiempoAproximado(token,idEmpresa,tiempo);
    }

    public void  updateDescripcion(EmpresaOficial empresa){
        mEmpresaOficialRepository.updateDescripcion(token,empresa);
    }

    public void updatePrecioMenu(int idempresa,float precio){
        mEmpresaOficialRepository.updatePrecioMenu(token,idempresa,precio);
    }

    public  LiveData<EmpresaOficial> getEmpresaOficialLiveData(){
        return mEmpresaOficialLiveData;
    }
}
