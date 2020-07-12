package com.example.empresayego.Repository.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Restaurante_Pedido implements Serializable {

    @SerializedName("idpedido")
    @Expose
    private int idpedido;

    @SerializedName("idempresa")
    @Expose
    private int idempresa;

    @SerializedName("idventa")
    @Expose
    private int idventa;

    @SerializedName("idubicacion")
    @Expose
    private int idubicacion;

    @SerializedName("venta_fecha")
    @Expose
    private Timestamp venta_fecha;

    @SerializedName("venta_fechaentrega")
    @Expose
    private Timestamp venta_fechaentrega;

    @SerializedName("venta_costodelivery")
    @Expose
    private float venta_costodelivery;

    @SerializedName("venta_costototal")
    @Expose
    private float venta_costototal;

    @SerializedName("comentario_global")
    @Expose
    private String comentario_global;

    @SerializedName("idestado_pago")
    @Expose
    private int idestado_pago;

    @SerializedName("nombre_estado")
    @Expose
    private String nombre_estado;





    @SerializedName("idusuario")
    @Expose
    private int idusuario;

    @SerializedName("usuario_nombre")
    @Expose
    private String usuario_nombre;

    @SerializedName("usuario_celular")
    @Expose
    private String usuario_celular;

    @SerializedName("orden_disponible")
    @Expose
    private boolean orden_disponible;

    @SerializedName("idrepartidor")
    @Expose
    private int idrepartidor;

    @SerializedName("idtipopago")
    @Expose
    private int idtipopago;

    @SerializedName("tipopago_nombre")
    @Expose
    private String tipopago_nombre;

    @SerializedName("idestado_venta")
    @Expose
    private int idestado_venta;

    @SerializedName("tipo_estado")
    @Expose
    private String tipo_estado;

    @SerializedName("idtipo_envio")
    @Expose
    private int idtipo_envio;

    @SerializedName("nombre_tipo_envio")
    @Expose
    private String nombre_tipo_envio;


    @SerializedName("tiempo_espera")
    @Expose
    private String tiempo_espera;

    @SerializedName("pedido_cantidadtotal")
    @Expose
    private int pedido_cantidadtotal;


    @SerializedName("comentario_pedido")
    @Expose
    private String comentario_pedido;

    @SerializedName("nombre_repartidor")
    @Expose
    private String nombre_repartidor;

    @SerializedName("imagen_repartidor")
    @Expose
    private String imagen_repartidor;

    @SerializedName("codigo_repartidor")
    @Expose
    private String codigo_repartidor;

    @SerializedName("cancelar")
    @Expose
    private boolean cancelar;

    @SerializedName("telefono")
    @Expose
    private String telefono;

    @SerializedName("listaProductos")
    @Expose
    List<ProductoJOINregistroPedidoJOINpedido> listaProductos;

    public List<ProductoJOINregistroPedidoJOINpedido> getListaProductos() {
        return listaProductos;
    }


    public boolean isCancelar() {
        return cancelar;
    }

    public void setCancelar(boolean cancelar) {
        this.cancelar = cancelar;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setListaProductos(List<ProductoJOINregistroPedidoJOINpedido> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public int getPedido_cantidadtotal() {
        return pedido_cantidadtotal;
    }

    public void setPedido_cantidadtotal(int pedido_cantidadtotal) {
        this.pedido_cantidadtotal = pedido_cantidadtotal;
    }

    public String getNombre_repartidor() {
        return nombre_repartidor;
    }

    public void setNombre_repartidor(String nombre_repartidor) {
        this.nombre_repartidor = nombre_repartidor;
    }

    public String getImagen_repartidor() {
        return imagen_repartidor;
    }

    public void setImagen_repartidor(String imagen_repartidor) {
        this.imagen_repartidor = imagen_repartidor;
    }

    public String getCodigo_repartidor() {
        return codigo_repartidor;
    }

    public void setCodigo_repartidor(String codigo_repartidor) {
        this.codigo_repartidor = codigo_repartidor;
    }
    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public int getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(int idubicacion) {
        this.idubicacion = idubicacion;
    }

    public Timestamp getVenta_fecha() {
        return venta_fecha;
    }

    public void setVenta_fecha(Timestamp venta_fecha) {
        this.venta_fecha = venta_fecha;
    }

    public Timestamp getVenta_fechaentrega() {
        return venta_fechaentrega;
    }

    public void setVenta_fechaentrega(Timestamp venta_fechaentrega) {
        this.venta_fechaentrega = venta_fechaentrega;
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

    public String getComentario_global() {
        return comentario_global;
    }

    public void setComentario_global(String comentario_global) {
        this.comentario_global = comentario_global;
    }

    public int getIdestado_pago() {
        return idestado_pago;
    }

    public void setIdestado_pago(int idestado_pago) {
        this.idestado_pago = idestado_pago;
    }

    public String getNombre_estado() {
        return nombre_estado;
    }

    public void setNombre_estado(String nombre_estado) {
        this.nombre_estado = nombre_estado;
    }


    public String getComentario_pedido() {
        return comentario_pedido;
    }

    public void setComentario_pedido(String comentario_pedido) {
        this.comentario_pedido = comentario_pedido;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsuario_nombre() {
        return usuario_nombre;
    }

    public void setUsuario_nombre(String usuario_nombre) {
        this.usuario_nombre = usuario_nombre;
    }

    public String getUsuario_celular() {
        return usuario_celular;
    }

    public void setUsuario_celular(String usuario_celular) {
        this.usuario_celular = usuario_celular;
    }

    public boolean isOrden_disponible() {
        return orden_disponible;
    }

    public void setOrden_disponible(boolean orden_disponible) {
        this.orden_disponible = orden_disponible;
    }

    public int getIdrepartidor() {
        return idrepartidor;
    }

    public void setIdrepartidor(int idrepartidor) {
        this.idrepartidor = idrepartidor;
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

    public int getIdestado_venta() {
        return idestado_venta;
    }

    public void setIdestado_venta(int idestado_venta) {
        this.idestado_venta = idestado_venta;
    }

    public String getTipo_estado() {
        return tipo_estado;
    }

    public void setTipo_estado(String tipo_estado) {
        this.tipo_estado = tipo_estado;
    }

    public int getIdtipo_envio() {
        return idtipo_envio;
    }

    public void setIdtipo_envio(int idtipo_envio) {
        this.idtipo_envio = idtipo_envio;
    }

    public String getNombre_tipo_envio() {
        return nombre_tipo_envio;
    }

    public void setNombre_tipo_envio(String nombre_tipo_envio) {
        this.nombre_tipo_envio = nombre_tipo_envio;
    }

    public String getTiempo_espera() {
        return tiempo_espera;
    }

    public void setTiempo_espera(String tiempo_espera) {
        this.tiempo_espera = tiempo_espera;
    }
}
