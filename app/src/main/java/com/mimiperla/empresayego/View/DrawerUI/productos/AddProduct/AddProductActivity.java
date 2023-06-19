package com.mimiperla.empresayego.View.DrawerUI.productos.AddProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Categoria_producto_empresa;
import com.mimiperla.empresayego.Repository.Modelo.Empresa;

public class AddProductActivity extends AppCompatActivity implements UpdatePriceMenuFragment.OnFragmentInteractionListener{

    private static final String CATEGORIA_PRODUCTO_EMPRESA = "categoriaproducto";

    private Categoria_producto_empresa mCategoria_producto_empresa;

    private Toolbar mToolbar;

    private CardView button_update_price,add_new_product;

    private TextView precio_menu;

    private Float priceMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        reciveDataIntent();
        priceMenu=Empresa.sEmpresa.getPrecio_menu();
        declararWidgets();
        clickChooseTypeMenu();
        clickUpdatePrice();

        mToolbar.setNavigationOnClickListener(v->{
            onBackPressed();
        });
    }

    private void declararWidgets(){
        button_update_price=findViewById(R.id.button_update_price);
        add_new_product=findViewById(R.id.add_new_product);
        precio_menu=findViewById(R.id.precio_menu);
        mToolbar=findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        precio_menu.setText(String.valueOf(priceMenu));
    }

    private void clickChooseTypeMenu(){
        add_new_product.setOnClickListener(v->{
            TipoMenuFragment fragment= TipoMenuFragment.newInstance(mCategoria_producto_empresa);
            fragment.show(getSupportFragmentManager(),TipoMenuFragment.TAG);
        });

    }

    private void clickUpdatePrice(){
        button_update_price.setOnClickListener(v->{
            UpdatePriceMenuFragment fragment=UpdatePriceMenuFragment.newInstance();
            fragment.show(getSupportFragmentManager(),UpdatePriceMenuFragment.TAG);
        });

    }

    @Override
    public void stateUpdatePrice(boolean respuesta, float newPrice) {
        if(respuesta){
            priceMenu=newPrice;
            precio_menu.setText(String.valueOf(priceMenu));
        }
    }


    private void reciveDataIntent() {
        if (getIntent().getSerializableExtra(CATEGORIA_PRODUCTO_EMPRESA) != null) {
            mCategoria_producto_empresa= (Categoria_producto_empresa) getIntent().getSerializableExtra(CATEGORIA_PRODUCTO_EMPRESA);
        }


    }

    public static Intent startIntentAddProductActivity(Context context, Categoria_producto_empresa categoria_producto_empresa){
        Intent intent= new Intent(context, AddProductActivity.class);
        intent.putExtra(CATEGORIA_PRODUCTO_EMPRESA,categoria_producto_empresa);
        return intent;
    }
}
