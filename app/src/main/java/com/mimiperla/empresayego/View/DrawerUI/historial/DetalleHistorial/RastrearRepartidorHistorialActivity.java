package com.mimiperla.empresayego.View.DrawerUI.historial.DetalleHistorial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Delivery;
import com.mimiperla.empresayego.Repository.Modelo.Empresa;
import com.mimiperla.empresayego.Repository.Modelo.Empresa_historial;
import com.mimiperla.empresayego.Repository.Modelo.Repartidor_Bi;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class RastrearRepartidorHistorialActivity extends AppCompatActivity implements OnMapReadyCallback {

    public final static String RESTAURANTE_PEDIDO="com.example.empresayego.View.OrderUI.NewOrder.Detail.RestauranteObjeto";

    public final static String TAG="yegooooooooooooooooooo";
    private static final String REPARTIDOR_BI = "repartidor";

    private Delivery delivery;

    private GoogleMap mMap;

    FirebaseFirestore db ;
    private Repartidor_Bi mRepartidor_bi;
    private Empresa_historial mEmpresa_historial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rastrear_repartidor_historial);
        db = FirebaseFirestore.getInstance();
        //Asign variable
        reciveDataIntent();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);

        CardView  back_inicio=findViewById(R.id.back_inicio);
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
        DocumentReference docRef = db.collection("UsuariosDelivery").document(String.valueOf(mRepartidor_bi.getIdusuariogeneral()));

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




        //mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 300));

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



    public static Intent newIntentRastrearRepartidorHistorialActivity(Context context, Empresa_historial restaurante_pedido, Repartidor_Bi repartidor_bi){
        Intent intent= new Intent(context, RastrearRepartidorHistorialActivity.class);
        intent.putExtra(RESTAURANTE_PEDIDO,restaurante_pedido);
        intent.putExtra(REPARTIDOR_BI,repartidor_bi);

        return intent;
    }


    private void reciveDataIntent(){
        if(getIntent().getSerializableExtra(RESTAURANTE_PEDIDO) !=null){
            mEmpresa_historial=(Empresa_historial) getIntent().getSerializableExtra(RESTAURANTE_PEDIDO);
            // System.out.println( "DATOS" +mRestaurante_pedido.getUsuario_nombre());

        }

        if(getIntent().getSerializableExtra(REPARTIDOR_BI) !=null){
            mRepartidor_bi=(Repartidor_Bi) getIntent().getSerializableExtra(REPARTIDOR_BI);
            // System.out.println( "DATOS" +mRestaurante_pedido.getUsuario_nombre());

        }
    }
}
