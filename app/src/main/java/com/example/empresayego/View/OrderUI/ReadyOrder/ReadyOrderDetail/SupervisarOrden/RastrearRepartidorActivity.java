package com.example.empresayego.View.OrderUI.ReadyOrder.ReadyOrderDetail.SupervisarOrden;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Delivery;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RastrearRepartidorActivity extends AppCompatActivity implements OnMapReadyCallback {

    public final static String RESTAURANTE_PEDIDO="com.example.empresayego.View.OrderUI.NewOrder.Detail.RestauranteObjeto";

    public final static String TAG="yegooooooooooooooooooo";

    private Restaurante_Pedido mRestaurante_pedido;

    private Delivery delivery;

    private GoogleMap mMap;

    FirebaseFirestore db ;

    private CardView back_inicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rastrear_repartidor);
        db = FirebaseFirestore.getInstance();
        //Asign variable
        reciveDataIntent();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);

        back_inicio=findViewById(R.id.back_inicio);
        back_inicio.setOnClickListener(v->{
            onBackPressed();
        });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        listenChangeLocation();
    }



    private void listenChangeLocation(){
        DocumentReference docRef = db.collection("UsuariosDelivery").document(String.valueOf(mRestaurante_pedido.getRepartidor_bi().getIdusuariogeneral()));


        docRef.addSnapshotListener((documentSnapshot, e) -> {

            if (e != null) {
                System.err.println("Listen failed: " + e);
                return;
            }

            if (documentSnapshot != null && documentSnapshot.exists()) {

                System.out.println("Current data: " + documentSnapshot.getData());

                drawDeliveryPosition(documentSnapshot);

            } else {
                System.out.print("Current data: null");
            }

        });
    }


    private void drawDeliveryPosition(DocumentSnapshot data){

        HashMap<String, Object> value = (   HashMap<String, Object>) data.getData();

        String locationString=value.get("location").toString();

        String[] coordenada=locationString.split(",");

        double lat = Double.parseDouble(coordenada[0]);

        double lng = Double.parseDouble(coordenada[1]);

        LatLng location = new LatLng(lat, lng);


        MarkerOptions markerOptions=new MarkerOptions();

        markerOptions.position(location);

        // markerOptions.title(location.latitude+ " : "+location.longitude);

        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.moto));



        mMap.addMarker(markerOptions).showInfoWindow();

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,18));


        double lat2 = Double.parseDouble(Empresa.sEmpresa.getMaps_coordenada_x());

        double lng2 = Double.parseDouble(Empresa.sEmpresa.getMaps_coordenada_y());


        LatLng location2 = new LatLng(lat2, lng2);


        MarkerOptions markerOptions2=new MarkerOptions();

        markerOptions2.position(location2);

        markerOptions2.title(Empresa.sEmpresa.getNombre_empresa());


        markerOptions2.icon(BitmapDescriptorFactory.fromResource(R.drawable.store));


        mMap.addMarker(markerOptions2);


        mMap.clear();



    }



    public static Intent newIntentRastrearRepartidorActivity(Context context, Restaurante_Pedido restaurante_pedido){
        Intent intent= new Intent(context, RastrearRepartidorActivity.class);
        intent.putExtra(RESTAURANTE_PEDIDO,restaurante_pedido);
        return intent;
    }


    private void reciveDataIntent(){
        if(getIntent().getSerializableExtra(RESTAURANTE_PEDIDO) !=null){
            mRestaurante_pedido=(Restaurante_Pedido) getIntent().getSerializableExtra(RESTAURANTE_PEDIDO);
            // System.out.println( "DATOS" +mRestaurante_pedido.getUsuario_nombre());

        }
    }
}
