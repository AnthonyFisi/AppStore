package com.example.empresayego.View.OrderUI.ReadyOrder.ReadyOrderDetail.MapLocation;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapLocationFragment extends Fragment implements OnMapReadyCallback {

   // private OnDataPass dataPasser;
   private MapView mapView;

    public MapLocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_map_location, container, false);


        mapView = (MapView) view.findViewById(R.id.google_map_supervisar_orden);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);//when you already implement OnMapReadyCallback in your fragment

       return view;
    }


    public void setPassData(Restaurante_Pedido restaurante_pedido){

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
/*
    public void passData(boolean agregar) {
        dataPasser.onDataPass(agregar);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        dataPasser = (MapLocationFragment.OnDataPass) context;
    }


    public interface OnDataPass {
        void onDataPass(boolean agregar);
    }*/

}
