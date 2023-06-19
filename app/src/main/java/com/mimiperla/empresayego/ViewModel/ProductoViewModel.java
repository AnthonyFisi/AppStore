package com.mimiperla.empresayego.ViewModel;

import android.app.Application;

import com.mimiperla.empresayego.Login.SessionPrefs;
import com.mimiperla.empresayego.Repository.Modelo.Gson.GsonProducto;
import com.mimiperla.empresayego.Repository.Modelo.Producto;
import com.mimiperla.empresayego.Repository.Repositorio.ProductoRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ProductoViewModel extends AndroidViewModel {

    private ProductoRepository mProductoRepository;
    private LiveData<GsonProducto> mGsonProductoLiveData;
    private LiveData<Producto> mProductoLiveData;
    private String token;

    public ProductoViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        token= SessionPrefs.get(getApplication()).getTokenPrefs();

        mProductoRepository= new ProductoRepository();
        mGsonProductoLiveData=mProductoRepository.getProductoGsonDataLiveData();
        mProductoLiveData=mProductoRepository.getProductoDataLiveData();
    }


    public void searchProductoById(int idEmpresa){
        mProductoRepository.searchProductoById(token,idEmpresa);
    }

    public void updateStateProduto(int idProducto,int idEmpresa,boolean state){
        mProductoRepository.updateStateProduto(token,idProducto,idEmpresa,state);
    }

    public void searchProducto(int idcategoriaproducto, int idempresa) {
        System.out.println("estoy en searchproducto VIEW MODEL");

        mProductoRepository.searchListProducto(token,idcategoriaproducto,idempresa);
    }

    public void insertProduct(Producto producto){
        mProductoRepository.insertProduct(token,producto);
    }

    public  LiveData<Producto> getProductoLiveData(){
        return mProductoLiveData;
    }


    public  LiveData<GsonProducto> getProductoGsonLiveData(){
        return mGsonProductoLiveData;
    }


}
