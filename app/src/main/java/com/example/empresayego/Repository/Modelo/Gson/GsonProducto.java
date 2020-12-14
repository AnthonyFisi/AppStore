package com.example.empresayego.Repository.Modelo.Gson;

import com.example.empresayego.Repository.Modelo.Producto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GsonProducto implements Serializable {

    @SerializedName("listaProducto")
    @Expose
    private List<Producto> listaProducto;

    public List<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }
}
