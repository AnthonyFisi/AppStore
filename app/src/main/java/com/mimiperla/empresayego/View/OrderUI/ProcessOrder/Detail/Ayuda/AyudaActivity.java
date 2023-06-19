package com.mimiperla.empresayego.View.OrderUI.ProcessOrder.Detail.Ayuda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.mimiperla.empresayego.View.OrderUI.ProcessOrder.Detail.Ayuda.CancelarPedido.CancelarPedidoActivity;
import com.mimiperla.empresayego.View.OrderUI.ProcessOrder.Detail.Ayuda.IncrementPrice.IncrementPriceActivity;
import com.mimiperla.empresayego.View.OrderUI.ProcessOrder.Detail.Ayuda.IncrementTime.IncrementTimeActivity;

public class AyudaActivity extends AppCompatActivity {//implements  IncrementTimeFragment.OnDataPass, EliminarFragment.OnDataPassEliminar, IncrementPriceFragment.OnDataPassPrice{

    public final static String RESTAURANTE_PEDIDO_AYUDA="com.example.empresayego.View.OrderUI.NewOrder.Detail.RestauranteObjeto";

    public final static String POSITION_AYUDA="com.example.empresayego.View.OrderUI.NewOrder.Detail.posicion";


    private Restaurante_Pedido mRestaurante_pedido;

    private int position;


    private CardView activity_ayuda_DEMORAR_PEDIDO,activity_ayuda_AUMENTAR_PRECIO,activity_ayuda_CANCELAR_PEDIDO,activity_ayuda_CONTACTAR_EMPRESA;

    private Intent returnIntent ;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
        returnIntent = new Intent();
        bundle= new Bundle();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v->{
            onBackPressed();
        });
        reciveDataIntent();
        declararWidget();


        clickCardViewAyuda();


    }



    private void clickCardViewAyuda(){

        activity_ayuda_DEMORAR_PEDIDO.setOnClickListener( v->{

            Intent intent= IncrementTimeActivity.newIntentIncrementTimeActivity(this,mRestaurante_pedido);
            startActivity(intent);
           /* IncrementTimeFragment addPhotoBottomDialogFragment =
                    IncrementTimeFragment.newInstance(mRestaurante_pedido);
            addPhotoBottomDialogFragment.show(getSupportFragmentManager(),
                    IncrementTimeFragment.TAG);*/

        });

        activity_ayuda_AUMENTAR_PRECIO.setOnClickListener( v->{
            /*IncrementPriceFragment addPhotoBottomDialogFragment =
                    IncrementPriceFragment.newInstance(mRestaurante_pedido);
            addPhotoBottomDialogFragment.show(getSupportFragmentManager(),
                    IncrementPriceFragment.TAG);*/
            Intent intent= IncrementPriceActivity.newIntentIncrementPriceActivity(this,mRestaurante_pedido);
            startActivity(intent);
        });

        activity_ayuda_CANCELAR_PEDIDO.setOnClickListener(v->{

            Intent intent= CancelarPedidoActivity.newIntentCancelarPedidoActivity(this,mRestaurante_pedido);
            startActivity(intent);

            /*
            EliminarFragment addPhotoBottomDialogFragment =
                    EliminarFragment.newInstance(mRestaurante_pedido);
            addPhotoBottomDialogFragment.show(getSupportFragmentManager(),
                    IncrementPriceFragment.TAG);*/
        });


    }


    private void reciveDataIntent(){
        if(getIntent().getSerializableExtra(RESTAURANTE_PEDIDO_AYUDA) !=null){
            mRestaurante_pedido=(Restaurante_Pedido) getIntent().getSerializableExtra(RESTAURANTE_PEDIDO_AYUDA);
        }
        if(getIntent().getSerializableExtra(POSITION_AYUDA) !=null){
            position=getIntent().getIntExtra(POSITION_AYUDA,100000);
        }

        /*if(getIntent().getSerializableExtra(POSITION_COUNT) !=null){
            positionCount=getIntent().getIntExtra(POSITION_COUNT,100000);
        }*/


        System.out.println( mRestaurante_pedido.getNombre() + " el objeto llego  y la possicion " + position +" ##");

    }



    public static Intent newIntentOrderProcesDetail(Context context, Restaurante_Pedido restaurante_pedido, int position){
        Intent intent= new Intent(context, AyudaActivity.class);
        intent.putExtra(RESTAURANTE_PEDIDO_AYUDA,restaurante_pedido);
        intent.putExtra(POSITION_AYUDA,position);
      //  intent.putExtra(POSITION_COUNT,positionCount);
        return intent;
    }


    private void declararWidget(){
        activity_ayuda_DEMORAR_PEDIDO=findViewById(R.id.activity_ayuda_DEMORAR_PEDIDO);
        activity_ayuda_AUMENTAR_PRECIO=findViewById(R.id.activity_ayuda_AUMENTAR_PRECIO);
        activity_ayuda_CANCELAR_PEDIDO=findViewById(R.id.activity_ayuda_CANCELAR_PEDIDO);
        activity_ayuda_CONTACTAR_EMPRESA=findViewById(R.id.activity_ayuda_CONTACTAR_EMPRESA);
    }


    private void returnData(){

        System.out.println( mRestaurante_pedido.getNombre() + " los datos estan retornando del FRAGMENT INCREMENTTIME" + position +" ##");


       // bundle.putBoolean("updateTime",updateTime);
        bundle.putInt("position",position);
        //bundle.putInt("positionCount",positionCount);
        bundle.putSerializable("objeto",mRestaurante_pedido);
       // bundle.putInt("cantidadTiempo",tiempo);
        //bundle.putBoolean("eliminar",eliminar);

        returnIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK,returnIntent);
    }

/*
    @Override
    public void onDataPass(boolean agregar,int tiempo) {
        bundle.putBoolean("updateTime",agregar);
        bundle.putInt("cantidadTiempo",tiempo);
        returnData();
    }

    @Override
    public void onDataPassEliminar(boolean agregar) {

        bundle.putBoolean("eliminar",agregar);
        returnData();
    }

    @Override
    public void onDataPassIncrementPrice(boolean aumentar_precio, float cantidad) {
        bundle.putFloat("priceTotal",cantidad);
        bundle.putBoolean("price",aumentar_precio);
        returnData();

    }*/
}
