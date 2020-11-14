package com.example.empresayego.ViewModel;

import android.app.Application;

import com.example.empresayego.Login.SessionPrefs;
import com.example.empresayego.Repository.Modelo.Gson.GsonRestaurante_Pedido;
import com.example.empresayego.Repository.Repositorio.Restaurante_PedidoRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class Restaurante_PedidoViewModel extends AndroidViewModel {

    private Restaurante_PedidoRepository restaurante_PedidoRepository;
    private LiveData<GsonRestaurante_Pedido>  gsonRestaurante_PedidoLiveData;
    private String token;

    public Restaurante_PedidoViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        token= SessionPrefs.get(getApplication()).getTokenPrefs();

        restaurante_PedidoRepository = new Restaurante_PedidoRepository();
        gsonRestaurante_PedidoLiveData= restaurante_PedidoRepository.getListRestaurante_PedidoLiveData();
    }

    public void searchRestaurantePedidoByEmpresa(int idEmpresa){
       restaurante_PedidoRepository.searchRestaurantePedidoByEmpresa(token,idEmpresa);
    }


    public void searchRestaurantePedidoByEmpresaProces(int idEmpresa){
        restaurante_PedidoRepository.searchRestaurantePedidoByEmpresaProces(token,idEmpresa);
    }

    public void searchRestaurantePedidoByEmpresaReady(int idEmpresa){
        restaurante_PedidoRepository.searchRestaurantePedidoByEmpresaReady(token,idEmpresa);
    }

    public void searchRestaurantePedidoByEmpresaHistorial(int idEmpresa){
        restaurante_PedidoRepository.searchRestaurantePedidoByEmpresaHistorial(token,idEmpresa);
    }
    public  LiveData<GsonRestaurante_Pedido> getRestaurante_PedidoLiveData(){
        return  gsonRestaurante_PedidoLiveData;
    }
}
