package com.example.empresayego.Proof;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import androidx.fragment.app.FragmentActivity;

public class DisplayActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = DisplayActivity.class.getSimpleName();
    private HashMap<String, Marker> mMarkers = new HashMap<>();
    private GoogleMap mMap;
    private DatabaseReference ref;


    Double latDes=Double.valueOf("-12.168431");
    Double logDes=Double.valueOf("-76.926879");

    private Restaurante_Pedido mRestaurante_pedido;

    private TextView activity_maps_tiempo,activity_maps_distancia;

    private LatLng locationTienda= new LatLng(latDes,logDes);

    public final static String RESTAURANTE_PEDIDO_DISPLAY="com.example.empresayego.View.OrderUI.Ubicacion";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        declararFirebaseInstance();

        reciveDataIntent();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        declararWidget();


    }

    private void declararFirebaseInstance(){
         ref= FirebaseDatabase.getInstance().getReference("Delivery")
                 .child("Usuarios")
                 .child(String.valueOf(mRestaurante_pedido.getIdrepartidor()))
                 .child("viajes")
                 .child(String.valueOf(mRestaurante_pedido.getIdventa()));
    }

    private void declararWidget(){

        activity_maps_distancia=findViewById(R.id.activity_maps_distancia);
        activity_maps_tiempo=findViewById(R.id.activity_maps_tiempo);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        subscribeToUpdates();
        System.out.println("CUANTAS VECES ENTRO POR ACA ON MAP READY");

        //calculateDistanceAndTime();
    }


    private void subscribeToUpdates() {

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                setMarker(dataSnapshot,false);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                setMarker(dataSnapshot,true);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void setMarker(DataSnapshot dataSnapshot,boolean update) {
        // When a location update is received, put or update
        // its value in mMarkers, which contains all the markers
        // for locations received, so that we can build the
        // boundaries required to show them all on the map at once

        mMap.clear();


        String key = dataSnapshot.getKey();




        HashMap<String, Object> value = (   HashMap<String, Object>) dataSnapshot.getValue();
        double lat = Double.parseDouble(value.get("latitude").toString());
        double lng = Double.parseDouble(value.get("longitude").toString());

        System.out.println( "latitude" + lat + " longitude" + lng);
        LatLng location = new LatLng(lat, lng);


        MarkerOptions markerOptions=new MarkerOptions();

        markerOptions.position(location);

        markerOptions.title(location.latitude+ " : "+location.longitude);

        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.moto));

        //mMap.clear();


        mMap.addMarker(markerOptions).showInfoWindow();

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,18));


        //mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 300));

        MarkerOptions markerOptions2=new MarkerOptions();

        markerOptions2.position(locationTienda);

        markerOptions2.title("Mi tienda");


        markerOptions2.icon(BitmapDescriptorFactory.fromResource(R.drawable.store));

        //mMapTienda.clear();


        mMap.addMarker(markerOptions2).showInfoWindow();

            calculateDistanceAndTime(location);


    }


    private void calculateDistanceAndTime(LatLng location)  {

        String url = getDirectionsUrl(location, locationTienda);

        System.out.println(url);

       DownloadTask downloadTask = new DownloadTask();



        downloadTask.execute(url);
    }


    private class DownloadTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... url) {

            String data = "";

            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            JSONObject parse ;
            JSONArray jRoutes;
            JSONArray jLegs;
            try {
                parse = new JSONObject(result);
                jRoutes=parse.getJSONArray("routes");
                 jLegs = ( (JSONObject)jRoutes.get(0)).getJSONArray("legs");

                  for(int j=0;j<jLegs.length();j++) {

                        JSONObject object= jLegs.getJSONObject(j);

                        System.out.println("DISTANCIA" + object.getJSONObject("distance").get("text")+"SOLO DISTANCIA");

                        String dis=object.getJSONObject("distance").get("text").toString();

                        activity_maps_distancia.setText(dis);

                        System.out.println("TIEMPO "+object.getJSONObject("duration").get("text")+"SOLO DISTANCIA");

                        String tiem=object.getJSONObject("duration").get("text").toString();

                        activity_maps_tiempo.setText(tiem);
                    }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";
        String mode = "mode=driving";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode;

        // Output format
        String output = "json";
        //API KEY
        String API_KEY="&key=AIzaSyAovb3NQYJdlU_a8SwdrWIe2cj-e2NWOmM";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters+API_KEY;


        /*String m=
                "https://maps.googleapis.com/maps/api/directions/json?origin=-12.1689212,-76.9275876&detination=-12.1690929,-76.9277597&key=AIzaSyAovb3NQYJdlU_a8SwdrWIe2cj-e2NWOmM"
*/
        return url;
    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    public static Intent newInstanceDisplayActivity(Context context , Restaurante_Pedido restaurante_pedido){

        Intent intent= new Intent(context, DisplayActivity.class);
        intent.putExtra(RESTAURANTE_PEDIDO_DISPLAY,restaurante_pedido);
        return intent;

    }

    private void reciveDataIntent(){
        if(getIntent().getSerializableExtra(RESTAURANTE_PEDIDO_DISPLAY) !=null){
            mRestaurante_pedido=(Restaurante_Pedido) getIntent().getSerializableExtra(RESTAURANTE_PEDIDO_DISPLAY);
        }

    }



}