package com.mimiperla.empresayego.View.DrawerUI.historial.DetalleHistorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Empresa_historial;

public class OrderReadyHistorialActivity extends AppCompatActivity {

    public final static String RESTAURANTE_PEDIDO="com.example.empresayego.View.OrderUI.NewOrder.Detail.RestauranteObjeto";

    private Empresa_historial mRestaurante_pedido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ready_historial);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v->{
            onBackPressed();
        });

        reciveDataIntent();


        passDataFragemnt();



    }

    private void passDataFragemnt(){
        ProductsHistorialFragment fragment=ProductsHistorialFragment.newInstance(mRestaurante_pedido);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_historial_productos,fragment).commit();
    }


    private void reciveDataIntent(){
        if(getIntent().getSerializableExtra(RESTAURANTE_PEDIDO) !=null){
            mRestaurante_pedido=(Empresa_historial) getIntent().getSerializableExtra(RESTAURANTE_PEDIDO);
        }
    }


    public static Intent newIntentOrderReadyHistorial(Context context, Empresa_historial restaurante_pedido){
        Intent intent= new Intent(context, OrderReadyHistorialActivity.class);
        intent.putExtra(RESTAURANTE_PEDIDO,restaurante_pedido);
        return intent;
    }
}
