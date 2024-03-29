package com.mimiperla.empresayego.Login.Service;

import com.mimiperla.empresayego.Login.Modelo.JwtResponse;
import com.mimiperla.empresayego.Login.Modelo.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginRequestService {

    @POST("/api/auth/signinEmpresa")
    Call<JwtResponse> registrarUsuarioEmpresa(@Body LoginRequest loginRequest );


    @POST("/api/auth/signoutEmpresa")
    Call<JwtResponse> signoutUsuarioEmpresa(@Body LoginRequest loginRequest );


}
