package com.example.empresayego.View.DrawerUI.productos.ProductoDetail;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Categoria_producto_empresa;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Modelo.Gson.GsonProducto;
import com.example.empresayego.Repository.Modelo.Producto;
import com.example.empresayego.View.DrawerUI.productos.AddProduct.NewProductActivity;
import com.example.empresayego.ViewModel.ProductoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class CategoriaDetailActivity extends AppCompatActivity  implements ProductoResultsAdapter.ItemClickProducto{

    private static final String CATEGORIA_PRODUCTO_EMPRESA = "categoriaproducto";
    private static final String GSONPRODUCTO = "gsonproductos";

    private static final int CODEPRODUCTO = 12;
    private ProductoViewModel viewModel;
    private ProductoResultsAdapter adapter;
    private RecyclerView fragment_product_recyclerView ;
    private SearchView searchView;
    private Categoria_producto_empresa categoria_producto_empresa;
    private TextView nombre_categoria;
    private ProgressBar progresbar_productos;
    private LinearLayout linearLayout15,reload_activity;
    private ImageView reload_image;
    private GsonProducto gsonproducto;

    private FloatingActionButton floatingActionButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);


        reciveDataIntent();

        loadData();

        declararWidget();

        setDataWidget();

        seachOrder();

        updateDataReponse();

        reloadViewModel();

        agregarProduct();

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v->{

            onBackPressed();
        });

        reload_activity.setVisibility(View.GONE);

    }

    public void declararWidget(){
        fragment_product_recyclerView=findViewById(R.id.fragment_product_recyclerView);
        searchView=findViewById(R.id.searchView);
        nombre_categoria=findViewById(R.id.nombre_categoria);
        progresbar_productos=findViewById(R.id.progresbar_productos);
        linearLayout15=findViewById(R.id.linearLayout15);
        reload_activity=findViewById(R.id.reload_activity);
        reload_image=findViewById(R.id.reload_image);


        floatingActionButtonAdd=findViewById(R.id.floatingActionButtonAdd);

    }

    private void loadData(){
        adapter= new ProductoResultsAdapter();
        viewModel= ViewModelProviders.of(this).get(ProductoViewModel.class);
        viewModel.init();
        viewModel.getProductoGsonLiveData().observe(this, gsonProducto -> {
            progresbar_productos.setVisibility(View.GONE);

            if(gsonProducto !=null){
                linearLayout15.setVisibility(View.VISIBLE);
                fragment_product_recyclerView.setVisibility(View.VISIBLE);
                if(gsonProducto.getListaProducto()!=null) adapter.setResults(gsonProducto.getListaProducto(), CategoriaDetailActivity.this);

            }else {

                reload_activity.setVisibility(View.VISIBLE);
                Toast.makeText(this,"Verificar las redes de Wifi o volver a intentarlo",Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void setDataWidget(){
        nombre_categoria.setText(categoria_producto_empresa.getNombre());
        viewModel.searchProducto(categoria_producto_empresa.getIdcategoriaproductoempresa(),Empresa.sEmpresa.getIdempresa());
        fragment_product_recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        fragment_product_recyclerView.setAdapter(adapter);
    }


    private void updateDataReponse(){
        ProductoViewModel viewModelProducto = ViewModelProviders.of(this).get(ProductoViewModel.class);
        viewModelProducto.init();
        viewModelProducto.getProductoLiveData().observe(this, new Observer<Producto>() {
            @Override
            public void onChanged(Producto producto) {
                if(producto !=null){

                    adapter.modifiedState(producto);

                    Toast.makeText(CategoriaDetailActivity.this,"Fue actualizado el producto",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(CategoriaDetailActivity.this,"No fue actualizado",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    private void seachOrder(){

        // Associate searchable configuration with the SearchView
        //SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = findViewById(R.id.searchView);
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



    @Override
    public void clickItem(Producto producto,boolean newState) {

        viewModel.updateStateProduto(producto.getIdproducto(),producto.getIdempresa(),newState);

    }


    private void reciveDataIntent() {
        if (getIntent().getSerializableExtra(CATEGORIA_PRODUCTO_EMPRESA) != null) {
            categoria_producto_empresa= (Categoria_producto_empresa) getIntent().getSerializableExtra(CATEGORIA_PRODUCTO_EMPRESA);
        }

    }

    public static Intent startIntentCategoriaDetailActivity(Context context, Categoria_producto_empresa categoria_producto_empresa){
        Intent intent= new Intent(context, CategoriaDetailActivity.class);
        intent.putExtra(CATEGORIA_PRODUCTO_EMPRESA,categoria_producto_empresa);
        return intent;
    }

    private void reloadViewModel(){
        reload_image.setOnClickListener(v->{
            viewModel.searchProducto(categoria_producto_empresa.getIdcategoriaproductoempresa(),Empresa.sEmpresa.getIdempresa());
        });
    }

    private void agregarProduct(){
        floatingActionButtonAdd.setOnClickListener(v->{

            Intent intent= NewProductActivity.startIntentCategoriaDetailActivity(CategoriaDetailActivity.this,categoria_producto_empresa);
            startActivityForResult(intent,CODEPRODUCTO);
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CODEPRODUCTO){
            if(resultCode == Activity.RESULT_OK){

                Bundle bundle= data.getExtras();

                gsonproducto=(GsonProducto) bundle.getSerializable(GSONPRODUCTO);

                gsonproducto.getListaProducto().forEach(v->{
                    adapter.addProduct(v);
                });


            }
        }

    }
}
