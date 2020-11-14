package com.example.empresayego.Repository.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Repartidor_Bi implements Serializable {

    @SerializedName("idusuariogeneral")
    @Expose
    private int idusuariogeneral;

    @SerializedName("idrepartidor")
    @Expose
    private int idrepartidor;

    @SerializedName("nombre_usuario")
    @Expose
    private String nombre_usuario;

    @SerializedName("correo")
    @Expose
    private String correo;

    @SerializedName("apellido")
    @Expose
    private String apellido;

    @SerializedName("celular")
    @Expose
    private String celular;

    @SerializedName("foto")
    @Expose
    private String foto;

    @SerializedName("idcuentarepartidor")
    @Expose
    private  int idcuentarepartidor;

    @SerializedName("activa")
    @Expose
    private boolean activa;

    @SerializedName("idtarifa")
    @Expose
    private int idtarifa;

    @SerializedName("nombre_tarifa")
    @Expose
    private String nombre_tarifa;

    @SerializedName("monto")
    @Expose
    private float monto;

    @SerializedName("idtipocuenta")
    @Expose
    private int idtipocuenta;

    @SerializedName("nombre_cuenta")
    @Expose
    private String nombre_cuenta;

    @SerializedName("placa_vehiculo")
    @Expose
    private String placa_vehiculo;

    @SerializedName("imagen_vehiculo")
    @Expose
    private String imagen_vehiculo;

    @SerializedName("disponible")
    @Expose
    private boolean disponible;

    @SerializedName("idempresa")
    @Expose
    private int idempresa;



    public int getIdusuariogeneral() {
        return idusuariogeneral;
    }

    public void setIdusuariogeneral(int idusuariogeneral) {
        this.idusuariogeneral = idusuariogeneral;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getIdcuentarepartidor() {
        return idcuentarepartidor;
    }

    public void setIdcuentarepartidor(int idcuentarepartidor) {
        this.idcuentarepartidor = idcuentarepartidor;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public int getIdtarifa() {
        return idtarifa;
    }

    public void setIdtarifa(int idtarifa) {
        this.idtarifa = idtarifa;
    }

    public String getNombre_tarifa() {
        return nombre_tarifa;
    }

    public void setNombre_tarifa(String nombre_tarifa) {
        this.nombre_tarifa = nombre_tarifa;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public int getIdtipocuenta() {
        return idtipocuenta;
    }

    public void setIdtipocuenta(int idtipocuenta) {
        this.idtipocuenta = idtipocuenta;
    }

    public String getNombre_cuenta() {
        return nombre_cuenta;
    }

    public void setNombre_cuenta(String nombre_cuenta) {
        this.nombre_cuenta = nombre_cuenta;
    }

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

    public boolean isDisponible() {
        return disponible;
    }

    public void setDiponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }
}
