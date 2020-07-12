package com.example.empresayego.View.OrderUI.ReadyOrder.ReadyOrderDetail.PersonDelivery;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.empresayego.Proof.DisplayActivity;
import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Repartidor;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.example.empresayego.ViewModel.RepartidorViewModel;

import org.jetbrains.annotations.NotNull;

public class PersonDeliveryFragment extends Fragment {

    private ImageView fragment_person_delivery_IMAGEN_DELIVERY;
    private TextView fragment_person_delivery_NOMBRE_DELIVERY,fragment_person_delivery_ID_DELIVERY,fragment_person_delivery_TELEFONO;
    private ImageButton fragment_person_delivery_LLAMAR;
    private LinearLayout fragment_person_delivery_BUTON_MENSAJE;
    private Button fragment_person_delivery_RASTREAR_UBICACION;

    private RepartidorViewModel viewModel;
    private Repartidor mRepartidor;

    private Restaurante_Pedido mRestaurante_pedido;


    //private OnDataPass dataPasser;

    public PersonDeliveryFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        viewModel = ViewModelProviders.of(this).get(RepartidorViewModel.class);
        viewModel.init();
        viewModel.getRepartidorLiveData().observe(this, repartidor -> {
            if(repartidor !=null){
                mRepartidor=new Repartidor();
                mRepartidor=repartidor;

                System.out.println("llego la data por aca");
            }else{
                mRepartidor=new Repartidor();
                System.out.println("NO  LLEGO LA DATA");

            }
        });



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_person_delivery, container, false);

        //DECLARAR WIDGETS
        declararWidgets(view);
        //LOAD IMAGE

        botonMapa();

        //SET DATA WIDGETS


        return view;
    }


    private void botonMapa(){
        fragment_person_delivery_RASTREAR_UBICACION.setOnClickListener( v->{

            Intent intent= DisplayActivity.newInstanceDisplayActivity(getContext(),mRestaurante_pedido);
            startActivity(intent);
        });
    }


    private void declararWidgets(View view){

        fragment_person_delivery_IMAGEN_DELIVERY=view.findViewById(R.id.fragment_person_delivery_IMAGEN_DELIVERY);
        fragment_person_delivery_NOMBRE_DELIVERY=view.findViewById(R.id.fragment_person_delivery_NOMBRE_DELIVERY);
        fragment_person_delivery_ID_DELIVERY=view.findViewById(R.id.fragment_person_delivery_ID_DELIVERY);
        fragment_person_delivery_LLAMAR=view.findViewById(R.id.fragment_person_delivery_LLAMAR);
        fragment_person_delivery_RASTREAR_UBICACION=view.findViewById(R.id.fragment_person_delivery_RASTREAR_UBICACION);
    }


    private void setDataWidgets(){
            fragment_person_delivery_NOMBRE_DELIVERY.setText(mRepartidor.getNombre());
            fragment_person_delivery_ID_DELIVERY.setText(mRepartidor.getCodigo());
    }


    private void loadImagen(String url){
            if (url!= null) {
                String imageUrl =url
                        .replace("http://", "https://");

                Glide.with(getContext())
                        .load(imageUrl)
                        .into(fragment_person_delivery_IMAGEN_DELIVERY);
            }
    }



    public void setPassData(Restaurante_Pedido restaurante_pedido){

        viewModel.searchUbicacionById(restaurante_pedido.getIdrepartidor());

        Handler handler = new Handler();
        handler.postDelayed(() -> {

            setDataWidgets();
            loadImagen(restaurante_pedido.getImagen_repartidor());
        }, 3000);

    }

}
