package com.example.empresayego.View.OrderUI.NewOrder;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.empresayego.MainActivity;
import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Modelo.Gson.GsonRestaurante_Pedido;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.example.empresayego.View.OrderUI.NewOrder.Detail.NewOrderDetailActivity;
import com.example.empresayego.ViewModel.Restaurante_PedidoViewModel;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.pusher.client.channel.SubscriptionEventListener;


import java.util.ArrayList;
import java.util.List;
public class ListNewOdersFragment extends Fragment implements Restaurante_PedidoResultsAdapter.ClickPedidoReciente{


    private final static int CODE=1;

    private boolean agregar=false;

    private int idEmpresa=23;

    private int position=1000;

    private Restaurante_Pedido mRestaurante_pedido=new Restaurante_Pedido();

    private Restaurante_PedidoViewModel viewModel;
    private Restaurante_PedidoResultsAdapter adapter;
    private  RecyclerView recyclerView;

    private List<Restaurante_Pedido> listaNewData= new ArrayList<>();

    private TextView text_gallery;

    private  MediaPlayer mediaPlayer;


    private boolean data=false;

    private boolean next=false;

    private LinearLayout imagen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mediaPlayer=MediaPlayer.create(getContext(),R.raw.soundorden);
        adapter = new Restaurante_PedidoResultsAdapter();
        viewModel = ViewModelProviders.of(this).get(Restaurante_PedidoViewModel.class);
        viewModel.init();
        viewModel.getRestaurante_PedidoLiveData().observe(this, new Observer<GsonRestaurante_Pedido>() {
            @Override
            public void onChanged(GsonRestaurante_Pedido gsonRestaurante_pedido) {

                if(gsonRestaurante_pedido !=null){
                    if(gsonRestaurante_pedido.getListaRestaurante_Pedido() !=null){
                        adapter.setResults(gsonRestaurante_pedido.getListaRestaurante_Pedido(),ListNewOdersFragment.this);
                        System.out.println("entraron los datos");
                        data=true;

                    }else{


                        List<Restaurante_Pedido> lista= new ArrayList<>();
                        Restaurante_Pedido pedido=new Restaurante_Pedido();
                        pedido.setCodigo_repartidor("");
                        lista.add(pedido);
                        adapter.setResults( lista,ListNewOdersFragment.this);

                        adapter.removeItem(pedido,0);
                        System.out.println("los datos 1");

                    }

                }else{
                    System.out.println("los datos 2");


                    List<Restaurante_Pedido> lista= new ArrayList<>();
                   Restaurante_Pedido pedido=new Restaurante_Pedido();
                   pedido.setCodigo_repartidor("");
                   lista.add(pedido);
                    adapter.setResults( lista,ListNewOdersFragment.this);
                    adapter.removeItem(pedido,0);
                }


            }
        });


        if(Empresa.sEmpresa.isDisponible()){
            AsyncTaskRunner runner = new AsyncTaskRunner();
            runner.execute("1");
        }


    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_new_orders, container, false);

        imagen=view.findViewById(R.id.imagen);


        imagen.setVisibility(View.GONE);






        funPusher();
        recyclerView=view.findViewById(R.id.fragment_restaurante_orden);
        text_gallery=view.findViewById(R.id.text_gallery);


        setOnclickUpdate();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return view;
    }


    private void funPusher(){
        MainActivity.channel.bind("my-event", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                System.out.println(data+"los datossssssssssssssssss");

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
                                text_gallery.setText("Tienes " + listaNewData.size() + " pedidos nuevos");
                                text_gallery.setVisibility(View.VISIBLE);

                                imagen.setVisibility(View.GONE);

                                mediaPlayer.start();
                                mediaPlayer.start();


                            }catch (Exception e){
                                System.out.println(e.getMessage());
                            }
                        }
                    });
                }

            }
        });
        MainActivity.pusher.connect();
    }

    private void setOnclickUpdate(){
        text_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(Restaurante_Pedido pedido:listaNewData){
                    adapter.addResults(pedido,true);
                }

                listaNewData.clear();

                text_gallery.setVisibility(View.GONE);

            }
        });
    }

    @Override
    public void clickPedido(Restaurante_Pedido restaurante_pedido,int position) {
        Toast.makeText(getContext(),"position " + position,Toast.LENGTH_SHORT).show();
        Intent intent= NewOrderDetailActivity.newIntentOrderDetail(getContext(),restaurante_pedido,position);
        startActivityForResult(intent,CODE);
        System.out.println(restaurante_pedido.getListaProductos());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == CODE) {
            if(resultCode == Activity.RESULT_OK){

                // ProductoService producto=(ProductoService) data.getSerializableExtra("data");
                //agregar= data.getBooleanExtra("agregar",false);
                Bundle bundle= data.getExtras();
                agregar=bundle.getBoolean("agregar");
                position=bundle.getInt("position");
                mRestaurante_pedido=(Restaurante_Pedido) bundle.getSerializable("objeto");

                System.out.println( mRestaurante_pedido.getUsuario_nombre() + " LOS DATOS LLEGARON " + position +" ##");



            }
            if (resultCode == Activity.RESULT_CANCELED) {
                System.out.println( "LOS DATOS NO LLEGARON");

            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("ESTADO " +agregar);

        if(agregar){
            System.out.println( "ESTAMOS REMOVIENDO");


            adapter.removeItem(mRestaurante_pedido,position);

            if(adapter.resultSize()==0){
                imagen.setVisibility(View.VISIBLE);
            }
            agregar=false;
        }
    }



    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {

            viewModel.searchRestaurantePedidoByEmpresa(idEmpresa);
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                int time = Integer.parseInt(params[0])*1000;

                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            //finalResult.setText(result);

            if(data || listaNewData.size()!=0){
                System.out.println("ENTRA POR ACA O NO");
                imagen.setVisibility(View.GONE);
                recyclerView.setAdapter(adapter);

            }else{
                System.out.println("nunaaaaaaaaaaaENTRA POR ACA O NO");
                imagen.setVisibility(View.VISIBLE);


            }
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getContext(),
                    "ProgressDialog",
                    "Wait for "+5+ " seconds");
        }


        @Override
        protected void onProgressUpdate(String... text) {
          //  finalResult.setText(text[0]);

        }
    }


}