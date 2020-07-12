package com.example.empresayego.Repository.Modelo.Gson;

import com.example.empresayego.Repository.Modelo.Producto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GsonProducto {

    @SerializedName("listaProductos")
    @Expose
    private List<Producto> listaProductos;


    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
}
