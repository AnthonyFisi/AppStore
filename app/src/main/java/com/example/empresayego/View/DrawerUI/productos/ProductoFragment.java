package com.example.empresayego.View.DrawerUI.productos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Modelo.Gson.GsonProducto;
import com.example.empresayego.Repository.Modelo.Producto;
import com.example.empresayego.ViewModel.ProductoViewModel;

public class ProductoFragment extends Fragment  implements ProductoResultsAdapter.ItemClickProducto{

    private ProductoViewModel viewModel;
    private ProductoResultsAdapter adapter;
    private RecyclerView fragment_product_recyclerView ;


   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter= new ProductoResultsAdapter();
        viewModel= ViewModelProviders.of(this).get(ProductoViewModel.class);
        viewModel.init();
        viewModel.getRepartidorLiveData().observe(this, gsonProducto -> {

            if(gsonProducto !=null){

                adapter.setResults(gsonProducto.getListaProductos(),ProductoFragment.this);

            }
        });


    }*/

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_producto, container, false);
      //  viewModel.searchProductoById(Empresa.sEmpresa.getIdempresa());

        //declararWidget(root);
        //setDataWidget();


        return root;
    }
/*
    public void declararWidget(View view){
        fragment_product_recyclerView=view.findViewById(R.id.fragment_product_recyclerView);

    }

    public void setDataWidget(){

        fragment_product_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        fragment_product_recyclerView.setAdapter(adapter);
    }*/


    @Override
    public void clickItem(Producto producto) {

    }
}