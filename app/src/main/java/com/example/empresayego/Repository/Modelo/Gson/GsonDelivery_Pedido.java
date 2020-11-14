package com.example.empresayego.Repository.Modelo.Gson;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.empresayego.Repository.Modelo.Delivery_Pedido;
import com.example.empresayego.Repository.Modelo.ProductoJOINregistroPedidoJOINpedido;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GsonDelivery_Pedido implements Serializable, Parcelable {

    @SerializedName("delivery_information")
    @Expose
    private Delivery_Pedido delivery_information;

    @SerializedName("productos")
    @Expose
    private List<ProductoJOINregistroPedidoJOINpedido> productos;

    protected GsonDelivery_Pedido(Parcel in) {
    }

    public GsonDelivery_Pedido(){}

    public static final Creator<GsonDelivery_Pedido> CREATOR = new Creator<GsonDelivery_Pedido>() {
        @Override
        public GsonDelivery_Pedido createFromParcel(Parcel in) {
            return new GsonDelivery_Pedido(in);
        }

        @Override
        public GsonDelivery_Pedido[] newArray(int size) {
            return new GsonDelivery_Pedido[size];
        }
    };

    public Delivery_Pedido getDelivery_information() {
        return delivery_information;
    }

    public void setDelivery_information(Delivery_Pedido delivery_information) {
        this.delivery_information = delivery_information;
    }

    public List<ProductoJOINregistroPedidoJOINpedido> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoJOINregistroPedidoJOINpedido> productos) {
        this.productos = productos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
