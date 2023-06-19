package com.mimiperla.empresayego.Repository.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;

public class Orden_estado_restaurante implements Serializable {

    @SerializedName("id")
    @Expose
    Orden_estado_restaurantePK id;

    @SerializedName("idempresa")
    @Expose
    private int idempresa;

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

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }
}
