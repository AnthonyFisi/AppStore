package com.mimiperla.empresayego.Repository.Modelo.Gson;

import com.mimiperla.empresayego.Repository.Modelo.ProductoJOINregistroPedidoJOINpedido;
import com.mimiperla.empresayego.Repository.Modelo.Repartidor_Bi;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GsonEmpresa_historialDetail {

	@SerializedName("repartidor_bi")
	@Expose
	private Repartidor_Bi repartidor_bi;

	@SerializedName("listaProductos")
	@Expose
	private List<ProductoJOINregistroPedidoJOINpedido> listaProductos;


	public Repartidor_Bi getRepartidor_bi() {
		return repartidor_bi;
	}

	public void setRepartidor_bi(Repartidor_Bi repartidor_bi) {
		this.repartidor_bi = repartidor_bi;
	}

	public List<ProductoJOINregistroPedidoJOINpedido> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<ProductoJOINregistroPedidoJOINpedido> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	

}
