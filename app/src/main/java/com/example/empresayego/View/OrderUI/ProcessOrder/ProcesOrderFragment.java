package com.example.empresayego.View.OrderUI.ProcessOrder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.empresayego.MainActivity;
import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Modelo.Gson.GsonRestaurante_Pedido;
import com.example.empresayego.Repository.Modelo.Orden_estado_restaurante;
import com.example.empresayego.Repository.Modelo.Orden_estado_restaurantePK;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.example.empresayego.View.OrderUI.ProcessOrder.Detail.ProcesOrderActivity;
import com.example.empresayego.ViewModel.Orden_estado_restauranteViewModel;
import com.example.empresayego.ViewModel.Restaurante_PedidoViewModel;

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


public class ProcesOrderFragment extends Fragment implements Restaurante_PedidoProcesResultsAdapter.ClickPedidoReciente {

    private final static int CODE=2;

    private Restaurante_PedidoViewModel viewModel;
    private Orden_estado_restauranteViewModel viewModel2;
    private Restaurante_PedidoProcesResultsAdapter adapter;
    private LinearLayout imagen;
    private TextView mensaje;
    private   RecyclerView recyclerView;
    private SearchView searchView;

    private int positionCount;

    private boolean eliminar=false;

    private List<Restaurante_Pedido> listaUpdateSate=new ArrayList<>();

    private boolean data=false;

    boolean respuesta=false;

    private boolean agregar=false;

    private boolean respuestaChangeTime=false;


    private int position=1000;

    private Restaurante_Pedido mRestaurante_pedido=new Restaurante_Pedido();

    private boolean updateTime=false;

    private int cantidadTiempo=0;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       Restaurante_PedidoProcesResultsAdapter.cancelAllTimers2();
       MainActivity.lista.clear();

