package com.mimiperla.empresayego.Repository.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductoJOINregistroPedidoJOINpedido implements Serializable {



    @SerializedName("idproducto")
    @Expose
    private int idproducto;

    @SerializedName("idempresa")
    @Expose
    private int idempresa;

    @SerializedName("producto_nombre")
    @Expose
    private String producto_nombre;

    @SerializedName("producto_precio")
    @Expose
    private float producto_precio;

    @SerializedName("producto_uriimagen")
    @Expose
    private String producto_uriimagen;

    @SerializedName("producto_calificacion")
    @Expose
    private float producto_calificacion;

    @SerializedName("producto_descuento")
    @Expose
    private float producto_descuento;

    @SerializedName("producto_precio_descuento")
    @Expose
    private float producto_precio_descuento;

    @SerializedName("registropedido_cantidadtotal")
    @Expose
    private int registropedido_cantidadtotal;

    @SerializedName("registropedido_preciototal")
    @Expose
    private float registropedido_preciototal;

    @SerializedName("idpedido")
    @Expose
    private int idpedido;

    @SerializedName("idusuario")
    @Expose
    private int idusuario;



    @SerializedName("pedido_estado")
    @Expose
    private boolean pedido_estado;

    @SerializedName("pedido_montototal")
    @Expose
    private float pedido_montototal;

    @SerializedName("pedido_cantidadtotal")
    @Expose
    private int pedido_cantidadtotal;



    @SerializedName("nombre_empresa")
    @Expose
    private String nombre_empresa;

    @SerializedName("costo_delivery")
    @Expose
    private float costo_delivery;

    @SerializedName("urlfoto_empresa")
    @Expose
    private String urlfoto_empresa;

    @SerializedName("icono_empresa")
    @Expose
    private String icono_empresa;


    @SerializedName("comentario")
    @Expose
    private String comentario;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public float getPedido_montototal() {
        return pedido_montototal;
    }

    public void setPedido_montototal(float pedido_montototal) {
        this.pedido_montototal = pedido_montototal;
    }

    public int getPedido_cantidadtotal() {
        return pedido_cantidadtotal;
    }

    public void setPedido_cantidadtotal(int pedido_cantidadtotal) {
        this.pedido_cantidadtotal = pedido_cantidadtotal;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public String getProducto_nombre() {
        return producto_nombre;
    }

    public void setProducto_nombre(String producto_nombre) {
        this.producto_nombre = producto_nombre;
    }

    public float getProducto_precio() {
        return producto_precio;
    }

    public void setProducto_precio(float producto_precio) {
        this.producto_precio = producto_precio;
    }

    public String getProducto_uriimagen() {
        return producto_uriimagen;
    }

    public void setProducto_uriimagen(String producto_uriimagen) {
        this.producto_uriimagen = producto_uriimagen;
    }

    public float getProducto_calificacion() {
        return producto_calificacion;
    }

    public void setProducto_calificacion(float producto_calificacion) {
        this.producto_calificacion = producto_calificacion;
    }

    public float getProducto_descuento() {
        return producto_descuento;
    }

    public void setProducto_descuento(float producto_descuento) {
        this.producto_descuento = producto_descuento;
    }

    public float getProducto_precio_descuento() {
        return producto_precio_descuento;
    }

    public void setProducto_precio_descuento(float producto_precio_descuento) {
        this.producto_precio_descuento = producto_precio_descuento;
    }

    public int getRegistropedido_cantidadtotal() {
        return registropedido_cantidadtotal;
    }

    public void setRegistropedido_cantidadtotal(int registropedido_cantidadtotal) {
        this.registropedido_cantidadtotal = registropedido_cantidadtotal;
    }

    public float getRegistropedido_preciototal() {
        return registropedido_preciototal;
    }

    public void setRegistropedido_preciototal(float registropedido_preciototal) {
        this.registropedido_preciototal = registropedido_preciototal;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public boolean isPedido_estado() {
        return pedido_estado;
    }

    public void setPedido_estado(boolean pedido_estado) {
        this.pedido_estado = pedido_estado;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public float getCosto_delivery() {
        return costo_delivery;
    }

    public void setCosto_delivery(float costo_delivery) {
        this.costo_delivery = costo_delivery;
    }

    public String getUrlfoto_empresa() {
        return urlfoto_empresa;
    }

    public void setUrlfoto_empresa(String urlfoto_empresa) {
        this.urlfoto_empresa = urlfoto_empresa;
    }

    public String getIcono_empresa() {
        return icono_empresa;
    }

    public void setIcono_empresa(String icono_empresa) {
        this.icono_empresa = icono_empresa;
    }


}
