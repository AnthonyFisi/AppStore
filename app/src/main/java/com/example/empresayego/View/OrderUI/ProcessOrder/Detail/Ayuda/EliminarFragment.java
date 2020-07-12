package com.example.empresayego.View.OrderUI.ProcessOrder.Detail.Ayuda;

import android.content.Context;
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
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.example.empresayego.Repository.Modelo.Venta;
import com.example.empresayego.ViewModel.VentaViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.jetbrains.annotations.NotNull;


public class EliminarFragment extends BottomSheetDialogFragment {


    private OnDataPassEliminar dataPasser;
    private  Restaurante_Pedido mRestaurante_pedido;
    private VentaViewModel viewModel;
    private boolean incrementarResponse=false;
    private String comentario="";


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private LinearLayout fragment_increment_ACTUALIZAR_OK,fragment_increment_ACTUALIZAR_ERROR,fragment_increment_LOAD,linearLayout2,linearLayout15;
    private Button fragment_increment_UPDATE_PEDIDO;
    private EditText comentario_cancelar;



    public EliminarFragment() {
    }

    public static EliminarFragment newInstance(Restaurante_Pedido restaurante_pedido) {
        EliminarFragment fragment = new EliminarFragment();
        Bundle args = new Bundle();
        args.putSerializable("EMPRESA",restaurante_pedido);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle=getArguments();
            mRestaurante_pedido=(Restaurante_Pedido) bundle.getSerializable("EMPRESA");
        }


        viewModel = ViewModelProviders.of(this).get(VentaViewModel.class);
        viewModel.init();
        viewModel.getVentaLiveData().observe(this, new Observer<Venta>() {
            @Override
            public void onChanged(Venta venta) {
                if(venta !=null){

                    incrementarResponse=true;
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_eliminar, container, false);
        declararWidgets(view);
        setVisibility();
        updateTime();
        return view;
    }


    private void declararWidgets(View view){

        fragment_increment_UPDATE_PEDIDO=view.findViewById(R.id.fragment_eliminar_UPDATE_PEDIDO);
        fragment_increment_ACTUALIZAR_ERROR=view.findViewById(R.id.fragment_increment_ACTUALIZAR_ERROR);
        fragment_increment_ACTUALIZAR_OK=view.findViewById(R.id.fragment_increment_ACTUALIZAR_OK);
        fragment_increment_LOAD=view.findViewById(R.id.fragment_increment_LOAD);
        linearLayout2=view.findViewById(R.id.linearLayout2_eliminar);
        linearLayout15=view.findViewById(R.id.linearLayout15_eliminar);
        comentario_cancelar=view.findViewById(R.id.comentario_cancelar);
    }

    private void setVisibility(){
        fragment_increment_ACTUALIZAR_ERROR.setVisibility(View.GONE);
        fragment_increment_ACTUALIZAR_OK.setVisibility(View.GONE);
        fragment_increment_LOAD.setVisibility(View.GONE);
    }

    private void updateTime(){

        fragment_increment_UPDATE_PEDIDO.setOnClickListener( view -> {


            comentario=comentario_cancelar.getText().toString();

            System.out.println(comentario +" cometario de la eliminacion");


            Venta venta= new Venta();

            venta.setIdestado_venta(mRestaurante_pedido.getIdventa());
            venta.setComentario(comentario);

            viewModel.updateEstadoVentaCostoCancelar(venta);



            fragment_increment_LOAD.setVisibility(View.VISIBLE);


            Handler handler = new Handler();
            handler.postDelayed(() -> {

                if(incrementarResponse){
                    fragment_increment_UPDATE_PEDIDO.setVisibility(View.INVISIBLE);

                    linearLayout2.setVisibility(View.INVISIBLE);
                    linearLayout15.setVisibility(View.INVISIBLE);
                    fragment_increment_ACTUALIZAR_OK.setVisibility(View.VISIBLE);
                    passData(true,0);


                }else {
                    fragment_increment_UPDATE_PEDIDO.setVisibility(View.INVISIBLE);

                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout15.setVisibility(View.VISIBLE);
                    fragment_increment_ACTUALIZAR_ERROR.setVisibility(View.VISIBLE);
                    passData(false,0);

                }
            }, 4000);


        });
    }




    public void passData(boolean agregar,int tiempo) {
        dataPasser.onDataPassEliminar(agregar,tiempo);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        dataPasser = (EliminarFragment.OnDataPassEliminar) context;
    }


    public interface OnDataPassEliminar {
        void onDataPassEliminar(boolean agregar,int tiempo);
    }




}
