package com.example.empresayego.Repository.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Orden_estado_restaurantePK {

    @SerializedName("idventa")
    @Expose
    private int idventa;

    @SerializedName("idestado_empresa")
    @Expose
    private int idestado_empresa;


    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public int getIdestado_empresa() {
        return idestado_empresa;
    }

    public void setIdestado_empresa(int idestado_empresa) {
        this.idestado_empresa = idestado_empresa;
    }
}
