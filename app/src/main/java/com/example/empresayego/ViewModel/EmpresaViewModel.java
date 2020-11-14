package com.example.empresayego.ViewModel;

import android.app.Application;

import com.example.empresayego.Login.SessionPrefs;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Repositorio.EmpresaRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class EmpresaViewModel extends AndroidViewModel {

    private EmpresaRepository mEmpresaRepository;
    private LiveData<Empresa> gsonEmpresaLiveData;
    private String token;

    public EmpresaViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        token= SessionPrefs.get(getApplication()).getTokenPrefs();

        mEmpresaRepository= new EmpresaRepository();
        gsonEmpresaLiveData=mEmpresaRepository.getEmpresaDataLiveData();
    }

    public void searchEmpresaById(int idEmpresa){
        mEmpresaRepository.searchEmpresaById(token,idEmpresa);
    }

    public void updateEmpresaDisponiblidad(int idEmpresa,boolean disponibilidad){
        mEmpresaRepository.updateEmpresaDisponibilidad(token,idEmpresa,disponibilidad);
    }

    public void updateNumeroTelefono(int idEmpresa,String numTelefono){
        mEmpresaRepository.updateNumeroTelefono(token,idEmpresa,numTelefono);
    }

    public void updateNumeroCelular(int idEmpresa,String numCelular) {

        mEmpresaRepository.updateNumeroCelular(token,idEmpresa,numCelular);
    }

    public void updateHorarioInicioFin(int idEmpresa,int horarioInicio,int horarioFin) {

            mEmpresaRepository.updateHorarioInicioFin(token,idEmpresa,horarioInicio,horarioFin);
    }

    public void updateiempoAproximado(int idEmpresa,String tiempo) {

        mEmpresaRepository.updateTiempoAproximado(token,idEmpresa,tiempo);
    }

    public void  updateDescripcion(Empresa empresa){
        mEmpresaRepository.updateDescripcion(token,empresa);
    }

    public void updatePrecioMenu(int idempresa,float precio){
        mEmpresaRepository.updatePrecioMenu(token,idempresa,precio);
    }

        public  LiveData<Empresa> getEmpresaLiveData(){
        return gsonEmpresaLiveData;
    }

}
