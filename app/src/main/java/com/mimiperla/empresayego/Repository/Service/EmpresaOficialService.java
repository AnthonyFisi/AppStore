package com.mimiperla.empresayego.Repository.Service;

import com.mimiperla.empresayego.Repository.Modelo.EmpresaOficial;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmpresaOficialService {

    @POST("/EmpresaOficialController/updateDisponibilidad/{idEmpresa}/{disponibilidad}")
    Call<EmpresaOficial> updateEmpresaState(@Header("Authorization") String token, @Path("idEmpresa") int idEmpresa, @Path("disponibilidad") boolean disponibilidad);

    @POST("/EmpresaOficialController/updateTelefono/{idEmpresa}/{numTelefono}")
    Call<EmpresaOficial> updateTelefonoState(@Header("Authorization") String token, @Path("idEmpresa") int idEmpresa, @Path("numTelefono") String numTelefono);

    @POST("/EmpresaOficialController/updateCelular/{idEmpresa}/{numCelular}")
    Call<EmpresaOficial> updateCelularState(@Header("Authorization") String token, @Path("idEmpresa") int idEmpresa, @Path("numCelular") String numCelular);

    @POST("/EmpresaOficialController/updateHorario/{idEmpresa}/{horarioInicio}/{horarioFin}")
    Call<EmpresaOficial> updateHorarioState(@Header("Authorization") String token, @Path("idEmpresa") int idEmpresa, @Path("horarioInicio") int horarioInicio, @Path("horarioFin") int horarioFin);

    @POST("/EmpresaOficialController/updateTiempo/{idEmpresa}/{tiempo}")
    Call<EmpresaOficial> updateTiempoState(@Header("Authorization") String token, @Path("idEmpresa") int idEmpresa, @Path("tiempo") String tiempo);

    @POST("/EmpresaOficialController/updateDescripcion")
    Call<EmpresaOficial> updateDescripcionState(@Header("Authorization") String token, @Body EmpresaOficial empresa);


    //UTILIZAMOS OTRO TIPO DE MODELO QUE SE PARECE A EMPRESA

    @POST("/EmpresaOficialController/updateDescuento/{idempresa}/{descuento}")
    Call<EmpresaOficial> updatePrecioMenu(@Header("Authorization") String token, @Path("idempresa") int idempresa, @Path("descuento") float descuento);

}
