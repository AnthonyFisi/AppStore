package com.example.empresayego.Repository.Modelo.Gson;

import java.util.List;
import com.example.empresayego.Repository.Modelo.Empresa_historial;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Empresa_historialGson {


	@SerializedName("listaEmpresaHistorial")
	@Expose
	private List<Empresa_historial> listaEmpresaHistorial;

	public List<Empresa_historial> getListaEmpresaHistorial() {
		return listaEmpresaHistorial;
	}

	public void setListaEmpresaHistorial(List<Empresa_historial> listaEmpresaHistorial) {
		this.listaEmpresaHistorial = listaEmpresaHistorial;
	}

	

}
