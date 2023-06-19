package com.mimiperla.empresayego.Login;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;

import com.mimiperla.empresayego.Login.Modelo.JwtResponse;
import com.mimiperla.empresayego.Login.ViewModel.Empresa_biViewModel;
import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Empresa;
import com.mimiperla.empresayego.View.ProcesoOrdenActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class SplashActivity extends AppCompatActivity implements MyReceiver.ConnectivityReciverListener {

    private boolean response=false;

    private Empresa_biViewModel viewModel;

    private JwtResponse mJwtResponse;

    boolean isConnected=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        viewModel= new ViewModelProvider(this).get(Empresa_biViewModel.class);
        viewModel.init();

        checkInternetConnection();


        if (SessionPrefs.get(this).isLoggedIn()) {

            response=true;

            mJwtResponse=SessionPrefs.get(this).data();

           viewModel.getEmpresaById(SessionPrefs.get(this).getTokenPrefs(),mJwtResponse.getId().intValue());

        }else{

            //INICIAR SESION ACTIVITY

            new Handler().postDelayed(() -> {
                Intent intent= new Intent(SplashActivity.this,IniciarSesionActivity.class);
                startActivity(intent);
                finish();
            },3000);
        }

        loadData();

    }


    private void loadData(){


        if(response){


            viewModel.getEmpresa_biLiveData().observe(this, empresa_bi -> {
                if(isConnected){

                    if(empresa_bi!=null){

                        //VA IR DIRECTAMENTE AL HOME DE LA APLICACION

                        Empresa.sEmpresa=empresa_bi;

                        Intent intent= ProcesoOrdenActivity.startIntentProcesoOrdenActivity(SplashActivity.this,false);
                        startActivity(intent);
                        finish();

                    }else {

                        //VA IR A INICIO DE SESION SI ES QUE HUBIERA ALGUN PROBLEMA DE INTERNET O ALGUN OTRO EN EL SERVIDOR

                        isConnected=false;
                        Intent intent= new Intent(SplashActivity.this,IniciarSesionActivity.class);
                        startActivity(intent);
                        finish();

                    }

                }else {

                    Intent intent= new Intent(getApplicationContext(), NetworkActivity.class);
                    startActivity(intent);
                    finish();


                }
            });

        }

    }



    private void checkInternetConnection() {

       isConnected=MyReceiver.isConnected();

    }

    @Override
    protected void onResume() {
        super.onResume();
        final IntentFilter intentFilter= new IntentFilter();

        intentFilter.addAction(ConnectivityManager.EXTRA_CAPTIVE_PORTAL);

        MyReceiver myReciver= new MyReceiver();

        registerReceiver(myReciver,intentFilter);

        MyApp.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        this.isConnected=isConnected;
    }
}
