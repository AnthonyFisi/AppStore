package com.mimiperla.empresayego.Repository.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Restaurante_Pedido implements Serializable {

    @SerializedName("idventa")
    @Expose
    private int idventa;

    @SerializedName("idtipopago")
    @Expose
    private int idtipopago;

    @SerializedName("tipopago_nombre")
    @Expose
    private String tipopago_nombre;

    @SerializedName("idhorario")
    @Expose
    private int idhorario;


    @SerializedName("horario_nombre")
    @Expose
    private String horario_nombre;

    @SerializedName("idubicacion")
    @Expose
    private int idubicacion;

    @SerializedName("idpedido")
    @Expose
    private int idpedido;

    @SerializedName("idempresa")
    @Expose
    private int idempresa;

    @SerializedName("pedido_cantidadtotal")
    @Expose
    private int pedido_cantidadtotal;

    @SerializedName("idusuario")
    @Expose
    private int idusuario;

    @SerializedName("idusuariogeneral")
    @Expose
    private int idusuariogeneral;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("apellido")
    @Expose
    private String apellido;

    @SerializedName("celular")
    @Expose
    private String celular;

    @SerializedName("ventafecha")
    @Expose
    private Timestamp ventafecha;

    @SerializedName("ventafechaentrega")
    @Expose
    private Timestamp ventafechaentrega;

    @SerializedName("venta_costodelivery")
    @Expose
    private float venta_costodelivery;

    @SerializedName("venta_costototal")
    @Expose
    private float venta_costototal;

    @SerializedName("comentario")
    @Expose
    private String comentario;

    @SerializedName("idestadoempresa")
    @Expose
    private int idestadoempresa;

    @SerializedName("idestado_pago")
    @Expose
    private int idestado_pago;

    @SerializedName("nombre_estadopago")
    @Expose
    private String nombre_estadopago;

    @SerializedName("idtipo_envio")
    @Expose
    private int idtipo_envio;

    @SerializedName("nombre_tipo_envio")
    @Expose
    private String nombre_tipo_envio;


    @SerializedName("ordendisponible")
    @Expose
    private boolean ordendisponible;


    @SerializedName("tiempo_espera")
    @Expose
    private String tiempo_espera;


    @SerializedName("idrepartidor")
    @Expose
    private int idrepartidor;


    @SerializedName("cancelar")
    @Expose
    private boolean cancelar;


    @SerializedName("comentario_cancelar")
    @Expose
    private boolean comentario_cancelar;


    @SerializedName("idestadodelivery")
    @Expose
    private int idestadodelivery;

    @SerializedName("idestadogeneral")
    @Expose
    private int idestadogeneral;

    @SerializedName("numeromesa")
    @Expose
    private int numeromesa;

    @SerializedName("descuento_mesa")
    @Expose
    private float descuento_mesa;

    @SerializedName("mesa")
    @Expose
    private boolean mesa;

    @SerializedName("fechaAceptado")
    @Expose
    private String fechaAceptado;

    @SerializedName("ubicacion_nombre")
    @Expose
    private String ubicacion_nombre;

    @SerializedName("maps_coordenada_x")
    @Expose
    private String maps_coordenada_x;

    @SerializedName("maps_coordenada_y")
    @Expose
    private String maps_coordenada_y;

    @SerializedName("ubicacion_comentarios")
    @Expose
    private String ubicacion_comentarios;

    @SerializedName("tiempo_aprox_delivery")
    @Expose
    private String tiempo_aprox_delivery;

    @SerializedName("distancia_delivery")
    @Expose
    private String distancia_delivery;

    @SerializedName("tiempototal_espera")
    @Expose
    private String tiempototal_espera;

    @SerializedName("horaservidor")
    @Expose
    private Timestamp horaservidor;


    @SerializedName("repartidor_bi")
    @Expose
    private Repartidor_Bi repartidor_bi;

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

    public void setListaProductos(List<ProductoJOINregistroPedidoJOINpedido> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public int getPedido_cantidadtotal() {
        return pedido_cantidadtotal;
    }

    public void setPedido_cantidadtotal(int pedido_cantidadtotal) {
        this.pedido_cantidadtotal = pedido_cantidadtotal;
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

    public int getIdestado_pago() {
        return idestado_pago;
    }

    public void setIdestado_pago(int idestado_pago) {
        this.idestado_pago = idestado_pago;
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

    public Timestamp getVentafecha() {
        return ventafecha;
    }

    public void setVentafecha(Timestamp ventafecha) {
        this.ventafecha = ventafecha;
    }

    public Timestamp getVentafechaentrega() {
        return ventafechaentrega;
    }

    public void setVentafechaentrega(Timestamp ventafechaentrega) {
        this.ventafechaentrega = ventafechaentrega;
    }


    public int getIdestadoempresa() {
        return idestadoempresa;
    }

    public void setIdestadoempresa(int idestadoempresa) {
        this.idestadoempresa = idestadoempresa;
    }

    public int getIdestadodelivery() {
        return idestadodelivery;
    }

    public void setIdestadodelivery(int idestadodelivery) {
        this.idestadodelivery = idestadodelivery;
    }

    public int getIdestadogeneral() {
        return idestadogeneral;
    }

    public void setIdestadogeneral(int idestadogeneral) {
        this.idestadogeneral = idestadogeneral;
    }

    public int getNumeromesa() {
        return numeromesa;
    }

    public void setNumeromesa(int numeromesa) {
        this.numeromesa = numeromesa;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdusuariogeneral() {
        return idusuariogeneral;
    }

    public void setIdusuariogeneral(int idusuariogeneral) {
        this.idusuariogeneral = idusuariogeneral;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public boolean isOrdendisponible() {
        return ordendisponible;
    }

    public void setOrdendisponible(boolean ordendisponible) {
        this.ordendisponible = ordendisponible;
    }

    public int getIdhorario() {
        return idhorario;
    }

    public void setIdhorario(int idhorario) {
        this.idhorario = idhorario;
    }

    public String getHorario_nombre() {
        return horario_nombre;
    }

    public void setHorario_nombre(String horario_nombre) {
        this.horario_nombre = horario_nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getNombre_estadopago() {
        return nombre_estadopago;
    }

    public void setNombre_estadopago(String nombre_estadopago) {
        this.nombre_estadopago = nombre_estadopago;
    }

    public boolean isComentario_cancelar() {
        return comentario_cancelar;
    }

    public void setComentario_cancelar(boolean comentario_cancelar) {
        this.comentario_cancelar = comentario_cancelar;
    }

    public String getFechaAceptado() {
        return fechaAceptado;
    }

    public void setFechaAceptado(String fechaAceptado) {
        this.fechaAceptado = fechaAceptado;
    }

    public float getDescuento_mesa() {
        return descuento_mesa;
    }

    public void setDescuento_mesa(float descuento_mesa) {
        this.descuento_mesa = descuento_mesa;
    }

    public boolean isMesa() {
        return mesa;
    }

    public void setMesa(boolean mesa) {
        this.mesa = mesa;
    }

    public Repartidor_Bi getRepartidor_bi() {
        return repartidor_bi;
    }

    public void setRepartidor_bi(Repartidor_Bi repartidor_bi) {
        this.repartidor_bi = repartidor_bi;
    }

    public String getUbicacion_nombre() {
        return ubicacion_nombre;
    }

    public void setUbicacion_nombre(String ubicacion_nombre) {
        this.ubicacion_nombre = ubicacion_nombre;
    }

    public String getMaps_coordenada_x() {
        return maps_coordenada_x;
    }

    public void setMaps_coordenada_x(String maps_coordenada_x) {
        this.maps_coordenada_x = maps_coordenada_x;
    }

    public String getMaps_coordenada_y() {
        return maps_coordenada_y;
    }

    public void setMaps_coordenada_y(String maps_coordenada_y) {
        this.maps_coordenada_y = maps_coordenada_y;
    }

    public String getUbicacion_comentarios() {
        return ubicacion_comentarios;
    }

    public void setUbicacion_comentarios(String ubicacion_comentarios) {
        this.ubicacion_comentarios = ubicacion_comentarios;
    }

    public String getTiempo_aprox_delivery() {
        return tiempo_aprox_delivery;
    }

    public void setTiempo_aprox_delivery(String tiempo_aprox_delivery) {
        this.tiempo_aprox_delivery = tiempo_aprox_delivery;
    }

    public String getDistancia_delivery() {
        return distancia_delivery;
    }

    public void setDistancia_delivery(String distancia_delivery) {
        this.distancia_delivery = distancia_delivery;
    }

    public String getTiempototal_espera() {
        return tiempototal_espera;
    }

    public void setTiempototal_espera(String tiempototal_espera) {
        this.tiempototal_espera = tiempototal_espera;
    }

    public Timestamp getHoraservidor() {
        return horaservidor;
    }

    public void setHoraservidor(Timestamp horaservidor) {
        this.horaservidor = horaservidor;
    }
}
