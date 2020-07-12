package com.example.empresayego.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.View.OrderUI.ProcessOrder.Detail.ProcesOrderActivity;
import com.example.empresayego.ViewModel.EmpresaViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class ProcesoOrdenActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    BottomNavigationView bottomNavView;
    DrawerLayout drawer;
    NavigationView navigationView;
    NavController navController;
    private TextView activity_proceso_orden_NOMBRE_EMPRESA;
    private Switch activity_proceso_orden_SWITCH_ENABLE;
    private Empresa mEmpresa;

    public final static String EMPRESA_OBJETO="com.example.empresayego.View.ProcesoOrdenActivity";

    private EmpresaViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceso_orden);


        drawer = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view_main);


        bottomNavView = findViewById(R.id.bottom_nav_view);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.nav_gallery, R.id.nav_home, R.id.nav_send,
                R.id.nav_share, R.id.nav_slideshow, R.id.nav_tools)
                .setDrawerLayout(drawer)
                .build();


        navController= Navigation.findNavController(this, R.id.nav_host_fragment_main);

        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(bottomNavView, navController);

        NavigationUI.setupWithNavController(navigationView,navController);


        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

                if(R.id.navigation_home == destination.getId() ||
                        R.id.navigation_dashboard == destination.getId() ||
                        R.id.navigation_notifications == destination.getId()
                ){
                    bottomNavView.setVisibility(View.VISIBLE);
                }else{
                    bottomNavView.setVisibility(View.GONE);

                }

                System.out.println(destination.getId()+ "ID DE LA FRAGMENT SELECCIONADO");

            }
        });


        reciveDataIntent();
        delcararWidgets();
        //setWidgets();
        changeDisponibilidadEmpresa();
        declararEmpreaViewModel();

    }


    private void declararEmpreaViewModel(){
        viewModel= ViewModelProviders.of(this).get(EmpresaViewModel.class);
        viewModel.init();
        viewModel.getEmpresaLiveData().observe(this, new Observer<Empresa>() {
            @Override
            public void onChanged(Empresa empresa) {

                if(empresa !=null){
                    Empresa.sEmpresa.setDisponible(empresa.isDisponible());
                }
            }
        });
    }



    private void delcararWidgets(){

        activity_proceso_orden_NOMBRE_EMPRESA=findViewById(R.id.activity_proceso_orden_NOMBRE_EMPRESA);
        activity_proceso_orden_SWITCH_ENABLE=findViewById(R.id.activity_proceso_orden_SWITCH_ENABLE);


    }

    private void setWidgets(Empresa empresa){

        System.out.println(empresa.getNombre_empresa() + "NOMRBE EMRPESA");
       // activity_proceso_orden_NOMBRE_EMPRESA.setText(empresa.getNombre_empresa());

        if(empresa.isDisponible()){
            activity_proceso_orden_SWITCH_ENABLE.setChecked(empresa.isDisponible());
            activity_proceso_orden_SWITCH_ENABLE.setBackgroundColor(Color.GREEN);

        }

    }

    private void changeDisponibilidadEmpresa(){
        activity_proceso_orden_SWITCH_ENABLE.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean response) {
                if(response){
                    viewModel.updateEmpresaDisponiblidad(mEmpresa.getIdempresa(),response);
                    activity_proceso_orden_SWITCH_ENABLE.setChecked(true);
                    activity_proceso_orden_SWITCH_ENABLE.setBackgroundColor(Color.GREEN);

                }else{
                    viewModel.updateEmpresaDisponiblidad(mEmpresa.getIdempresa(),response);
                    activity_proceso_orden_SWITCH_ENABLE.setChecked(false);
                    activity_proceso_orden_SWITCH_ENABLE.setBackgroundColor(Color.GRAY);


                }
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.proof, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    private void reciveDataIntent() {
        if (getIntent().getSerializableExtra(EMPRESA_OBJETO) != null) {
            mEmpresa = (Empresa) getIntent().getSerializableExtra(EMPRESA_OBJETO);
            System.out.println(mEmpresa.getNombre_empresa() + " -- "+mEmpresa.getDireccion_empresa());
            setWidgets(mEmpresa);

        }

    }

    public static Intent startIntentProceso(Context context, Empresa empresa){
        Intent intent= new Intent(context, ProcesoOrdenActivity.class);
        intent.putExtra(EMPRESA_OBJETO,empresa);
        return intent;
    }
}
