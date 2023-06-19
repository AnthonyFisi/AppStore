package com.mimiperla.empresayego.View.OrderUI.ReadyOrder.ReadyOrderDetail.PersonDelivery;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mimiperla.empresayego.Proof.DisplayActivity;
import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.mimiperla.empresayego.ViewModel.Repartidor_BiViewModel;

public class PersonDeliveryFragment extends Fragment {

    private ImageView fragment_person_delivery_IMAGEN_DELIVERY;
    private TextView fragment_person_delivery_NOMBRE_DELIVERY,fragment_person_delivery_ID_DELIVERY,fragment_person_delivery_TELEFONO;
    private ImageButton fragment_person_delivery_LLAMAR;
    private LinearLayout fragment_person_delivery_BUTON_MENSAJE;
    private Button fragment_person_delivery_RASTREAR_UBICACION;

    private Repartidor_BiViewModel viewModel;
    //private Repartidor mRepartidor;

    private Restaurante_Pedido mRestaurante_pedido;


    public PersonDeliveryFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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


    private void setDataWidgets(Restaurante_Pedido data){
           /* fragment_person_delivery_NOMBRE_DELIVERY.setText(data.getNombre_repartidor());
            fragment_person_delivery_ID_DELIVERY.setText(data.getCodigo_repartidor());
        if (data.getImagen_repartidor()!= null) {
            String imageUrl =data.getImagen_repartidor()
                    .replace("http://", "https://");

            Glide.with(this)
                    .load(imageUrl)
                    .into(fragment_person_delivery_IMAGEN_DELIVERY);
        }*/
    }



    public void setPassData(Restaurante_Pedido restaurante_pedido){

        this.mRestaurante_pedido=restaurante_pedido;
        //System.out.println(mRestaurante_pedido.getNombre_estado());
        System.out.println("-------------------------------------");

       // System.out.println(restaurante_pedido.getUsuario_nombre());

        setDataWidgets(restaurante_pedido);


    }

}
