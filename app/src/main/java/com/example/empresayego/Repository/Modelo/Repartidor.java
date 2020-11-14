package com.example.empresayego.Repository.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repartidor {

    @SerializedName("idrepartidor")
    @Expose
    private int idrepartidor;

    @SerializedName("placa_vehiculo")
    @Expose
    private String placa_vehiculo;

    @SerializedName("imagen_vehiculo")
    @Expose
    private String imagen_vehiculo;

    @SerializedName("tipo_brevete")
    @Expose
    private String tipo_brevete;

    @SerializedName("detalles")
    @Expose
    private String detalles;

    @SerializedName("disponible")
    @Expose
    private boolean disponible ;

    @SerializedName("idusuariogeneral")
    @Expose
    private int idusuariogeneral;

    @SerializedName("idempresa")
    @Expose
    private int idempresa;




    public int getIdrepartidor() {
        return idrepartidor;
    }

    public void setIdrepartidor(int idrepartidor) {
        this.idrepartidor = idrepartidor;
    }

    public String getPlaca_vehiculo() {
        return placa_vehiculo;
    }

    public void setPlaca_vehiculo(String placa_vehiculo) {
        this.placa_vehiculo = placa_vehiculo;
    }

    public String getImagen_vehiculo() {
        return imagen_vehiculo;
    }

    public void setImagen_vehiculo(String imagen_vehiculo) {
        this.imagen_vehiculo = imagen_vehiculo;
    }

    public String getTipo_brevete() {
        return tipo_brevete;
    }

    public void setTipo_brevete(String tipo_brevete) {
        this.tipo_brevete = tipo_brevete;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getIdusuariogeneral() {
        return idusuariogeneral;
    }

    public void setIdusuariogeneral(int idusuariogeneral) {
        this.idusuariogeneral = idusuariogeneral;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }
}
