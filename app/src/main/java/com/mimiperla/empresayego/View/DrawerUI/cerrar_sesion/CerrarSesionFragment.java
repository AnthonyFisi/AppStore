package com.mimiperla.empresayego.View.DrawerUI.cerrar_sesion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mimiperla.empresayego.Login.IniciarSesionActivity;
import com.mimiperla.empresayego.Login.Modelo.JwtResponse;
import com.mimiperla.empresayego.Login.Modelo.LoginRequest;
import com.mimiperla.empresayego.Login.SessionPrefs;
import com.mimiperla.empresayego.Login.ViewModel.LoginRequestViewModel;
import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Empresa;

import org.jetbrains.annotations.NotNull;

public class CerrarSesionFragment extends Fragment {


    private LoginRequestViewModel viewModel;

    private BackToInicio mBackToInicio;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_tools, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sendRequest();

        signout();
    }

    private void sendRequest(){
        viewModel=new ViewModelProvider(this).get(LoginRequestViewModel.class);
        viewModel.init();
        viewModel.getJwtResponseLiveData().observe(getViewLifecycleOwner(), new Observer<JwtResponse>() {
            @Override
            public void onChanged(JwtResponse jwtResponse) {
                if(jwtResponse!=null){

                    SessionPrefs.get(getContext()).logOut();
                    Intent intent= new Intent(getContext(),IniciarSesionActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    requireActivity().finish();

                }else{

                    backToInicio();
                }
            }
        });
    }

    private void signout(){
        LoginRequest loginRequest= new LoginRequest();
        loginRequest.setUsername(Empresa.sEmpresa.getCorreo());
        loginRequest.setPassword("signout");
        viewModel.signoutUsuarioEmpresa(loginRequest);
    }



    private void backToInicio() {
        mBackToInicio.back();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mBackToInicio = (CerrarSesionFragment.BackToInicio) context;
    }

    public interface BackToInicio{
        void  back();
    }


}