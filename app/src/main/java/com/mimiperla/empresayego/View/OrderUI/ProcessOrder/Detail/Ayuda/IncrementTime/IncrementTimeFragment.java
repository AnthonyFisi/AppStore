package com.mimiperla.empresayego.View.OrderUI.ProcessOrder.Detail.Ayuda.IncrementTime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.mimiperla.empresayego.Repository.Modelo.Venta;
import com.mimiperla.empresayego.View.OrderUI.ProcessOrder.Restaurante_PedidoProcesResultsAdapter;
import com.mimiperla.empresayego.View.ProcesoOrdenActivity;
import com.mimiperla.empresayego.ViewModel.VentaViewModel;

import org.jetbrains.annotations.NotNull;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


public class IncrementTimeFragment extends Fragment {


    private OnDataPass dataPasser;

    static final String TAG = "";
    private  Restaurante_Pedido mRestaurante_pedido;
    private VentaViewModel viewModel;
    private ImageView fragment_increment_DISMINUIR_TIEMPO,fragment_increment_INCREMENTAR_TIEMPO;
    private TextView fragment_increment_TIEMPO,text_aceptar_pedido,new_time;
    private ProgressBar progres_aceptar_pedido;
    private CardView cardview_aceptar_pedido,cerrar_increment;


    private LinearLayout linearlayout_ACTUALIZAR_OK,linearlayout_INCREMENT_TIME;

    private int cantidadTiempo=5;

    public IncrementTimeFragment() {
        // Required empty public constructor
    }

/*
    public static IncrementTimeFragment newInstance(Restaurante_Pedido restaurante_pedido) {
        IncrementTimeFragment fragment = new IncrementTimeFragment();
        Bundle args = new Bundle();
        args.putSerializable("EMPRESA",restaurante_pedido);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
           Bundle bundle=getArguments();
            mRestaurante_pedido=(Restaurante_Pedido) bundle.getSerializable("EMPRESA");
        }

        viewModel = new ViewModelProvider(this).get(VentaViewModel.class);
        viewModel.init();
        viewModel.getVentaLiveData().observe(this, new Observer<Venta>() {
            @Override
            public void onChanged(Venta venta) {


                if(venta !=null){
                    String total=venta.getVenta_costototal()+" min ";
                    new_time.setText(total);
                    linearlayout_INCREMENT_TIME.setVisibility(View.GONE);
                    linearlayout_ACTUALIZAR_OK.setVisibility(View.VISIBLE);

                    passData(true);

                }else{

                    linearlayout_ACTUALIZAR_OK.setVisibility(View.GONE);

                    progres_aceptar_pedido.setVisibility(View.GONE);
                    cardview_aceptar_pedido.setCardBackgroundColor(getResources().getColor(R.color.original_color));
                    text_aceptar_pedido.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(),"No se pudo incrementar el tiempo,intentelo una vez mas",Toast.LENGTH_LONG).show();

                   passData(false);

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
        cerrar_increment();
        return view;
    }




    private void declararWidgets(View view){
        fragment_increment_DISMINUIR_TIEMPO=view.findViewById(R.id.fragment_increment_DISMINUIR_TIEMPO);
        fragment_increment_INCREMENTAR_TIEMPO=view.findViewById(R.id.fragment_increment_INCREMENTAR_TIEMPO);
        fragment_increment_TIEMPO=view.findViewById(R.id.fragment_increment_TIEMPO);

        linearlayout_ACTUALIZAR_OK=view.findViewById(R.id.linearlayout_ACTUALIZAR_OK);

        progres_aceptar_pedido=view.findViewById(R.id.progres_aceptar_pedido);
        text_aceptar_pedido=view.findViewById(R.id.text_aceptar_pedido);
        cardview_aceptar_pedido=view.findViewById(R.id.cardview_aceptar_pedido);
        cerrar_increment=view.findViewById(R.id.cerrar_increment);

        new_time=view.findViewById(R.id.new_time);
        linearlayout_INCREMENT_TIME=view.findViewById(R.id.linearlayout_INCREMENT_TIME);
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

        cardview_aceptar_pedido.setOnClickListener( view -> {


            viewModel.updateEstadoVentaTiempo(mRestaurante_pedido.getIdventa(),cantidadTiempo);

            progres_aceptar_pedido.setVisibility(View.VISIBLE);
            cardview_aceptar_pedido.setCardBackgroundColor(getResources().getColor(R.color.white));
            text_aceptar_pedido.setVisibility(View.GONE);

        });
    }

    private void cerrar_increment(){
        cerrar_increment.setOnClickListener(v->{
            //dismiss();
            Restaurante_PedidoProcesResultsAdapter.cancelAllTimers();
            ProcesoOrdenActivity.countDownMap.clear();
            Intent intent= ProcesoOrdenActivity.startIntentProcesoOrdenActivity(getContext(),true);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            getActivity().finish();
        });
    }
/*
    public void setPassData(Restaurante_Pedido restaurante_pedido) {
        this.mRestaurante_pedido=restaurante_pedido;
    }
*/




    private void passData(boolean agregar) {
        dataPasser.onDataPass(agregar);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        dataPasser = (IncrementTimeFragment.OnDataPass) context;
    }



    public interface OnDataPass {
        void onDataPass(boolean agregar);
    }



}

