package com.mimiperla.empresayego.View.OrderUI.ProcessOrder.Detail.Ayuda.IncrementTime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.mimiperla.empresayego.View.OrderUI.ProcessOrder.Restaurante_PedidoProcesResultsAdapter;
import com.mimiperla.empresayego.View.ProcesoOrdenActivity;

public class IncrementTimeActivity extends AppCompatActivity implements IncrementTimeFragment.OnDataPass{

    private Restaurante_Pedido mRestaurante_pedido;

    public final static String RESTAURANTE_PEDIDO_AYUDA="com.example.empresayego.View.OrderUI.NewOrder.Detail.RestauranteObjeto";
    private Boolean respuesta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_increment_time);
        reciveDataIntent();

      /*  IncrementTimeFragment fragment1 = (IncrementTimeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_INCREMENT_TIME_FRAGMENT);
        if(fragment1 !=null){
            fragment1.setPassData(mRestaurante_pedido);
        }*/

      respuesta=false;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v->{
            onBackPressed();
        });
        Bundle bundle=new Bundle();
        bundle.putSerializable("EMPRESA",mRestaurante_pedido);
        IncrementTimeFragment fragment=new IncrementTimeFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_INCREMENT_TIME_FRAGMENT,fragment).commit();
    }


    private void reciveDataIntent(){
        if(getIntent().getSerializableExtra(RESTAURANTE_PEDIDO_AYUDA) !=null){
            mRestaurante_pedido=(Restaurante_Pedido) getIntent().getSerializableExtra(RESTAURANTE_PEDIDO_AYUDA);
        }
    }



    public static Intent newIntentIncrementTimeActivity(Context context, Restaurante_Pedido restaurante_pedido){
        Intent intent= new Intent(context, IncrementTimeActivity.class);
        intent.putExtra(RESTAURANTE_PEDIDO_AYUDA,restaurante_pedido);
        return intent;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(respuesta){

            Restaurante_PedidoProcesResultsAdapter.cancelAllTimers();
            ProcesoOrdenActivity.countDownMap.clear();

            Intent intent= ProcesoOrdenActivity.startIntentProcesoOrdenActivity(this,true);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }

    @Override
    public void onDataPass(boolean respuesta) {
        this.respuesta=respuesta;

    }

}
