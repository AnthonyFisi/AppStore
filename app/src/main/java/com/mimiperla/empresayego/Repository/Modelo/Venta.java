package com.mimiperla.empresayego.Repository.Modelo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Venta {

    @SerializedName("idEmpresa")
    @Expose
    private int idEmpresa;

    @SerializedName("idtipopago")
    @Expose
    private int idtipopago;

    @SerializedName("idhorario")
    @Expose
    private int idhorario;

    @SerializedName("idubicacion")
    @Expose
    private int idubicacion;

    @SerializedName("idpedido")
    @Expose
    private int idpedido;

    @SerializedName("venta_fecha")
    @Expose
    private String venta_fecha;

    @SerializedName("venta_fechaentrega")
    @Expose
    private String  venta_fechaentrega;

    @SerializedName("venta_costodelivery")
    @Expose
    private float venta_costodelivery;

    @SerializedName("venta_costototal")
    @Expose
    private float venta_costototal;

    @SerializedName("comentario")
    @Expose
    private String comentario;

    @SerializedName("idestado_venta")
    @Expose
    private int idestado_venta;

    @SerializedName("idestado_pago")
    @Expose
    private int idestado_pago;

    @SerializedName("idtipo_envio")
    @Expose
    private int idtipo_envio;

    @SerializedName("idUsuario")
    @Expose
    private int idUsuario;

    @SerializedName("repsuesta_registro_venta")
    @Expose
    private boolean repsuesta_registro_venta;


    public Venta(int idEmpresa, int idtipopago, int idhorario, int idubicacion, int idpedido, String venta_fecha,String venta_fechaentrega, float venta_costodelivery, float venta_costototal, String comentario, int idestado_venta, int idestado_pago, int idtipo_envio, int idUsuario, boolean repsuesta_registro_venta) {
        this.idEmpresa = idEmpresa;
        this.idtipopago = idtipopago;
        this.idhorario = idhorario;
        this.idubicacion = idubicacion;
        this.idpedido = idpedido;
        this.venta_fecha = venta_fecha;
        this.venta_fechaentrega = venta_fechaentrega;
        this.venta_costodelivery = venta_costodelivery;
        this.venta_costototal = venta_costototal;
        this.comentario = comentario;
        this.idestado_venta = idestado_venta;
        this.idestado_pago = idestado_pago;
        this.idtipo_envio = idtipo_envio;
        this.idUsuario = idUsuario;
        this.repsuesta_registro_venta = repsuesta_registro_venta;
    }

    public Venta(){}

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdtipopago() {
        return idtipopago;
    }

    public void setIdtipopago(int idtipopago) {
        this.idtipopago = idtipopago;
    }

    public int getIdhorario() {
        return idhorario;
    }

    public void setIdhorario(int idhorario) {
        this.idhorario = idhorario;
    }

    public int getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(int idubicacion) {
        this.idubicacion = idubicacion;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public String getVenta_fecha() {
        return venta_fecha;
    }

    public void setVenta_fecha(String venta_fecha) {
        this.venta_fecha = venta_fecha;
    }

    public String getVenta_fechaentrega() {
        return venta_fechaentrega;
    }

    public void setVenta_fechaentrega(String venta_fechaentrega) {
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getIdestado_venta() {
        return idestado_venta;
    }

    public void setIdestado_venta(int idestado_venta) {
        this.idestado_venta = idestado_venta;
    }

    public int getIdestado_pago() {
        return idestado_pago;
    }

    public void setIdestado_pago(int idestado_pago) {
        this.idestado_pago = idestado_pago;
    }

    public int getIdtipo_envio() {
        return idtipo_envio;
    }

    public void setIdtipo_envio(int idtipo_envio) {
        this.idtipo_envio = idtipo_envio;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isRepsuesta_registro_venta() {
        return repsuesta_registro_venta;
    }

    public void setRepsuesta_registro_venta(boolean repsuesta_registro_venta) {
        this.repsuesta_registro_venta = repsuesta_registro_venta;
    }
}
