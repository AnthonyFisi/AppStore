package com.example.empresayego.Repository.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Orden_estado_restaurante {

    @SerializedName("id")
    @Expose
    Orden_estado_restaurantePK id;

    @SerializedName("detalle")
    @Expose
    private String detalle;

    @SerializedName("fecha")
    @Expose
    private Timestamp fecha;

    public Orden_estado_restaurantePK getId() {
        return id;
    }

    public void setId(Orden_estado_restaurantePK id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
