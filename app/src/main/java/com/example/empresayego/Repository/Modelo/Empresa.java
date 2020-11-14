package com.example.empresayego.Repository.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Empresa implements Serializable {

    public static Empresa sEmpresa;

    @SerializedName("idempresa")
    @Expose
    private int idempresa;

    @SerializedName("idsubcategoriaempresa")
    @Expose
    private int idsubcategoriaempresa;

    @SerializedName("nombre_empresa")
    @Expose
    private String nombre_empresa;

    @SerializedName("direccion_empresa")
    @Expose
    private String direccion_empresa;

    @SerializedName("ruc_empresa")
    @Expose
    private String ruc_empresa;

    @SerializedName("telefono_empresa")
    @Expose
    private String telefono_empresa;

    @SerializedName("celular_empresa")
    @Expose
    private String celular_empresa;

    @SerializedName("descripcion_empresa")
    @Expose
    private String descripcion_empresa;

    @SerializedName("urlfoto_empresa")
    @Expose
    private String urlfoto_empresa;

    @SerializedName("nombredueno_empresa")
    @Expose
    private String nombredueno_empresa;

    @SerializedName("idcuentaempresa")
    @Expose
    private int idcuentaempresa;

    @SerializedName("cuentaactiva")
    @Expose
    private boolean cuentaactiva;

    @SerializedName("porcentaje_popularidad")
    @Expose
    private double porcentaje_popularidad;

    @SerializedName("cuenta_delivery")
    @Expose
    private boolean cuenta_Delivery;

    @SerializedName("costo_delivery")
    @Expose
    private float costo_delivery;

    @SerializedName("detalle_delivery")
    @Expose
    private String detalle_delivery;


    @SerializedName("iddistrito")
    @Expose
    private int iddistrito;

    @SerializedName("tiempo_aproximado_entrega")
    @Expose
    private String tiempo_aproximado_entrega;

    @SerializedName("icono_empresa")
    @Expose
    private String icono_empresa;


    @SerializedName("horario_inicio")
    @Expose
    private int horario_inicio;

    @SerializedName("horario_fin")
    @Expose
    private int horario_fin;

    @SerializedName("tarjeta")
    @Expose
    private boolean tarjeta;

    @SerializedName("detalle_tarjeta")
    @Expose
    private String detalle_tarjeta;

    @SerializedName("estrella_empresa")
    @Expose
    private float estrella_empresa;



    @SerializedName("maps_coordenada_x")
    @Expose
    private String maps_coordenada_x;

    @SerializedName("maps_coordenada_y")
    @Expose
    private String maps_coordenada_y;

    @SerializedName("detalle_ubicacion")
    @Expose
    private String detalle_ubicacion;

    @SerializedName("disponible")
    @Expose
    private boolean disponible;

    @SerializedName("precio_menu")
    @Expose
    private float precio_menu;

    @SerializedName("monto_descuento_menu")
    @Expose
    private float monto_descuento_menu;

    @SerializedName("idusuariogeneral")
    @Expose
    private int idusuariogeneral;

    @SerializedName("apellido")
    @Expose
    private String apellido;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("correo")
    @Expose
    private String correo;

    @SerializedName("celular")
    @Expose
    private String celular;

    @SerializedName("foto")
    @Expose
    private String foto;

    public String getIcono_empresa() {
        return icono_empresa;
    }

    public void setIcono_empresa(String icono_empresa) {
        this.icono_empresa = icono_empresa;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public int getIdsubcategoriaempresa() {
        return idsubcategoriaempresa;
    }

    public void setIdsubcategoriaempresa(int idsubcategoriaempresa) {
        this.idsubcategoriaempresa = idsubcategoriaempresa;
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

    public String getRuc_empresa() {
        return ruc_empresa;
    }

    public void setRuc_empresa(String ruc_empresa) {
        this.ruc_empresa = ruc_empresa;
    }

    public String getTelefono_empresa() {
        return telefono_empresa;
    }

    public void setTelefono_empresa(String telefono_empresa) {
        this.telefono_empresa = telefono_empresa;
    }

    public String getCelular_empresa() {
        return celular_empresa;
    }

    public void setCelular_empresa(String celular_empresa) {
        this.celular_empresa = celular_empresa;
    }

    public String getDescripcion_empresa() {
        return descripcion_empresa;
    }

    public void setDescripcion_empresa(String descripcion_empresa) {
        this.descripcion_empresa = descripcion_empresa;
    }

    public String getUrlfoto_empresa() {
        return urlfoto_empresa;
    }

    public void setUrlfoto_empresa(String urlfoto_empresa) {
        this.urlfoto_empresa = urlfoto_empresa;
    }

    public String getNombredueno_empresa() {
        return nombredueno_empresa;
    }

    public void setNombredueno_empresa(String nombredueno_empresa) {
        this.nombredueno_empresa = nombredueno_empresa;
    }

    public int getIdcuentaempresa() {
        return idcuentaempresa;
    }

    public void setIdcuentaempresa(int idcuentaempresa) {
        this.idcuentaempresa = idcuentaempresa;
    }

    public double getPorcentaje_popularidad() {
        return porcentaje_popularidad;
    }

    public void setPorcentaje_popularidad(double porcentaje_popularidad) {
        this.porcentaje_popularidad = porcentaje_popularidad;
    }

    public boolean isCuenta_Delivery() {
        return cuenta_Delivery;
    }

    public void setCuenta_Delivery(boolean cuenta_Delivery) {
        this.cuenta_Delivery = cuenta_Delivery;
    }

    public float getCosto_delivery() {
        return costo_delivery;
    }

    public void setCosto_delivery(float costo_delivery) {
        this.costo_delivery = costo_delivery;
    }

    public String getDetalle_delivery() {
        return detalle_delivery;
    }

    public void setDetalle_delivery(String detalle_delivery) {
        this.detalle_delivery = detalle_delivery;
    }



    public int getIddistrito() {
        return iddistrito;
    }

    public void setIddistrito(int iddistrito) {
        this.iddistrito = iddistrito;
    }

    public String getTiempo_aproximado_entrega() {
        return tiempo_aproximado_entrega;
    }

    public void setTiempo_aproximado_entrega(String tiempo_aproximado_entrega) {
        this.tiempo_aproximado_entrega = tiempo_aproximado_entrega;
    }

    public int getHorario_inicio() {
        return horario_inicio;
    }

    public void setHorario_inicio(int horario_inicio) {
        this.horario_inicio = horario_inicio;
    }

    public int getHorario_fin() {
        return horario_fin;
    }

    public void setHorario_fin(int horario_fin) {
        this.horario_fin = horario_fin;
    }

    public boolean isTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(boolean tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getDetalle_tarjeta() {
        return detalle_tarjeta;
    }

    public void setDetalle_tarjeta(String detalle_tarjeta) {
        this.detalle_tarjeta = detalle_tarjeta;
    }

    public float getEstrella_empresa() {
        return estrella_empresa;
    }

    public void setEstrella_empresa(float estrella_empresa) {
        this.estrella_empresa = estrella_empresa;
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

    public String getDetalle_ubicacion() {
        return detalle_ubicacion;
    }

    public void setDetalle_ubicacion(String detalle_ubicacion) {
        this.detalle_ubicacion = detalle_ubicacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public float getPrecio_menu() {
        return precio_menu;
    }

    public void setPrecio_menu(float precio_menu) {
        this.precio_menu = precio_menu;
    }

    public float getMonto_descuento_menu() {
        return monto_descuento_menu;
    }

    public void setMonto_descuento_menu(float monto_descuento_menu) {
        this.monto_descuento_menu = monto_descuento_menu;
    }

    public int getIdusuariogeneral() {
        return idusuariogeneral;
    }

    public void setIdusuariogeneral(int idusuariogeneral) {
        this.idusuariogeneral = idusuariogeneral;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isCuentaactiva() {
        return cuentaactiva;
    }

    public void setCuentaactiva(boolean cuentaactiva) {
        this.cuentaactiva = cuentaactiva;
    }
}
