package com.mimiperla.empresayego.Repository.Modelo.Gson;

import com.mimiperla.empresayego.Repository.Modelo.Repartidor_Bi;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GsonRepartidor_Bi {

    @SerializedName("listaRepartidor")
    @Expose
    private List<Repartidor_Bi> listaRepartidor;

    public List<Repartidor_Bi> getListaRepartidor() {
        return listaRepartidor;
    }

    public void setListaRepartidor(List<Repartidor_Bi> listaRepartidor) {
        this.listaRepartidor = listaRepartidor;
    }


}
