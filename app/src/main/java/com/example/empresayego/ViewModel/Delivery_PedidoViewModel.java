package com.example.empresayego.ViewModel;

import android.app.Application;

import com.example.empresayego.Login.SessionPrefs;
import com.example.empresayego.Repository.Modelo.Gson.GsonDelivery_Pedido;
import com.example.empresayego.Repository.Repositorio.Delivery_PedidoRepository;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class Delivery_PedidoViewModel extends AndroidViewModel {

    private Delivery_PedidoRepository mDelivery_pedidoRepository;
    private LiveData<GsonDelivery_Pedido> mDelivery_pedidoLiveData;

    private String token;

    public Delivery_PedidoViewModel(@NonNull Application application) {
        super(application);
    }

    public  void init(){
        token= SessionPrefs.get(getApplication()).getTokenPrefs();

        mDelivery_pedidoRepository= new Delivery_PedidoRepository();
        mDelivery_pedidoLiveData=mDelivery_pedidoRepository.getDelivery_PedidoDataLiveData();

    }

    public void searchRepartidor(GsonDelivery_Pedido pedido){
       // String token= SessionPrefs.get(getApplication()).getTokenPrefs();

        mDelivery_pedidoRepository.searchRepartidor(token,pedido);
    }

    public LiveData<GsonDelivery_Pedido> getDelivery_PedidoLiveData(){
        return mDelivery_pedidoLiveData;
    }

}
