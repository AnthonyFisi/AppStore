package com.example.empresayego.View.OrderUI.ProcessOrder.Detail;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Modelo.Gson.GsonRepartidor_Bi;
import com.example.empresayego.Repository.Modelo.Orden_estado_restaurante;
import com.example.empresayego.Repository.Modelo.Repartidor_Bi;
import com.example.empresayego.ViewModel.Orden_estado_restauranteViewModel;
import com.example.empresayego.ViewModel.Repartidor_BiViewModel;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class FragmentComentarioVentaDialog extends AppCompatDialogFragment implements RepartidorResultsAdapter.RepartidorClick {


    public static final String TAG = FragmentComentarioVentaDialog.class.getSimpleName();

    private static final String ARG_IDUSUARIO ="ARG_IDUSUARIO" ;

    private FormDialogListener1 listener;

    private static final String ARG_VALUE = "ARG_VALUE";

    private LinearLayout linearlayout_repartidores,linearlayout_progressbar,linearlayout_result,linearlayout_fail;

    private RecyclerView recyclerView_repartidores;

    private Repartidor_BiViewModel viewModel;

    private RepartidorResultsAdapter adapter;

    private ImageButton imageButton_back;

    private ImageView imageView_reload;

    private Orden_estado_restauranteViewModel viewModelEstado;

    private Orden_estado_restaurante orden_estado_empresa;

    private int idusuario;

    private TextView text_nombre_repartidor,text_codigo_repartidor;

    private String codigo_venta;

    private Button button_cerrar;

    private boolean result_send;

    private Timestamp fecha;

    public static FragmentComentarioVentaDialog newInstance(Orden_estado_restaurante orden_estado_restaurante,int idusuario) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_VALUE, orden_estado_restaurante);
        args.putInt(ARG_IDUSUARIO,idusuario);
        FragmentComentarioVentaDialog frag = new FragmentComentarioVentaDialog();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        result_send=false;
        adapter=new RepartidorResultsAdapter();

        viewModel=new ViewModelProvider(this).get(Repartidor_BiViewModel.class);
        viewModel.init();
        viewModel.getGsonRepartidorLiveData().observe(this, new Observer<GsonRepartidor_Bi>() {
            @Override
            public void onChanged(GsonRepartidor_Bi gsonRepartidorBi) {
                linearlayout_progressbar.setVisibility(View.GONE);

                if(gsonRepartidorBi !=null){

                    linearlayout_repartidores.setVisibility(View.VISIBLE);
                    linearlayout_fail.setVisibility(View.GONE);

                    adapter.setResults(gsonRepartidorBi.getListaRepartidor(),FragmentComentarioVentaDialog.this);
                }else {
                    linearlayout_repartidores.setVisibility(View.GONE);

                    linearlayout_fail.setVisibility(View.VISIBLE);
                }
            }
        });


        viewModelEstado = new ViewModelProvider(this).get(Orden_estado_restauranteViewModel.class);
        viewModelEstado.init();
        viewModelEstado.getOrden_estado_restauranteLiveData().observe(this, new Observer<Orden_estado_restaurante>() {
            @Override
            public void onChanged(Orden_estado_restaurante orden_estado_restaurante) {
                linearlayout_progressbar.setVisibility(View.GONE);

                if(orden_estado_restaurante !=null){
                    result_send=true;
                    fecha=orden_estado_restaurante.getFecha();
                    linearlayout_result.setVisibility(View.VISIBLE);
                    listener.update(true,fecha);



                }else{
                    linearlayout_repartidores.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(),"Volver a intentalor",Toast.LENGTH_LONG).show();
                }
            }
        });
        if(getArguments()!=null){
            orden_estado_empresa=(Orden_estado_restaurante) getArguments().getSerializable(ARG_VALUE);
            idusuario=getArguments().getInt(ARG_IDUSUARIO);
        }
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        try {
            listener = (FragmentComentarioVentaDialog.FormDialogListener1) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FormDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View content = LayoutInflater.from(getContext()).inflate(R.layout.fragment_repartidores, null);

        setupContent(content);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(content);

        AlertDialog alertDialog = builder.create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        alertDialog.setCanceledOnTouchOutside(false);

        imageButton_back.setOnClickListener(v->{
            dismiss();

            if(result_send){
                listener.update(result_send,fecha);
            }

        });

        imageView_reload.setOnClickListener(v->{
            linearlayout_repartidores.setVisibility(View.GONE);
            linearlayout_result.setVisibility(View.GONE);
            linearlayout_fail.setVisibility(View.GONE);
            linearlayout_progressbar.setVisibility(View.VISIBLE);
            viewModel.listaRepartidor(Empresa.sEmpresa.getIdempresa());

        });

        viewModel.listaRepartidor(Empresa.sEmpresa.getIdempresa());

        clickCerrar();
        return alertDialog;
    }


    private void setupContent(View content) {
        linearlayout_repartidores=content.findViewById(R.id.linearlayout_repartidores);
        linearlayout_progressbar=content.findViewById(R.id.linearlayout_progressbar);
        linearlayout_result=content.findViewById(R.id.linearlayout_result);
        recyclerView_repartidores=content.findViewById(R.id.recyclerView_repartidores);
        imageButton_back=content.findViewById(R.id.imageButton_back);
        linearlayout_fail=content.findViewById(R.id.linearlayout_fail);
        imageView_reload=content.findViewById(R.id.imageView_reload);
        text_codigo_repartidor=content.findViewById(R.id.text_codigo_repartidor);
        text_nombre_repartidor=content.findViewById(R.id.text_nombre_repartidor);

        button_cerrar=content.findViewById(R.id.button_cerrar);

        linearlayout_repartidores.setVisibility(View.GONE);
        linearlayout_result.setVisibility(View.GONE);
        linearlayout_fail.setVisibility(View.GONE);


        recyclerView_repartidores.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView_repartidores.setAdapter(adapter);
    }


    @Override
    public void selectRepartidor(Repartidor_Bi repartidor) {


        viewModelEstado.updateEstadoProcesMarketPlace(orden_estado_empresa,idusuario,repartidor.getIdrepartidor());
        linearlayout_progressbar.setVisibility(View.VISIBLE);

        linearlayout_repartidores.setVisibility(View.GONE);
        linearlayout_result.setVisibility(View.GONE);
        linearlayout_fail.setVisibility(View.GONE);

        text_nombre_repartidor.setText(repartidor.getNombre_usuario());

        codigo_venta="#"+orden_estado_empresa.getId().getIdventa();

        text_codigo_repartidor.setText(codigo_venta);

       // responseDataPedidoListo();

    }


    public interface FormDialogListener1{
        void update(boolean respuesta, Timestamp fecha);
    }



    private void clickCerrar(){
        button_cerrar.setOnClickListener(v->{
            dismiss();
            listener.update(result_send,fecha);
        });
    }


}
