package com.mimiperla.empresayego.Login.ViewModel;

import android.app.Application;

import com.mimiperla.empresayego.Login.Modelo.JwtResponse;
import com.mimiperla.empresayego.Login.Modelo.LoginRequest;
import com.mimiperla.empresayego.Login.Repositorio.LoginRequestRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class LoginRequestViewModel extends AndroidViewModel {

    private LoginRequestRepository mLoginRequestRepository;

    private LiveData<JwtResponse> mJwtResponseLiveData;


    public LoginRequestViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){

        mLoginRequestRepository=new LoginRequestRepository();
        mJwtResponseLiveData=mLoginRequestRepository.getJwtResponseMutableLiveData();
    }

    public void registrarUsuarioEmpresa(LoginRequest loginRequest ){
        mLoginRequestRepository.registrarUsuarioEmpresa(loginRequest);
    }

    public void signoutUsuarioEmpresa(LoginRequest loginRequest ){
        mLoginRequestRepository.signoutUsuarioEmpresa(loginRequest);
    }



    public LiveData<JwtResponse> getJwtResponseLiveData(){
        return mJwtResponseLiveData;
    }

    }
