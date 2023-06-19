package com.mimiperla.empresayego.View.OrderUI.ProcessOrder.Detail.Ayuda.IncrementPrice;

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


public class IncrementPriceFragment extends Fragment {

    private  Restaurante_Pedido mRestaurante_pedido;
    private VentaViewModel viewModel;
    private ImageView fragment_increment_DISMINUIR_TIEMPO,fragment_increment_INCREMENTAR_TIEMPO;
    private TextView fragment_increment_TIEMPO,text_aceptar_pedido,new_time;
    private ProgressBar progres_aceptar_pedido;
    private CardView cardview_aceptar_pedido,cerrar_increment;

    private boolean incrementarResponse=false;

    private float costoTotal=0;

    private LinearLayout linearlayout_ACTUALIZAR_OK,linearLayout_INCREMENT_PRICE;

    private TextView costo_total;

    private int cantidadTiempo=0;

    private OnDataPassPrice dataPasser;

    public IncrementPriceFragment() {
        // Required empty public constructor
    }


   /* public static IncrementPriceFragment newInstance(Restaurante_Pedido restaurante_pedido) {
        IncrementPriceFragment fragment = new IncrementPriceFragment();
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

            costoTotal=mRestaurante_pedido.getVenta_costototal();
        }

        viewModel = new ViewModelProvider(this).get(VentaViewModel.class);
        viewModel.init();
        viewModel.getVentaLiveData().observe(this, new Observer<Venta>() {
            @Override
            public void onChanged(Venta venta) {
                if(venta !=null){

                    linearLayout_INCREMENT_PRICE.setVisibility(View.GONE);

                   // dataPasser.onDataPassIncrementPrice(true,costoTotal);
                    String total="S/ "+venta.getVenta_costototal();
                    new_time.setText(total);

                    linearlayout_ACTUALIZAR_OK.setVisibility(View.VISIBLE);

                    dataPasser.onDataPassIncrementPrice(true);

                    progres_aceptar_pedido.setVisibility(View.GONE);


                }else{


                    dataPasser.onDataPassIncrementPrice(false);


                    linearlayout_ACTUALIZAR_OK.setVisibility(View.VISIBLE);

                    progres_aceptar_pedido.setVisibility(View.VISIBLE);

                    cardview_aceptar_pedido.setCardBackgroundColor(getResources().getColor(R.color.original_color));
                    text_aceptar_pedido.setVisibility(View.VISIBLE);


                    Toast.makeText(getContext(),"No se pudo incrementar el precio,intentelo una vez mas",Toast.LENGTH_LONG).show();

                   // dataPasser.onDataPassIncrementPrice(false,0);

                }

            }
        });
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_increment_tiem_fragment1, container, false);
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
        costo_total=view.findViewById(R.id.costo_total);
        progres_aceptar_pedido=view.findViewById(R.id.progres_aceptar_pedido);
        text_aceptar_pedido=view.findViewById(R.id.text_aceptar_pedido);
        cardview_aceptar_pedido=view.findViewById(R.id.cardview_aceptar_pedido);
        cerrar_increment=view.findViewById(R.id.cerrar_increment);
        new_time=view.findViewById(R.id.new_time);
        linearLayout_INCREMENT_PRICE=view.findViewById(R.id.linearLayout_INCREMENT_PRICE);

    }



    private void setDataWidget(){
        costo_total.setText(String.valueOf(costoTotal));
        fragment_increment_TIEMPO.setText(String.valueOf(cantidadTiempo));

        fragment_increment_DISMINUIR_TIEMPO.setOnClickListener(v->{

            if(cantidadTiempo>0){
                cantidadTiempo-=1;
                costoTotal-=1;

            }
            fragment_increment_TIEMPO.setText(String.valueOf(cantidadTiempo));
        });

        fragment_increment_INCREMENTAR_TIEMPO.setOnClickListener(v->{
            if(10>=cantidadTiempo){
                cantidadTiempo+=1;
                costoTotal+=1;

            }

            costo_total.setText(String.valueOf(costoTotal));
            fragment_increment_TIEMPO.setText(String.valueOf(cantidadTiempo));
        });

    }

    private void updateTime(){

        cardview_aceptar_pedido.setOnClickListener( view -> {


            viewModel.updateEstadoVentaCostoTotal(mRestaurante_pedido.getIdventa(),cantidadTiempo);

            progres_aceptar_pedido.setVisibility(View.VISIBLE);
            cardview_aceptar_pedido.setCardBackgroundColor(getResources().getColor(R.color.white));
            text_aceptar_pedido.setVisibility(View.GONE);


        });
    }



    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        dataPasser = (IncrementPriceFragment.OnDataPassPrice) context;
    }


    public interface OnDataPassPrice {
        void onDataPassIncrementPrice(boolean respuesta);
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
        setDataWidget();
    }*/
}

/*
*
*
*

*
*
* */