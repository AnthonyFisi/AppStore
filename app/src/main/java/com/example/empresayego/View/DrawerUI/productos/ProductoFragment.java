package com.example.empresayego.View.DrawerUI.productos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Categoria_producto_empresa;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Modelo.Gson.GsonCategoria_producto_empresa;
import com.example.empresayego.View.DrawerUI.productos.AddProduct.AddProductActivity;
import com.example.empresayego.View.DrawerUI.productos.ProductoDetail.CategoriaDetailActivity;
import com.example.empresayego.View.DrawerUI.soporte.SoporteFragment;
import com.example.empresayego.View.OrderUI.ReadyOrder.ReadyOrderDetail.Products.ProductsResultsAdapter;
import com.example.empresayego.ViewModel.Categoria_producto_empresaViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductoFragment extends Fragment  implements ProductosResultsAdapter4.ClickCategoria{

    private Categoria_producto_empresaViewModel viewModel;
    private ProductosResultsAdapter4 adapter;
    private RecyclerView fragment_product_recyclerView ;
    private CardView anadir_producto_menu;
    private Categoria_producto_empresa mCategoria_producto_empresa;
    private ImageView ic_back;

    private BackToInicio mBackToInicio;

    private ProgressBar progressbar;

    private LinearLayout reload_activity;

    private ImageView reload_image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCategoria_producto_empresa=new Categoria_producto_empresa();

        adapter=new ProductosResultsAdapter4();
        viewModel= new ViewModelProvider(this).get(Categoria_producto_empresaViewModel.class);
        viewModel.init();
        viewModel.getGsonCategoria_producto_empresaLiveData().observe(this, new Observer<GsonCategoria_producto_empresa>() {
            @Override
            public void onChanged(GsonCategoria_producto_empresa gsonCategoria_producto_empresa) {
                progressbar.setVisibility(View.GONE);
                if(gsonCategoria_producto_empresa !=null){

                    mCategoria_producto_empresa=gsonCategoria_producto_empresa.getListaCategoriaEmpresa().get(0);
                    adapter.setResults(gsonCategoria_producto_empresa.getListaCategoriaEmpresa(),ProductoFragment.this);
                    Toast.makeText(getContext(),"Fue actualizado el producto",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getContext(),"No fue actualizado",Toast.LENGTH_SHORT).show();

                    reload_activity.setVisibility(View.VISIBLE);
                }
            }
        });



    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_producto, container, false);

        declararWidget(root);
        setDataWidget();
        //seachOrder(root);
        agregarProduct();

        ic_back.setOnClickListener(v->{
            backToInicio();
        });

        clickReload();

        return root;
    }

    private void declararWidget(View view){
        fragment_product_recyclerView=view.findViewById(R.id.fragment_product_recyclerView);
        anadir_producto_menu=view.findViewById(R.id.anadir_producto_menu);

        ic_back=view.findViewById(R.id.ic_back);

        progressbar=view.findViewById(R.id.progressbar);

        reload_activity=view.findViewById(R.id.reload_activity);

        reload_image=view.findViewById(R.id.reload_image);
    }

    public void setDataWidget(){
        viewModel.searchCategoriaProductoEmpresa(Empresa.sEmpresa.getIdempresa());
        fragment_product_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        fragment_product_recyclerView.setAdapter(adapter);


    }



  private void agregarProduct(){
      anadir_producto_menu.setOnClickListener(v->{
          Intent intent= AddProductActivity.startIntentAddProductActivity(getContext(),mCategoria_producto_empresa);
          startActivity(intent);
      });
  }

    @Override
    public void itemClickCategoria(Categoria_producto_empresa categoria, List<Categoria_producto_empresa> listaCategoria) {
        Intent intent= CategoriaDetailActivity.startIntentCategoriaDetailActivity(getContext(),categoria);
        startActivity(intent);
    }


    private void backToInicio() {
        mBackToInicio.back();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mBackToInicio = (ProductoFragment.BackToInicio) context;
    }

    public interface BackToInicio{
        void  back();
    }

    private void clickReload(){
        reload_image.setOnClickListener(v->{
            viewModel.searchCategoriaProductoEmpresa(Empresa.sEmpresa.getIdempresa());
            progressbar.setVisibility(View.VISIBLE);
            reload_activity.setVisibility(View.GONE);
        });
    }

}