        adapter = new Restaurante_PedidoProcesResultsAdapter();
        viewModel = ViewModelProviders.of(this).get(Restaurante_PedidoViewModel.class);
        viewModel.init();
        viewModel.getRestaurante_PedidoLiveData().observe(this, new Observer<GsonRestaurante_Pedido>() {
            @Override
            public void onChanged(GsonRestaurante_Pedido gsonRestaurante_pedido) {
                if(gsonRestaurante_pedido !=null){
                    if(gsonRestaurante_pedido.getListaRestaurante_Pedido() !=null){

                        List<Restaurante_Pedido> lista=new ArrayList<>();

                        for(Restaurante_Pedido data:gsonRestaurante_pedido.getListaRestaurante_Pedido()){
                           if(updateStateReady(data)){
                                listaUpdateSate.add(data);
                            }else{

                               MainActivity.lista.add(data);
                                lista.add(data);
                            }
                        }
          adapter.setResults(lista, ProcesOrderFragment.this);
                        data=true;
                    }

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

        if(Empresa.sEmpresa.isDisponible()){
            AsyncTaskRunner runner = new AsyncTaskRunner();
            runner.execute("3");
        }

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_proces_orders, container, false);
        imagen=root.findViewById(R.id.imagen);
        mensaje=root.findViewById(R.id.mensaje);
        recyclerView=root.findViewById(R.id.fragment_restaurante_ordenProces);
        seachOrder(root);

        whiteNotificationBar(recyclerView);
        imagen.setVisibility(View.GONE);
        mensaje.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return root;
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
                positionCount=bundle.getInt("positionCount");
                updateTime=bundle.getBoolean("updateTime");
                cantidadTiempo=bundle.getInt("cantidadTiempo");

                eliminar=bundle.getBoolean("eliminar");

                if(updateTime){
                    System.out.println("LLEGO EL TIEMPO MODIFICADO");


                }else {

                    System.out.println("NO LLEGO EL TIEMPO");

                }



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


        if(eliminar){
            adapter.cancelAllTimers2();
            adapter.clear();



            MainActivity.lista.remove(position);



            adapter.setResults(MainActivity.lista,ProcesOrderFragment.this);


            eliminar=false;

        }


        if(agregar){
            //adapter.removeItem(mRestaurante_pedido,position);


            adapter.cancelAllTimers2();
            adapter.clear();



            MainActivity.lista.remove(position);



            adapter.setResults(MainActivity.lista,ProcesOrderFragment.this);



            agregar=false;

        }

        if(updateTime){

            if (adapter != null) {

               // adapter.cancelAllTimers(positionCount,cantidadTiempo);
                adapter.cancelAllTimers2();
                adapter.clear();
                //adapter.modified(cantidadTiempo,mRestaurante_pedido.getIdventa());


                List<Restaurante_Pedido> provisional=new ArrayList<>();

                for(Restaurante_Pedido pedido:MainActivity.lista){

                    if(mRestaurante_pedido.getIdventa()==pedido.getIdventa()){

                        int restante=tiempoRestante(pedido);
                        int nuevaCantidad=cantidadTiempo + restante;

                        pedido.setTiempo_espera(String.valueOf(nuevaCantidad));
                    }

                    provisional.add(pedido);

                }

                for(Restaurante_Pedido pedido:MainActivity.lista){
                    System.out.println(pedido.getIdventa()  + " --- LLENANDO OTRA VEZ ");
                }


                    adapter.setResults(provisional,ProcesOrderFragment.this);


                updateTime=false;

               /* adapter.clear();

                AsyncTaskRunner runner = new AsyncTaskRunner();
                runner.execute("3");

                updateTime=false;*/

            }
        }


    }


    @Override
    public void onPause() {
        super.onPause();
        MainActivity.dataEmpty=true;

    }

    @Override
    public void clickPedido(Restaurante_Pedido restaurante_pedido, int posisiton,int positionCount) {
        Toast.makeText(getContext(),"position " +  posisiton,Toast.LENGTH_SHORT).show();
        Intent intent= ProcesOrderActivity.newIntentOrderProcesDetail(getContext(),restaurante_pedido, posisiton,positionCount);
        startActivityForResult(intent,CODE);
        System.out.println(restaurante_pedido.getListaProductos());

    }

    @Override
    public void updateStateToReadyOrder(Restaurante_Pedido pedido,int position) {


        if(adapter.searchObject(pedido)){


        Orden_estado_restaurantePK pk =new Orden_estado_restaurantePK();
        pk.setIdventa(pedido.getIdventa());
        pk.setIdestado_venta(3);
        Orden_estado_restaurante ordenEstado=new Orden_estado_restaurante();
        ordenEstado.setId(pk);
        ordenEstado.setFecha(null);
        ordenEstado.setDetalle("");


        int restante=tiempoRestante(pedido);
            System.out.println("EL TIEMPO RESTANTE ES" + restante);

        int tiempo=Integer.valueOf(pedido.getTiempo_espera());

            System.out.println("EL TIEMPO TOTAL ES " + restante);




            viewModel2.updateEstadoProces(ordenEstado,pedido.getIdusuario());


            if(adapter.resultsSize()==0){
                imagen.setVisibility(View.VISIBLE);
                mensaje.setVisibility(View.VISIBLE);

            }

        }
    }





    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {

            if(adapter.getList().size()==0){

                int idEmpresa = 23;
                viewModel.searchRestaurantePedidoByEmpresaProces(idEmpresa);
            }
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

            if(data){
                System.out.println("ENTRA POR ACA O NO");
                imagen.setVisibility(View.GONE);
                mensaje.setVisibility(View.GONE);

                System.out.println(adapter.getList().size()+"CANTIDAD DE LA LISTA ADPATER");

                recyclerView.setAdapter(adapter);


            }else{
                System.out.println("nunaaaaaaaaaaaENTRA POR ACA O NO");
                imagen.setVisibility(View.VISIBLE);
                mensaje.setVisibility(View.VISIBLE);
            }


            System.out.println(" adapter size" + adapter.getList().size());

            for(Restaurante_Pedido p:listaUpdateSate){

                Orden_estado_restaurantePK pk=new Orden_estado_restaurantePK();
                pk.setIdventa(p.getIdventa());
                pk.setIdestado_venta(3);

                Orden_estado_restaurante estado= new Orden_estado_restaurante();
                estado.setId(pk);
                estado.setDetalle("");
                estado.setFecha(null);

                //adapter.cancelAllTimers();

                viewModel2.updateEstadoReady(estado,p.getIdusuario());
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




    private boolean updateStateReady (Restaurante_Pedido pedido){

        boolean respuesta=false;

        Timestamp ts = Timestamp.valueOf(pedido.getCodigo_repartidor());

        String pattern = "HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        String time1=dateFormat.format(ts);





        DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String time2 = dateFormat2.format(date);



        System.out.println("dateformated  " +time1+" |  fecha1 "+time2);



        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = null;
        Date date2 =null;
        try {
            date1 = format.parse(time1);
            date2=format.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long difference = date2.getTime() - date1.getTime();

        Long tiempoTotal= new Long(Integer.valueOf(pedido.getTiempo_espera())*60000);


        if((difference)>=tiempoTotal) {
            respuesta=true;
        }


        return respuesta;

    }

    private int tiempoRestante(Restaurante_Pedido pedido){


        Timestamp ts = Timestamp.valueOf(pedido.getCodigo_repartidor());

        String pattern = "HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        String time1=dateFormat.format(ts);





        DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String time2 = dateFormat2.format(date);



        System.out.println("dateformated  " +time1+" |  fecha1 "+time2);



        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = null;
        Date date2 =null;
        try {
            date1 = format.parse(time1);
            date2=format.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long difference = date2.getTime() - date1.getTime();

        int tiempo=(int)(difference/60000);

        return tiempo;


    }

    private void seachOrder(View view){

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
    }


    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            //getWindow().setStatusBarColor(Color.WHITE);
        }
    }




}