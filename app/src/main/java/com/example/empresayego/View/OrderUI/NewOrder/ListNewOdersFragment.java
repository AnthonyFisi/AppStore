package com.example.empresayego.View.OrderUI.NewOrder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.empresayego.MainActivity;
import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Modelo.EmpresaOficial;
import com.example.empresayego.Repository.Modelo.Gson.GsonRestaurante_Pedido;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.example.empresayego.View.OrderUI.NewOrder.Detail.NewOrderDetailActivity;
import com.example.empresayego.View.ProcesoOrdenActivity;
import com.example.empresayego.ViewModel.EmpresaOficialViewModel;
import com.example.empresayego.ViewModel.EmpresaViewModel;
import com.example.empresayego.ViewModel.Restaurante_PedidoViewModel;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ListNewOdersFragment extends Fragment implements Restaurante_PedidoResultsAdapter.ClickPedidoReciente {


    private final static int CODE = 1;

    private boolean agregar = false;

    private int position = 1000;

    private Restaurante_Pedido mRestaurante_pedido = new Restaurante_Pedido();

    private Restaurante_PedidoViewModel viewModel;

    private Restaurante_PedidoResultsAdapter adapter;

    private RecyclerView recyclerView;

    private List<Restaurante_Pedido> listaNewData = new ArrayList<>();

    private TextView text_gallery;

    private MediaPlayer mediaPlayer;

    private LinearLayout screen_negocio_esperando, screen_negocio_deuda, screen_negocio_cerrado;

    private TextView activity_proceso_orden_NOMBRE_EMPRESA, text_disponible;

    private CardView activity_proceso_orden_SWITCH_ENABLE;

    private EmpresaOficialViewModel viewModelEmpresa;

    private ProgressBar progres;

    private EmpresaEnable mEmpresaEnable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        adapter = new Restaurante_PedidoResultsAdapter();
        viewModel = ViewModelProviders.of(this).get(Restaurante_PedidoViewModel.class);
        viewModel.init();
        viewModel.getRestaurante_PedidoLiveData().observe(this, new Observer<GsonRestaurante_Pedido>() {
            @Override
            public void onChanged(GsonRestaurante_Pedido gsonRestaurante_pedido) {
                progres.setVisibility(View.GONE);
                if (gsonRestaurante_pedido != null) {
                    goneAllScreen();
                    adapter.setResults(gsonRestaurante_pedido.getListaRestaurante_Pedido(), ListNewOdersFragment.this);
                } else {
                    visibleEsperando();
                    //screen_negocio_esperando.setVisibility(View.VISIBLE);
                }


            }
        });


    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_new_orders, container, false);


        delcararWidgets(view);
        setWidgets();
        declararEmpreaViewModel();
        changeDisponibilidadEmpresa();
        funPusher();
        setOnclickUpdate();

        return view;
    }

    private void delcararWidgets(View view) {

        screen_negocio_esperando = view.findViewById(R.id.screen_negocio_esperando);

        progres = view.findViewById(R.id.progres);
        recyclerView = view.findViewById(R.id.fragment_restaurante_orden);
        text_gallery = view.findViewById(R.id.text_gallery);

        screen_negocio_cerrado = view.findViewById(R.id.screen_negocio_cerrado);
        activity_proceso_orden_NOMBRE_EMPRESA = view.findViewById(R.id.activity_proceso_orden_NOMBRE_EMPRESA);
        activity_proceso_orden_SWITCH_ENABLE = view.findViewById(R.id.activity_proceso_orden_SWITCH_ENABLE);
        text_disponible = view.findViewById(R.id.text_disponible);
        //activity_proceso_orden_SWITCH_ENABLE.setChecked(false);

        screen_negocio_deuda = view.findViewById(R.id.screen_negocio_deuda);


    }

    private void setWidgets() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(adapter);

        activity_proceso_orden_NOMBRE_EMPRESA.setText(Empresa.sEmpresa.getNombre_empresa());

        if (Empresa.sEmpresa.isCuentaactiva()) {



            if (Empresa.sEmpresa.isDisponible()) {
                activity_proceso_orden_SWITCH_ENABLE.setCardBackgroundColor(getResources().getColor(R.color.original_color));
                text_disponible.setText("Abierto");
                text_disponible.setTextColor(getResources().getColor(R.color.white));
                text_disponible.setBackgroundColor(getResources().getColor(R.color.original_color));

                progres.setVisibility(View.VISIBLE);

                viewModel.searchRestaurantePedidoByEmpresa(Empresa.sEmpresa.getIdempresa());

                goneAllScreen();
                // activity_proceso_orden_SWITCH_ENABLE.setBackgroundColor(Color.GREEN);
            } else {

                visibleCerrado();
                stateEmpresa(true);
                //screen_negocio_cerrado.setVisibility(View.VISIBLE);

            }

        } else {
            activity_proceso_orden_SWITCH_ENABLE.setVisibility(View.GONE);
            stateEmpresa(true);
            visibleDeuda();

            //screen_negocio_deuda.setVisibility(View.VISIBLE);
        }


    }

    private void changeDisponibilidadEmpresa() {
        activity_proceso_orden_SWITCH_ENABLE.setOnClickListener(v -> {

            goneAllScreen();
            progres.setVisibility(View.VISIBLE);
            viewModelEmpresa.updateEmpresaDisponiblidad(Empresa.sEmpresa.getIdempresa(), !Empresa.sEmpresa.isDisponible());

        });
    }

    private void declararEmpreaViewModel() {
        viewModelEmpresa = new ViewModelProvider(this).get(EmpresaOficialViewModel.class);
        viewModelEmpresa.init();
        viewModelEmpresa.getEmpresaOficialLiveData().observe(getViewLifecycleOwner(), new Observer<EmpresaOficial>() {
            @Override
            public void onChanged(EmpresaOficial empresa) {
                progres.setVisibility(View.GONE);
                if (empresa != null) {


                    Empresa.sEmpresa.setDisponible(empresa.isDisponible());

                    if (empresa.isDisponible()) {
                        activity_proceso_orden_SWITCH_ENABLE.setCardBackgroundColor(getResources().getColor(R.color.original_color));
                        text_disponible.setText("Abierto");
                        text_disponible.setTextColor(getResources().getColor(R.color.white));
                        // activity_proceso_orden_SWITCH_ENABLE.setBackgroundColor(Color.GREEN);
                        Toast.makeText(getContext(), "Negocio abierto", Toast.LENGTH_LONG).show();
                        visibleEsperando();
                        //screen_negocio_esperando.setVisibility(View.VISIBLE);
                        stateEmpresa(false);

                    } else {

                        activity_proceso_orden_SWITCH_ENABLE.setCardBackgroundColor(getResources().getColor(R.color.background));
                        text_disponible.setText("Cerrado");
                        text_disponible.setTextColor(getResources().getColor(R.color.titulo));
                        //activity_proceso_orden_SWITCH_ENABLE.setChecked(false);
                        //  activity_proceso_orden_SWITCH_ENABLE.setBackgroundColor(Color.GRAY);
                        Toast.makeText(getContext(), "Negocio cerrado", Toast.LENGTH_LONG).show();
                       visibleCerrado();
                       // screen_negocio_cerrado.setVisibility(View.VISIBLE);
                        stateEmpresa(true);

                    }
                } else {
                    Toast.makeText(getContext(), "Hay problemas en nuestros servicos,vuelva a intentarlo", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void funPusher() {
        ProcesoOrdenActivity.channel.bind("my-event", (channelName, eventName, data) -> {
            System.out.println(data + "los datossssssssssssssssss");

            if (getActivity() != null) {

                mediaPlayer = MediaPlayer.create(getContext(), R.raw.soundorden);

                getActivity().runOnUiThread(() -> {
                    try {

                        JsonParser parser = new JsonParser();
                        JsonElement mJson = parser.parse(data);
                        Gson gson = new Gson();
                        Restaurante_Pedido object = gson.fromJson(mJson, Restaurante_Pedido.class);
                        listaNewData.add(object);

                        text_gallery.setText("Tienes " + listaNewData.size() + " pedidos nuevos");

                        text_gallery.setVisibility(View.VISIBLE);

                        screen_negocio_esperando.setVisibility(View.GONE);

                        mediaPlayer.start();
                        mediaPlayer.start();

                        mediaPlayer.setOnCompletionListener(MediaPlayer::stop);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
            }

        });
        ProcesoOrdenActivity.pusher.connect();
    }

    private void setOnclickUpdate() {
        text_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (Restaurante_Pedido pedido : listaNewData) {
                    adapter.addResults(pedido, ListNewOdersFragment.this::clickPedido);
                }

                listaNewData.clear();

                text_gallery.setVisibility(View.GONE);


            }
        });
    }

    @Override
    public void clickPedido(Restaurante_Pedido restaurante_pedido, int position) {
        System.out.println("pedido"+restaurante_pedido.getIdventa());
        Intent intent = NewOrderDetailActivity.newIntentOrderDetail(getContext(), restaurante_pedido, position);
        startActivityForResult(intent, CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == CODE) {
            if (resultCode == Activity.RESULT_OK) {

                Bundle bundle = data.getExtras();
                agregar = bundle.getBoolean("agregar");
                position = bundle.getInt("position");
                mRestaurante_pedido = (Restaurante_Pedido) bundle.getSerializable("objeto");


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                System.out.println("LOS DATOS NO LLEGARON");

            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (agregar) {
            adapter.removeItem(mRestaurante_pedido, position);

            if (adapter.resultSize() == 0) {

                screen_negocio_esperando.setVisibility(View.VISIBLE);
            }
            agregar = false;
        }
    }

    private void visibleEsperando(){
        screen_negocio_esperando.setVisibility(View.VISIBLE);
        screen_negocio_deuda.setVisibility(View.GONE);
        screen_negocio_cerrado.setVisibility(View.GONE);
    }
    private void visibleDeuda(){
        screen_negocio_esperando.setVisibility(View.GONE);
        screen_negocio_deuda.setVisibility(View.VISIBLE);
        screen_negocio_cerrado.setVisibility(View.GONE);
    }
    private void visibleCerrado(){
        screen_negocio_esperando.setVisibility(View.GONE);
        screen_negocio_deuda.setVisibility(View.GONE);
        screen_negocio_cerrado.setVisibility(View.VISIBLE);
    }

    private void goneAllScreen(){
        screen_negocio_esperando.setVisibility(View.GONE);
        screen_negocio_deuda.setVisibility(View.GONE);
        screen_negocio_cerrado.setVisibility(View.GONE);
    }

    private void stateEmpresa(boolean state) {
        mEmpresaEnable.stateEmpresa(state);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mEmpresaEnable = (ListNewOdersFragment.EmpresaEnable) context;
    }

    public interface EmpresaEnable{
        void  stateEmpresa(boolean state);
    }

}