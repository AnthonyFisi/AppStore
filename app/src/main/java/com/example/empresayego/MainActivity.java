package com.example.empresayego;

import android.content.Intent;

import android.os.Build;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.empresayego.Proof.DisplayActivity;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.example.empresayego.View.MapsActivity;
import com.example.empresayego.View.ProcesoOrdenActivity;
import com.example.empresayego.ViewModel.EmpresaViewModel;


import android.os.Handler;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import androidx.navigation.ui.AppBarConfiguration;

import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;


import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
   // public static Pusher pusher;
   // public static  Channel channel ;
    private int idEmpresa=23;
    private Button mButton;

    private  Empresa mEmpresa;

    private View layoutEncontrarPers;
    private ImageView imgMisFotos,imgAnim,imgAnim2;
    private Handler handlerAnimationCIMG;
    public static List<Restaurante_Pedido> lista=new ArrayList<>();
    public static  boolean dataEmpty=false;




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        declararHttpRequestEmpresa();
   /*     PusherOptions options = new PusherOptions();
        options.setCluster("us2");

        pusher = new Pusher( "18c8170377c406cfcf3a", options);
        channel= MainActivity.pusher.subscribe("canal-orden-reciente-"+idEmpresa);
*/
        mButton=findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent= ProcesoOrdenActivity.startIntentProceso(MainActivity.this,mEmpresa);
                //startActivity(intent);
            }
        });


        init();

        startTask();


        Glide.with(getBaseContext()).load(R.drawable.time).apply(new RequestOptions().
                        circleCrop()).into(imgMisFotos);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //startTask();
                Intent intent= new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);

            }
        });


        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
    public void onClick(View view) {


        //endTask();

        Intent intent= new Intent(MainActivity.this, DisplayActivity.class);
        startActivity(intent);
    }
});
        }


    private void init(){
        this.handlerAnimationCIMG= new Handler();
        this.layoutEncontrarPers=findViewById(R.id.layoutPers);
        this.imgMisFotos=findViewById(R.id.imgMiFoto);
        this.imgAnim=findViewById(R.id.imgAnim);
        this.imgAnim2=findViewById(R.id.imgAnim2);

    }


    private void declararHttpRequestEmpresa(){
        EmpresaViewModel viewModel = ViewModelProviders.of(this).get(EmpresaViewModel.class);
        viewModel.init();
        viewModel.getEmpresaLiveData().observe(this, new Observer<Empresa>() {
            @Override
            public void onChanged(Empresa empresa) {

                if(empresa !=null){
                    mEmpresa=empresa;
                    System.out.println("Empresa" + mEmpresa.getDireccion_empresa() + " --- "+ mEmpresa.getNombre_empresa());
                    Empresa.sEmpresa=mEmpresa;
                }
            }
        });

        viewModel.searchEmpresaById(23);
    }

    private void startTask(){
        this.runnableAnim.run();
        this.layoutEncontrarPers.setVisibility(View.VISIBLE);

    }

    private void endTask(){

        this.handlerAnimationCIMG.removeCallbacks(runnableAnim);
        this.layoutEncontrarPers.setVisibility(View.GONE);
    }

    private Runnable runnableAnim= new Runnable() {
        @Override
        public void run() {
            imgAnim.animate().scaleX(4f).scaleY(4f).alpha(0f).setDuration(1000).withEndAction(new Runnable() {
                @Override
                public void run() {
                    imgAnim.setScaleX(1f);
                    imgAnim.setScaleY(1f);
                    imgAnim.setAlpha(1f);
                }
            });


            imgAnim.animate().scaleX(4f).scaleY(4f).alpha(0f).setDuration(700).withEndAction(new Runnable() {
                @Override
                public void run() {
                    imgAnim2.setScaleX(1f);
                    imgAnim2.setScaleY(1f);
                    imgAnim2.setAlpha(1f);
                }
            });

            handlerAnimationCIMG.postDelayed(runnableAnim,1500);
        }
    };

}
