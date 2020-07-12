package com.example.empresayego.ViewModel;

import android.app.Application;

import com.example.empresayego.Repository.Modelo.Gson.GsonProducto;
import com.example.empresayego.Repository.Repositorio.ProductoRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ProductoViewModel extends AndroidViewModel {

    private ProductoRepository mProductoRepository;
    private LiveData<GsonProducto> mGsonProductoLiveData;


    public ProductoViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        mProductoRepository= new ProductoRepository();
        mGsonProductoLiveData=mProductoRepository.getGsonProductoDataLiveData();
    }

    public void searchProductoById(int idEmpresa){
        mProductoRepository.searchProductoById(idEmpresa);
    }

    public  LiveData<GsonProducto> getRepartidorLiveData(){
        return mGsonProductoLiveData;
    }


}
