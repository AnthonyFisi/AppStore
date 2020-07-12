package com.example.empresayego.View.OrderUI.ReadyOrder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
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

import com.example.empresayego.View.OrderUI.ReadyOrder.ReadyOrderDetail.OrderReadyDetailActivity;
import com.example.empresayego.ViewModel.Restaurante_PedidoViewModel;

import java.util.ArrayList;
import java.util.List;

public class ReadyOrderFragment extends Fragment implements  Restaurante_PedidoReadyResultsAdapter.ClickPedidoReciente {

    public final static int CODE=3;

    private Restaurante_PedidoViewModel viewModel;
    private Restaurante_PedidoReadyResultsAdapter adapter;

    private boolean agregar=false;

    private int idEmpresa=23;

    private int position=1000;

    private LinearLayout imagen;

    private boolean data=false;

    private Restaurante_Pedido mRestaurante_pedido=new Restaurante_Pedido();

    private RecyclerView recyclerView;

    private SearchView searchView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        System.out.println("CANTIDAD DEL GENERAL PEDIDOS" + MainActivity.lista.size());

        adapter = new Restaurante_PedidoReadyResultsAdapter();
        viewModel = ViewModelProviders.of(this).get(Restaurante_PedidoViewModel.class);
        viewModel.init();
        viewModel.getRestaurante_PedidoLiveData().observe(this, new Observer<GsonRestaurante_Pedido>() {
            @Override
            public void onChanged(GsonRestaurante_Pedido gsonRestaurante_pedido) {
                if(gsonRestaurante_pedido !=null){
                    if(gsonRestaurante_pedido.getListaRestaurante_Pedido() !=null){
                        adapter.setResults(gsonRestaurante_pedido.getListaRestaurante_Pedido(), ReadyOrderFragment.this);
                        data=true;
                        System.out.println("RESULTADO DE DATA" +data);
                    }

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

        View root = inflater.inflate(R.layout.fragment_ready_order, container, false);

        imagen=root.findViewById(R.id.imagen);
        imagen.setVisibility(View.GONE);

     recyclerView=root.findViewById(R.id.fragment_restaurante_ordenReady);
        //viewModel.searchRestaurantePedidoByEmpresaReady(idEmpresa);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        searchOrder(root);

        return root;
    }



    private void searchOrder(View view){

        // Associate searchable configuration with the SearchView
        //SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = view.findViewById(R.id.searchView);//.getActionView();
        // searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                System.out.println("ENTRAMOS POR ACA O  SUBMIT"+query);
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                System.out.println("ENTRAMOS POR ACA O  CHANGES"+query);

                // filter recycler view when text is changed
                adapter.getFilter().filter(query);
                return false;
            }
        });
    }


    @Override
    public void clickPedido(Restaurante_Pedido restaurante_pedido, int posisiton) {

        Toast.makeText(getContext(),"position " +  posisiton,Toast.LENGTH_SHORT).show();
        Intent intent= OrderReadyDetailActivity.newIntentOrderReadyDetail(getContext(),restaurante_pedido, posisiton);
        startActivityForResult(intent,CODE);
        System.out.println(restaurante_pedido.getIdventa() + "  "+ restaurante_pedido.getUsuario_nombre());

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

                System.out.println( mRestaurante_pedido.getUsuario_nombre() + " LOS DATOS LLEGARON " + position +" ##"+ "la rpta es " + agregar);



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

            viewModel.searchRestaurantePedidoByEmpresaReady(idEmpresa);
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