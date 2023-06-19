package com.mimiperla.empresayego.Repository.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Categoria_producto_empresa implements Serializable {

    @SerializedName("idcategoriaproductoempresa")
    @Expose
    private int idcategoriaproductoempresa;

    @SerializedName("idempresa")
    @Expose
    private int idempresa ;

    @SerializedName("nombre")
    @Expose
    private String nombre ;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    public int getIdcategoriaproductoempresa() {
        return idcategoriaproductoempresa;
    }

    public void setIdcategoriaproductoempresa(int idcategoriaproductoempresa) {
        this.idcategoriaproductoempresa = idcategoriaproductoempresa;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
