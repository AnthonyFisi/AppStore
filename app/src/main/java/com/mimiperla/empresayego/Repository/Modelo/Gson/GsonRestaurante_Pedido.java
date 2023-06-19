package com.mimiperla.empresayego.Repository.Modelo.Gson;

import com.mimiperla.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GsonRestaurante_Pedido {

    @SerializedName("listaRestaurante_Pedido")
    @Expose
    private List<Restaurante_Pedido> listaRestaurante_Pedido;

    public List<Restaurante_Pedido> getListaRestaurante_Pedido() {
        return listaRestaurante_Pedido;
    }

    public void setListaRestaurante_Pedido(List<Restaurante_Pedido> listaRestaurante_Pedido) {
        this.listaRestaurante_Pedido = listaRestaurante_Pedido;
    }
}
