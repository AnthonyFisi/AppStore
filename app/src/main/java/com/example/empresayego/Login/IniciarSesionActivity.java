package com.example.empresayego.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.empresayego.Login.Modelo.Empresa_bi;
import com.example.empresayego.Login.Modelo.JwtResponse;
import com.example.empresayego.Login.Modelo.LoginRequest;
import com.example.empresayego.Login.Service.Empresa_biService;
import com.example.empresayego.Login.ViewModel.Empresa_biViewModel;
import com.example.empresayego.Login.ViewModel.LoginRequestViewModel;
import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.View.ProcesoOrdenActivity;

public class IniciarSesionActivity extends AppCompatActivity {

    private EditText usernameEditText,passwordEditText;

    private CardView loginButton;

    private ProgressBar loadingProgressBar;

    private LoginRequestViewModel viewModel;

    private Empresa_biViewModel viewModelEmpresa;

    private LinearLayout layout_correo,layout_constrasena;

    private TextView error_correo,error_contrasena,load_text;

    private LinearLayout show_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        declararWidgets();
        sendRequest();
        iniciarSesion();
        getEmpresaData();
        changeStateEditext();
        activeLayout();
        showPassword();

    }



    private void declararWidgets(){
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loadingProgressBar= findViewById(R.id.loadingProgressBar);
        loadingProgressBar.setVisibility(View.GONE);

        layout_correo=findViewById(R.id.layout_correo);
        layout_constrasena=findViewById(R.id.layout_contrasena);
        error_correo=findViewById(R.id.error_correo);
        error_contrasena=findViewById(R.id.error_contrasena);
        load_text=findViewById(R.id.load_text);

        show_password=findViewById(R.id.show_password);
        show_password.setVisibility(View.GONE);

        usernameEditText.setCursorVisible(false);
        passwordEditText.setCursorVisible(false);

    }

    private void sendRequest(){
        viewModel=new ViewModelProvider(this).get(LoginRequestViewModel.class);
        viewModel.init();
        viewModel.getJwtResponseLiveData().observe(this, new Observer<JwtResponse>() {
            @Override
            public void onChanged(JwtResponse jwtResponse) {
                if(jwtResponse!=null){

                    SessionPrefs.get(IniciarSesionActivity.this).saveJwtResponse(jwtResponse);
                    SessionPrefs.get(IniciarSesionActivity.this).data();
                    viewModelEmpresa.getEmpresaById(SessionPrefs.get(IniciarSesionActivity.this).getTokenPrefs(),jwtResponse.getId().intValue());
                }else{
                    loadingProgressBar.setVisibility(View.GONE);
                    afterIniciarSession();
                    Toast.makeText(IniciarSesionActivity.this,"Verificar correo y contraseña ingresada",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getEmpresaData(){
        viewModelEmpresa=new ViewModelProvider(this).get(Empresa_biViewModel.class);
        viewModelEmpresa.init();
        viewModelEmpresa.getEmpresa_biLiveData().observe(this, new Observer<Empresa>() {
            @Override
            public void onChanged(Empresa empresa_bi) {
                loadingProgressBar.setVisibility(View.GONE);
                if(empresa_bi!=null){

                    Empresa.sEmpresa=empresa_bi;

                    Intent intent= ProcesoOrdenActivity.startIntentProcesoOrdenActivity(IniciarSesionActivity.this,false);
                    startActivity(intent);
                    finish();

                }else{
                    afterIniciarSession();

                    Toast.makeText(IniciarSesionActivity.this,"Tuvimos problemas,volver a intentarlo porfavor",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void iniciarSesion(){

        loginButton.setOnClickListener(v->{

            boolean respuesta1=false;
            boolean respuesta2=false;


            if(usernameEditText.getText().length()>0 ){
                respuesta1=true;

            }else {
                error_correo.setVisibility(View.VISIBLE);
            }
            if(passwordEditText.getText().length()>0){
                respuesta2=true;
            }else {
                error_contrasena.setVisibility(View.VISIBLE);
            }

            if(respuesta1 && respuesta2){

                loadingProgressBar.setVisibility(View.VISIBLE);
                load_text.setVisibility(View.GONE);

                LoginRequest loginRequest= new LoginRequest();
                loginRequest.setUsername(usernameEditText.getText().toString());
                loginRequest.setPassword(passwordEditText.getText().toString());
                viewModel.registrarUsuarioEmpresa(loginRequest);

            }else {
                Toast.makeText(this, "Ingresar datos correctos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void changeStateEditext(){
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length()>0){
                    layout_correo.setBackground(getResources().getDrawable(R.drawable.border_inicio_sesion_able));
                    error_correo.setVisibility(View.GONE);
                }else {
                    layout_correo.setBackground(getResources().getDrawable(R.drawable.border_inicio_sesion));

                }
            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length()>0){
                    layout_constrasena.setBackground(getResources().getDrawable(R.drawable.border_inicio_sesion_able));
                    error_contrasena.setVisibility(View.GONE);
                    show_password.setVisibility(View.VISIBLE);
                }else {
                    show_password.setVisibility(View.GONE);
                    layout_constrasena.setBackground(getResources().getDrawable(R.drawable.border_inicio_sesion));
                }
            }
        });
    }

    private void activeLayout(){
        usernameEditText.setOnClickListener(v->{
            layout_correo.setBackground(getResources().getDrawable(R.drawable.border_inicio_sesion_able));
            layout_constrasena.setBackground(getResources().getDrawable(R.drawable.border_inicio_sesion));
            usernameEditText.setCursorVisible(true);
        });

        passwordEditText.setOnClickListener(v->{
            layout_correo.setBackground(getResources().getDrawable(R.drawable.border_inicio_sesion));
            layout_constrasena.setBackground(getResources().getDrawable(R.drawable.border_inicio_sesion_able));
            passwordEditText.setCursorVisible(true);
        });
    }

    private void afterIniciarSession(){
        usernameEditText.setText("");
        passwordEditText.setText("");

        layout_correo.setBackground(getResources().getDrawable(R.drawable.border_inicio_sesion));
        layout_constrasena.setBackground(getResources().getDrawable(R.drawable.border_inicio_sesion));

        load_text.setVisibility(View.VISIBLE);

    }

    private void showPassword(){

        show_password.setOnClickListener(v->{

            if(show_password.getTag().toString().equals("able")){
                passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }else {
                passwordEditText.setTransformationMethod(null);
            }

        });



    }
}
