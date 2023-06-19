package com.mimiperla.empresayego.Repository.Modelo;


public class Delivery {


    private boolean disponible;

    private int idCuentaRepartidor;

    private int idRepartidor;

    private int idUsuario;

    private String  location;

    private String nombre;

    private boolean online;

    private boolean cuentaActivada;


    private int hora;

    private double pedido;

    private double frecuencia;

    private double calificacion;



    public Delivery(boolean disponible, int idCuentaRepartidor, int idRepartidor, int idUsuario, String location,
                    String nombre, boolean online, boolean cuentaActivada, int hora, double pedido, double frecuencia,
                    double calificacion) {

        this.disponible = disponible;
        this.idCuentaRepartidor = idCuentaRepartidor;
        this.idRepartidor = idRepartidor;
        this.idUsuario = idUsuario;
        this.location = location;
        this.nombre = nombre;
        this.online = online;
        this.cuentaActivada = cuentaActivada;
        this.hora = hora;
        this.pedido = pedido;
        this.frecuencia = frecuencia;
        this.calificacion = calificacion;


    }

    public Delivery() {}

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getIdCuentaRepartidor() {
        return idCuentaRepartidor;
    }

    public void setIdCuentaRepartidor(int idCuentaRepartidor) {
        this.idCuentaRepartidor = idCuentaRepartidor;
    }

    public int getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(int idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isCuentaActivada() {
        return cuentaActivada;
    }

    public void setCuentaActivada(boolean cuentaActivada) {
        this.cuentaActivada = cuentaActivada;
    }



    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public double getPedido() {
        return pedido;
    }

    public void setPedido(double pedido) {
        this.pedido = pedido;
    }

    public double getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(double frecuencia) {
        this.frecuencia = frecuencia;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }





}
