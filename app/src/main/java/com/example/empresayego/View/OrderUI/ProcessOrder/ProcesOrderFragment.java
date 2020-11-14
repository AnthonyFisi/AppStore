package com.example.empresayego.View.OrderUI.ProcessOrder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.SearchView;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.empresayego.Login.Main;
import com.example.empresayego.MainActivity;
import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Modelo.Gson.GsonRestaurante_Pedido;
import com.example.empresayego.Repository.Modelo.Orden_estado_restaurante;
import com.example.empresayego.Repository.Modelo.Orden_estado_restaurantePK;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.example.empresayego.View.OrderUI.ProcessOrder.Detail.ProcesOrderActivity;
import com.example.empresayego.View.ProcesoOrdenActivity;
import com.example.empresayego.ViewModel.Orden_estado_restauranteViewModel;
import com.example.empresayego.ViewModel.Restaurante_PedidoViewModel;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.pusher.client.channel.SubscriptionEventListener;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ProcesOrderFragment extends Fragment implements  Restaurante_PedidoProcesResultsAdapter.ClickPedidoReciente{

    private final static int CODE=2;

    private Restaurante_PedidoViewModel viewModel;
    private Orden_estado_restauranteViewModel viewModel2;
    private Restaurante_PedidoProcesResultsAdapter adapter;
   // private LinearLayout imagen;
   // private TextView mensaje,update_pedido;

   // private TextView update_pedido;

    private   RecyclerView recyclerView;
    private SearchView searchView;

   // private int positionCount;

    private boolean eliminar=false;

    private List<Restaurante_Pedido> listaUpdateSate;

    private boolean data=false;

    boolean respuesta=false;

    private boolean agregar=false;

    private boolean respuestaChangeTime;


    private int position=1000;

    private Restaurante_Pedido mRestaurante_pedido;

    private boolean updateTime,price;

    private int cantidadTiempo=0;

    private List<Restaurante_Pedido> listaNewData;

    private LinearLayout pedidos_preparandose_empty;

    private ProgressBar progres;

    private float priceTotal;

    private String fechaServidor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateTime=false;
        listaNewData= new ArrayList<>();
        listaUpdateSate=new ArrayList<>();
        respuestaChangeTime=false;

       Restaurante_PedidoProcesResultsAdapter.cancelAllTimers();
       //MainActivity.lista.clear();
        ProcesoOrdenActivity.countDownMap.clear();

        adapter = new Restaurante_PedidoProcesResultsAdapter();
        viewModel = ViewModelProviders.of(this).get(Restaurante_PedidoViewModel.class);
        viewModel.init();
        viewModel.getRestaurante_PedidoLiveData().observe(this, new Observer<GsonRestaurante_Pedido>() {
            @Override
            public void onChanged(GsonRestaurante_Pedido gsonRestaurante_pedido) {

                progres.setVisibility(View.GONE);
                if(gsonRestaurante_pedido !=null){

                    adapter.setResults(gsonRestaurante_pedido.getListaRestaurante_Pedido(), ProcesOrderFragment.this,getContext());

                    ProcesoOrdenActivity.lista=gsonRestaurante_pedido.getListaRestaurante_Pedido();
                    for (int i = 0,length = ProcesoOrdenActivity.countDownMap.size(); i < length; i++) {

                            CountDownTimer cdt = ProcesoOrdenActivity.countDownMap.get(ProcesoOrdenActivity.countDownMap.keyAt(i));

                            cdt.cancel();


                            ProcesoOrdenActivity.countDownMap.remove(ProcesoOrdenActivity.countDownMap.keyAt(i));

                            Log.e("TAG",  "ID  :  " + ProcesoOrdenActivity.countDownMap.keyAt(i) +" valor "+ProcesoOrdenActivity.countDownMap.get(ProcesoOrdenActivity.countDownMap.keyAt(i)));


                    }

                    System.out.println("TAMANO DEL COUNTER "+ProcesoOrdenActivity.countDownMap.size());

                }else {

                    pedidos_preparandose_empty.setVisibility(View.VISIBLE);

                }

            }
        });




        viewModel2 = ViewModelProviders.of(this).get(Orden_estado_restauranteViewModel.class);
        viewModel2.init();
        viewModel2.getOrden_estado_restauranteLiveData().observe(this, new Observer<Orden_estado_restaurante>() {
            @Override
            public void onChanged(Orden_estado_restaurante orden_estado_restaurante) {

                if(orden_estado_restaurante!=null){

                    respuestaChangeTime=true;
                }

            }
        });



    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_proces_orders, container, false);

        pedidos_preparandose_empty=root.findViewById(R.id.pedidos_preparandose_empty);
        progres=root.findViewById(R.id.progres);


      /*  imagen=root.findViewById(R.id.imagen);
        mensaje=root.findViewById(R.id.mensaje);
        */

    //    update_pedido=root.findViewById(R.id.update_pedido);
        recyclerView=root.findViewById(R.id.fragment_restaurante_ordenProces);
        //seachOrder(root);

        whiteNotificationBar(recyclerView);
     /*   imagen.setVisibility(View.GONE);
        mensaje.setVisibility(View.GONE);*/
        funPusher();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);


        if(Empresa.sEmpresa.isDisponible()){
            progres.setVisibility(View.VISIBLE);
            viewModel.searchRestaurantePedidoByEmpresaProces(Empresa.sEmpresa.getIdempresa());


        }



        //setOnclickUpdate();

        return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == CODE) {
            if(resultCode == Activity.RESULT_OK){


                Bundle bundle= data.getExtras();
                agregar=bundle.getBoolean("agregar");
                position=bundle.getInt("position");
                mRestaurante_pedido=(Restaurante_Pedido) bundle.getSerializable("objeto");
                //positionCount=bundle.getInt("positionCount");
                updateTime=bundle.getBoolean("updateTime");
                cantidadTiempo=bundle.getInt("cantidadTiempo");

                eliminar=bundle.getBoolean("eliminar");



                priceTotal=bundle.getFloat("priceTotal");
                price=bundle.getBoolean("price");

                fechaServidor=bundle.getString("fechaServidor");



            }
            if (resultCode == Activity.RESULT_CANCELED) {
                System.out.println( "LOS DATOS NO LLEGARON");

            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(agregar){

            /*eliminarObject(mRestaurante_pedido);
            Restaurante_PedidoProcesResultsAdapter.cancelAllTimers();
            ProcesoOrdenActivity.countDownMap.clear();

            adapter.addResults2(ProcesoOrdenActivity.lista,ProcesOrderFragment.this);



            adapter.setResults(ProcesoOrdenActivity.lista,ProcesOrderFragment.this,getContext());*/

            adapter.removeItem(mRestaurante_pedido,fechaServidor);

            //adapter.notifyItemRemoved(posicion);

            if(adapter.resultsSize()<=0){
                pedidos_preparandose_empty.setVisibility(View.VISIBLE);
            }

            agregar=false;
        }


    }


    @Override
    public void onPause() {
        super.onPause();
    }



   /* private void seachOrder(View view){

        // Associate searchable configuration with the SearchView
        //SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = view.findViewById(R.id.searchView);
               // .getActionView();
       // searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapter.getFilter().filter(query);
                return false;
            }
        });
    }*/


    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            //getWindow().setStatusBarColor(Color.WHITE);
        }
    }




    private void funPusher(){
        ProcesoOrdenActivity.channel_proces.bind("my-event", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                System.out.println(data+"los datossssssssssssssssss 852");

                if(getActivity() !=null){

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try{

                                JsonParser parser = new JsonParser();
                                JsonElement mJson =  parser.parse(data);
                                Gson gson = new Gson();
                                Restaurante_Pedido object = gson.fromJson(mJson, Restaurante_Pedido.class);
                                listaNewData.add(object);
                                //update_pedido.setText("Tienes " + listaNewData.size() + " pedidos nuevos");
                                //update_pedido.setVisibility(View.VISIBLE);



                                recyclerView.setVisibility(View.VISIBLE);

                                pedidos_preparandose_empty.setVisibility(View.GONE);

                                for(Restaurante_Pedido pedido:listaNewData){
                                    adapter.addResults(pedido,ProcesOrderFragment.this,pedido.getHoraservidor());
                                }

                                listaNewData.clear();

                                //update_pedido.setVisibility(View.GONE);


                            }catch (Exception e){
                                System.out.println(e.getMessage());
                            }
                        }
                    });
                }

            }
        });
        ProcesoOrdenActivity.pusher.connect();
    }


  /*  private void setOnclickUpdate(){
        update_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recyclerView.setVisibility(View.VISIBLE);

                pedidos_preparandose_empty.setVisibility(View.GONE);

              /*  ProcesoOrdenActivity.lista.addAll(listaNewData);

                updateFechaServidor(listaNewData.get(0).getHoraservidor());

                Restaurante_PedidoProcesResultsAdapter.cancelAllTimers();
                ProcesoOrdenActivity.countDownMap.clear();

                adapter.addResults2(ProcesoOrdenActivity.lista,ProcesOrderFragment.this);

                for(Restaurante_Pedido pedido:listaNewData){
                    System.out.println(pedido.getIdventa()+" id del pedido");
                    adapter.addResults(pedido,ProcesOrderFragment.this);
                }

                listaNewData.clear();

                update_pedido.setVisibility(View.GONE);


            }
        });
    }
*/



    @Override
    public void clickPedido(Restaurante_Pedido restaurante_pedido, int posisiton) {

            Intent intent= ProcesOrderActivity.newIntentOrderProcesDetail(getContext(),restaurante_pedido, posisiton);
            startActivityForResult(intent,CODE);
            System.out.println(restaurante_pedido.getListaProductos());


        Toast.makeText(getContext(),"position " +  posisiton,Toast.LENGTH_SHORT).show();

    }

    private void eliminarObject(Restaurante_Pedido restaurante_pedido){
        int posicion=0;
        int count=0;
        for(Restaurante_Pedido pedido:ProcesoOrdenActivity.lista){

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date d = null;
            try {
                d = formatter.parse(fechaServidor);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            pedido.setHoraservidor(Timestamp.valueOf(formatter.format(d)));


            if(restaurante_pedido.getIdventa()==pedido.getIdventa()){
                posicion=count;
            }
            count++;
        }

        ProcesoOrdenActivity.lista.remove(posicion);

    }

    private void updateFechaServidor(Timestamp fechaServidor){
        for(Restaurante_Pedido pedido:ProcesoOrdenActivity.lista){
            pedido.setHoraservidor(fechaServidor);

        }
    }

}