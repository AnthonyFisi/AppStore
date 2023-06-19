package com.mimiperla.empresayego.Repository.Service;

import com.mimiperla.empresayego.Repository.Modelo.Empresa;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmpresaService {

    @GET("/EmpresaController/findByEmpresa/{idEmpresa}")
    Call<Empresa> searchEmpresaById(@Header ("Authorization")String token,@Path("idEmpresa")int idEmpresa);


    @POST("/EmpresaController/updateDisponibilidad/{idEmpresa}/{disponibilidad}")
    Call<Empresa> updateEmpresaState(@Header ("Authorization")String token,@Path("idEmpresa")int idEmpresa,@Path("disponibilidad") boolean disponibilidad);

    @POST("/EmpresaController/updateTelefono/{idEmpresa}/{numTelefono}")
    Call<Empresa> updateTelefonoState(@Header ("Authorization")String token,@Path("idEmpresa")int idEmpresa,@Path("numTelefono")String numTelefono);

    @POST("/EmpresaController/updateCelular/{idEmpresa}/{numCelular}")
    Call<Empresa> updateCelularState(@Header ("Authorization")String token,@Path("idEmpresa")int idEmpresa,@Path("numCelular") String numCelular);

    @POST("/EmpresaController/updateHorario/{idEmpresa}/{horarioInicio}/{horarioFin}")
    Call<Empresa> updateHorarioState(@Header ("Authorization")String token,@Path("idEmpresa")int idEmpresa,@Path("horarioInicio") int horarioInicio,@Path("horarioFin")int horarioFin);

    @POST("/EmpresaController/updateTiempo/{idEmpresa}/{tiempo}")
    Call<Empresa> updateTiempoState(@Header ("Authorization")String token,@Path("idEmpresa")int idEmpresa,@Path("tiempo") String tiempo);

    @POST("/EmpresaController/updateDescripcion")
    Call<Empresa> updateDescripcionState(@Header ("Authorization")String token,@Body Empresa empresa);


    //UTILIZAMOS OTRO TIPO DE MODELO QUE SE PARECE A EMPRESA

    @POST("/EmpresaOficialController/updateDescuento/{idempresa}/{descuento}")
    Call<Empresa> updatePrecioMenu(@Header ("Authorization")String token,@Path("idempresa")int idempresa,@Path("descuento")float descuento);
}



