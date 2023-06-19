package com.mimiperla.empresayego.Repository.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Delivery_Pedido {

    @SerializedName("idventa")
    @Expose
    private int idventa;

    @SerializedName("idtipopago")
    @Expose
    private int idtipopago;

    @SerializedName("tipopago_nombre")
    @Expose
    private String tipopago_nombre;

    @SerializedName("ubicacion_nombre")
    @Expose
    private String ubicacion_nombre;

    @SerializedName("ubicacion_comentarios")
    @Expose
    private String ubicacion_comentarios;

    @SerializedName("usuario_coordenada_x")
    @Expose
    private String usuario_coordenada_x;

    @SerializedName("usuario_coordenada_y")
    @Expose
    private String usuario_coordenada_y;

    @SerializedName("idpedido")
    @Expose
    private int idpedido;

    @SerializedName("venta_costodelivery")
    @Expose
    private float venta_costodelivery;

    @SerializedName("venta_costototal")
    @Expose
    private float venta_costototal;

    @SerializedName("cancelar")
    @Expose
    private boolean cancelar;

    @SerializedName("comentario_global")
    @Expose
    private String comentario_global;

    @SerializedName("costo_delivery")
    @Expose
    private float costo_delivery;

    @SerializedName("distancia_delivery")
    @Expose
    private String distancia_delivery;

    @SerializedName("tiempo")
    @Expose
    private String tiempo;

    @SerializedName("orden_disponible")
    @Expose
    private boolean orden_disponible;

    @SerializedName("idestado_delivery")
    @Expose
    private int idestado_delivery;

    @SerializedName("idrepartidor")
    @Expose
    private int idrepartidor;

    @SerializedName("idestado_pago")
    @Expose
    private int idestado_pago;

    @SerializedName("nombre_estadopago")
    @Expose
    private String nombre_estadopago;

    @SerializedName("idusuario")
    @Expose
    private int idusuario;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("celular")
    @Expose
    private String celular;

    @SerializedName("idempresa")
    @Expose
    private int idempresa;

    @SerializedName("nombre_empresa")
    @Expose
    private String nombre_empresa;

    @SerializedName("direccion_empresa")
    @Expose
    private String direccion_empresa;

    @SerializedName("empresa_coordenada_x")
    @Expose
    private String empresa_coordenada_x;

    @SerializedName("empresa_coordenada_y")
    @Expose
    private String empresa_coordenada_y;


    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public int getIdtipopago() {
        return idtipopago;
    }

    public void setIdtipopago(int idtipopago) {
        this.idtipopago = idtipopago;
    }

    public String getTipopago_nombre() {
        return tipopago_nombre;
    }

    public void setTipopago_nombre(String tipopago_nombre) {
        this.tipopago_nombre = tipopago_nombre;
    }

    public String getUbicacion_nombre() {
        return ubicacion_nombre;
    }

    public void setUbicacion_nombre(String ubicacion_nombre) {
        this.ubicacion_nombre = ubicacion_nombre;
    }

    public String getUbicacion_comentarios() {
        return ubicacion_comentarios;
    }

    public void setUbicacion_comentarios(String ubicacion_comentarios) {
        this.ubicacion_comentarios = ubicacion_comentarios;
    }

    public String getUsuario_coordenada_x() {
        return usuario_coordenada_x;
    }

    public void setUsuario_coordenada_x(String usuario_coordenada_x) {
        this.usuario_coordenada_x = usuario_coordenada_x;
    }

    public String getUsuario_coordenada_y() {
        return usuario_coordenada_y;
    }

    public void setUsuario_coordenada_y(String usuario_coordenada_y) {
        this.usuario_coordenada_y = usuario_coordenada_y;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }


    public float getVenta_costodelivery() {
        return venta_costodelivery;
    }

    public void setVenta_costodelivery(float venta_costodelivery) {
        this.venta_costodelivery = venta_costodelivery;
    }

    public float getVenta_costototal() {
        return venta_costototal;
    }

    public void setVenta_costototal(float venta_costototal) {
        this.venta_costototal = venta_costototal;
    }

    public boolean isCancelar() {
        return cancelar;
    }

    public void setCancelar(boolean cancelar) {
        this.cancelar = cancelar;
    }

    public String getComentario_global() {
        return comentario_global;
    }

    public void setComentario_global(String comentario_global) {
        this.comentario_global = comentario_global;
    }

    public float getCosto_delivery() {
        return costo_delivery;
    }

    public void setCosto_delivery(float costo_delivery) {
        this.costo_delivery = costo_delivery;
    }

    public boolean isOrden_disponible() {
        return orden_disponible;
    }

    public void setOrden_disponible(boolean orden_disponible) {
        this.orden_disponible = orden_disponible;
    }

    public int getIdestado_pago() {
        return idestado_pago;
    }

    public void setIdestado_pago(int idestado_pago) {
        this.idestado_pago = idestado_pago;
    }

    public String getNombre_estadopago() {
        return nombre_estadopago;
    }

    public void setNombre_estadopago(String nombre_estadopago) {
        this.nombre_estadopago = nombre_estadopago;
    }

    public String getDistancia_delivery() {
        return distancia_delivery;
    }

    public void setDistancia_delivery(String distancia_delivery) {
        this.distancia_delivery = distancia_delivery;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getDireccion_empresa() {
        return direccion_empresa;
    }

    public void setDireccion_empresa(String direccion_empresa) {
        this.direccion_empresa = direccion_empresa;
    }

    public String getEmpresa_coordenada_x() {
        return empresa_coordenada_x;
    }

    public void setEmpresa_coordenada_x(String empresa_coordenada_x) {
        this.empresa_coordenada_x = empresa_coordenada_x;
    }

    public String getEmpresa_coordenada_y() {
        return empresa_coordenada_y;
    }

    public void setEmpresa_coordenada_y(String empresa_coordenada_y) {
        this.empresa_coordenada_y = empresa_coordenada_y;
    }

    public int getIdrepartidor() {
        return idrepartidor;
    }

    public void setIdrepartidor(int idrepartidor) {
        this.idrepartidor = idrepartidor;
    }

    public int getIdestado_delivery() {
        return idestado_delivery;
    }

    public void setIdestado_delivery(int idestado_delivery) {
        this.idestado_delivery = idestado_delivery;
    }
}
