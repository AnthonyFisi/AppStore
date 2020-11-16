package com.example.empresayego.View;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.example.empresayego.View.DrawerUI.cerrar_sesion.CerrarSesionFragment;
import com.example.empresayego.View.DrawerUI.dataEmpresa.DataEmpresaFragment;
import com.example.empresayego.View.DrawerUI.productos.ProductoFragment;
import com.example.empresayego.View.DrawerUI.soporte.SoporteFragment;
import com.example.empresayego.View.OrderUI.NewOrder.ListNewOdersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import static com.example.empresayego.R.*;
import static com.example.empresayego.R.string.*;

public class ProcesoOrdenActivity extends AppCompatActivity implements ListNewOdersFragment.EmpresaEnable , SoporteFragment.BackToInicio, DataEmpresaFragment.BackToInicio, ProductoFragment.BackToInicio, CerrarSesionFragment.BackToInicio {
    private static final String RESPUESTA = "repsuesta";
    private AppBarConfiguration appBarConfiguration;
    BottomNavigationView bottomNavView;
    DrawerLayout drawer;
    NavigationView navigationView;
    NavController navController;
    private Empresa mEmpresa;

    public static final String CHANNEL_1_ID = "channel1";


    public static Pusher pusher;
    public static Channel channel,channel_proces ;

    public static SparseArray<CountDownTimer> countDownMap;

    public static List<Restaurante_Pedido> lista;

    private NavInflater navInflater;

    private NavGraph graph;

    private NavController nav;

    public final static String EMPRESA_OBJETO="com.example.empresayego.View.ProcesoOrdenActivity";
    private boolean respuesta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_proceso_orden);


        reciveDataIntent();

        NavHostFragment navHost= (NavHostFragment) getSupportFragmentManager().findFragmentById(id.nav_host_fragment_main);
        nav= navHost.getNavController();

        navInflater = nav.getNavInflater();
        graph = navInflater.inflate(navigation.mobile_navigation);

        if (respuesta) {
            graph.setStartDestination(id.navigation_proces_orders);
        } else {
            graph.setStartDestination(id.navigation_list_new_orders);
        }



        nav.setGraph(graph);

        lista=new ArrayList<>();

        countDownMap = new SparseArray<>();

        drawer = findViewById(id.drawer_layout);

        navigationView = findViewById(id.nav_view_main);


        bottomNavView = findViewById(id.bottom_nav_view);

        appBarConfiguration = new AppBarConfiguration.Builder(
                id.navigation_list_new_orders, id.navigation_proces_orders,
                id.navigation_ready_order, id.nav_cerrar_sesion, id.nav_producto, id.nav_soporte, id.nav_data_empresa, id.nav_historial, id.nav_deuda)
                .build();



        navController= Navigation.findNavController(this, id.nav_host_fragment_main);



        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(bottomNavView, navController);

        NavigationUI.setupWithNavController(navigationView,navController);



        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {


            if(id.navigation_list_new_orders == destination.getId() ||
                    id.navigation_proces_orders == destination.getId() ||
                    id.navigation_ready_order == destination.getId()|| id.nav_historial==destination.getId()
            ){
                bottomNavView.setVisibility(View.VISIBLE);
            }else{

                bottomNavView.setVisibility(View.GONE);
            }


        });


      //  reciveDataIntent();

        initDataDrawer();

        settingPusher();

        funPusher();

    }

    private void settingPusher(){
        PusherOptions options = new PusherOptions();
        options.setCluster(getString(pusher_region));
        pusher = new Pusher( getString(pusher_apikey), options);
        channel= pusher.subscribe("canal-orden-reciente-"+Empresa.sEmpresa.getIdempresa());
        channel_proces=pusher.subscribe("canal-orden-proces-"+Empresa.sEmpresa.getIdempresa());
    }

    public void initDataDrawer(){

        View hView =  navigationView.getHeaderView(0);
        TextView nombre_usuario=hView.findViewById(id.nombre_usuario);
        TextView correo_usuario=hView.findViewById(id.correo_usuario);

        Empresa cliente_bi=Empresa.sEmpresa;
        correo_usuario.setText(cliente_bi.getCorreo());
        nombre_usuario.setText(cliente_bi.getNombre());

        ImageView imageView_USUARIO = hView.findViewById(id.imageView_USUARIO);

        if (cliente_bi.getFoto()!= null) {
            String imageUrl = cliente_bi.getFoto()
                    .replace("http://", "https://");

            Glide.with(this)
                    .load(imageUrl)
                    .into(imageView_USUARIO);
        }
    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.proof, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, id.nav_host_fragment_main);
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

        respuesta=getIntent().getBooleanExtra(RESPUESTA,false);

    }

    public static Intent startIntentProcesoOrdenActivity(Context context,boolean respuesta){
        Intent intent= new Intent(context, ProcesoOrdenActivity.class);
        intent.putExtra(EMPRESA_OBJETO,respuesta);
        return intent;
    }


    @Override
    public void stateEmpresa(boolean state) {
        if(state){
            bottomNavView.setVisibility(View.GONE);
        }else {
            bottomNavView.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void back() {
        graph.setStartDestination(id.navigation_list_new_orders);
        nav.setGraph(graph);
    }


    private void funPusher() {
        ProcesoOrdenActivity.channel.bind("my-event", (channelName, eventName, data) -> {

            JsonParser parser = new JsonParser();
            JsonElement mJson = parser.parse(data);
            Gson gson = new Gson();
            Restaurante_Pedido object = gson.fromJson(mJson, Restaurante_Pedido.class);

            createNotificationChannel();

            String titulo="Nuevo pedido";

            String message="Pedido"+object.getIdventa()+" : "+object.getListaProductos().size()+" productos , total  S/"+object.getVenta_costototal();

            String pattern = "hh:mm:ss a";
            DateFormat dateFormat = new SimpleDateFormat(pattern);
            String fecha=dateFormat.format(object.getVentafecha().toString());


            publishNotification(titulo,message,fecha);
        });
        ProcesoOrdenActivity.pusher.connect();
    }


    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name="Notification";
            NotificationChannel notificationChannel = new NotificationChannel(
                    CHANNEL_1_ID,
                    name,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

        }

    }

    private void publishNotification(String titulo,String mensaje,String horario){

        Intent i=ProcesoOrdenActivity. startIntentProcesoOrdenActivity(this,true);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(mipmap.ic_launcher)

                .setContentIntent(pendingIntent)
                .setContentTitle(titulo)
                .setContentText(mensaje)
                .setSubText(horario)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setSound(Uri.parse("android.resource://"
                        + getApplicationContext().getPackageName() + "/" + raw.soundorden))
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

        notificationManager.notify(1, notification);
    }


}
