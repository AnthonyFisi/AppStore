package com.example.empresayego.View.OrderUI.ProcessOrder.Detail.Ayuda;

import android.content.Context;
import android.os.Bundle;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.example.empresayego.Repository.Modelo.Venta;
import com.example.empresayego.ViewModel.VentaViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class IncrementTiemFragment extends BottomSheetDialogFragment {


    private OnDataPass dataPasser;

    public static final String TAG = "";
    private  Restaurante_Pedido mRestaurante_pedido;
    private VentaViewModel viewModel;
    private ImageView fragment_increment_DISMINUIR_TIEMPO,fragment_increment_INCREMENTAR_TIEMPO;
    private Button fragment_increment_UPDATE_PEDIDO;
    private TextView fragment_increment_TIEMPO;
    private boolean incrementarResponse=false;
    private ProgressBar fragment_proces_order_item_TIME_PROGRES;

    private LinearLayout fragment_increment_ACTUALIZAR_OK,fragment_increment_ACTUALIZAR_ERROR,fragment_increment_LOAD,linearLayout2,linearLayout15;

    private int cantidadTiempo=5;

    public IncrementTiemFragment() {
        // Required empty public constructor
    }


    public static IncrementTiemFragment newInstance(Restaurante_Pedido restaurante_pedido) {
        IncrementTiemFragment fragment = new IncrementTiemFragment();
        Bundle args = new Bundle();
        args.putSerializable("EMPRESA",restaurante_pedido);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_increment_tiem, container, false);
        declararWidgets(view);
        setDataWidget();
        updateTime();
        setVisibility();

        return view;
    }




    private void declararWidgets(View view){
        fragment_increment_DISMINUIR_TIEMPO=view.findViewById(R.id.fragment_increment_DISMINUIR_TIEMPO);
        fragment_increment_INCREMENTAR_TIEMPO=view.findViewById(R.id.fragment_increment_INCREMENTAR_TIEMPO);
        fragment_increment_UPDATE_PEDIDO=view.findViewById(R.id.fragment_eliminar_UPDATE_PEDIDO);
        fragment_increment_TIEMPO=view.findViewById(R.id.fragment_increment_TIEMPO);
        fragment_increment_ACTUALIZAR_ERROR=view.findViewById(R.id.fragment_increment_ACTUALIZAR_ERROR);
        fragment_increment_ACTUALIZAR_OK=view.findViewById(R.id.fragment_increment_ACTUALIZAR_OK);
        fragment_increment_LOAD=view.findViewById(R.id.fragment_increment_LOAD);
        fragment_proces_order_item_TIME_PROGRES=view.findViewById(R.id.fragment_proces_order_item_TIME_PROGRES);
        linearLayout2=view.findViewById(R.id.linearLayout2_eliminar);
        linearLayout15=view.findViewById(R.id.linearLayout15_eliminar);
    }

    private void setVisibility(){
        fragment_increment_ACTUALIZAR_ERROR.setVisibility(View.GONE);
        fragment_increment_ACTUALIZAR_OK.setVisibility(View.GONE);
        fragment_increment_LOAD.setVisibility(View.GONE);
    }


    private void setDataWidget(){

        fragment_increment_DISMINUIR_TIEMPO.setOnClickListener(v->{

            if(cantidadTiempo>0){
                cantidadTiempo-=5;

            }
            fragment_increment_TIEMPO.setText(String.valueOf(cantidadTiempo));

        });

        fragment_increment_INCREMENTAR_TIEMPO.setOnClickListener(v->{
            if(20>=cantidadTiempo){
                cantidadTiempo+=5;
            }


            fragment_increment_TIEMPO.setText(String.valueOf(cantidadTiempo));
        });

    }

    private void updateTime(){

        fragment_increment_UPDATE_PEDIDO.setOnClickListener( view -> {


            viewModel.updateEstadoVentaTiempo(mRestaurante_pedido.getIdventa(),cantidadTiempo);

            fragment_increment_LOAD.setVisibility(View.VISIBLE);



            Handler handler = new Handler();
            handler.postDelayed(() -> {

                if(incrementarResponse){
                    fragment_increment_UPDATE_PEDIDO.setVisibility(View.INVISIBLE);

                    linearLayout2.setVisibility(View.INVISIBLE);
                    linearLayout15.setVisibility(View.INVISIBLE);
                    fragment_increment_ACTUALIZAR_OK.setVisibility(View.VISIBLE);
                    passData(true,cantidadTiempo);


                }else {
                    fragment_increment_UPDATE_PEDIDO.setVisibility(View.INVISIBLE);

                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout15.setVisibility(View.VISIBLE);
                    fragment_increment_ACTUALIZAR_ERROR.setVisibility(View.VISIBLE);
                    passData(false,cantidadTiempo);

                }
            }, 3000);


        });
    }



    public void passData(boolean agregar,int tiempo) {
        dataPasser.onDataPass(agregar,tiempo);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        dataPasser = (IncrementTiemFragment.OnDataPass) context;
    }


    public interface OnDataPass {
        void onDataPass(boolean agregar,int tiempo);
    }






}

