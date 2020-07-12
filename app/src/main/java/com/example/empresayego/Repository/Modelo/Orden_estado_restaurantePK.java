package com.example.empresayego.Repository.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Orden_estado_restaurantePK {

    @SerializedName("idventa")
    @Expose
    private int idventa;

    @SerializedName("idestado_venta")
    @Expose
    private int idestado_venta;


    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public int getIdestado_venta() {
        return idestado_venta;
    }

    public void setIdestado_venta(int idestado_venta) {
        this.idestado_venta = idestado_venta;
    }
}
