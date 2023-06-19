package com.mimiperla.empresayego.ViewModel;

import android.app.Application;

import com.mimiperla.empresayego.Login.SessionPrefs;
import com.mimiperla.empresayego.Repository.Modelo.Gson.GsonCategoria_producto_empresa;
import com.mimiperla.empresayego.Repository.Repositorio.Categoria_producto_empresaRepository;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class Categoria_producto_empresaViewModel extends AndroidViewModel {

    private Categoria_producto_empresaRepository mCategoria_producto_empresaRepository;

    private LiveData<GsonCategoria_producto_empresa> mGsonCategoria_producto_empresaLiveData;

    private String token;

    public Categoria_producto_empresaViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        token= SessionPrefs.get(getApplication()).getTokenPrefs();
        mCategoria_producto_empresaRepository= new Categoria_producto_empresaRepository();
        mGsonCategoria_producto_empresaLiveData=mCategoria_producto_empresaRepository.getGsonCategoria_producto_empresaMutableLiveData();
    }

    public  void searchCategoriaProductoEmpresa(int idempresa){
        mCategoria_producto_empresaRepository.searchCategoriaProductoEmpresa(token,idempresa);
    }

    public LiveData<GsonCategoria_producto_empresa> getGsonCategoria_producto_empresaLiveData(){
        return mGsonCategoria_producto_empresaLiveData;
    }


}
