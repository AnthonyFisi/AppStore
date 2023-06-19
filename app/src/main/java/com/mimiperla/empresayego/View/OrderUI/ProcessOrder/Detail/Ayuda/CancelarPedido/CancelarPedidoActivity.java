package com.mimiperla.empresayego.View.OrderUI.ProcessOrder.Detail.Ayuda.CancelarPedido;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.mimiperla.empresayego.View.OrderUI.ProcessOrder.Restaurante_PedidoProcesResultsAdapter;
import com.mimiperla.empresayego.View.ProcesoOrdenActivity;

public class CancelarPedidoActivity extends AppCompatActivity implements EliminarFragment.OnDataPassEliminar{

    private Restaurante_Pedido mRestaurante_pedido;

    public final static String RESTAURANTE_PEDIDO_AYUDA="com.example.empresayego.View.OrderUI.NewOrder.Detail.RestauranteObjeto";

    private boolean respuesta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelar_pedido);

        reciveDataIntent();

        respuesta=false;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v->{
            onBackPressed();
        });
        /*EliminarFragment fragment1 = (EliminarFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_ELIMINAR_FRAGMENT);
        if(fragment1 !=null){
            fragment1.setPassData(mRestaurante_pedido);
        }*/
        Bundle bundle=new Bundle();
        bundle.putSerializable("EMPRESA",mRestaurante_pedido);
        EliminarFragment fragment=new EliminarFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_ELIMINAR_FRAGMENT,fragment).commit();
    }


    private void reciveDataIntent(){
        if(getIntent().getSerializableExtra(RESTAURANTE_PEDIDO_AYUDA) !=null){
            mRestaurante_pedido=(Restaurante_Pedido) getIntent().getSerializableExtra(RESTAURANTE_PEDIDO_AYUDA);
        }
    }



    public static Intent newIntentCancelarPedidoActivity(Context context, Restaurante_Pedido restaurante_pedido){
        Intent intent= new Intent(context, CancelarPedidoActivity.class);
        intent.putExtra(RESTAURANTE_PEDIDO_AYUDA,restaurante_pedido);
        return intent;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(respuesta){

            Restaurante_PedidoProcesResultsAdapter.cancelAllTimers();
            ProcesoOrdenActivity.countDownMap.clear();

            Intent intent= ProcesoOrdenActivity.startIntentProcesoOrdenActivity(this,false);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }

    @Override
    public void onDataPassEliminar(boolean respuesta) {
        this.respuesta=respuesta;

    }
}
