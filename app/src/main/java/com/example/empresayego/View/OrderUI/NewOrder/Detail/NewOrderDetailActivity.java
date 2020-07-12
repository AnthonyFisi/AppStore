package com.example.empresayego.View.OrderUI.NewOrder.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;

public class NewOrderDetailActivity extends AppCompatActivity implements NewOrderDetailFragment.OnDataPass {

    public final static String RESTAURANTE_PEDIDO="com.example.empresayego.View.OrderUI.NewOrder.Detail.RestauranteObjeto";

    public final static String POSITION="com.example.empresayego.View.OrderUI.NewOrder.Detail.posicion";

    private Restaurante_Pedido mRestaurante_pedido;

    private int position;

    private boolean agregar=false;

    String numeroOrden="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order_detail);



        //RECIVE DATA
        reciveDataIntent();


        //SEND DATA
        passDataFragemnt();


    }


    private void returnData(){

        System.out.println( mRestaurante_pedido.getUsuario_nombre() + " los datos estan retornando" + position +" ##");

        Intent returnIntent = new Intent();
        Bundle bundle= new Bundle();
        bundle.putBoolean("agregar",agregar);
        bundle.putInt("position",position);
        bundle.putSerializable("objeto",mRestaurante_pedido);
        returnIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK,returnIntent);
    }


    private void passDataFragemnt(){
        NewOrderDetailFragment fragment = (NewOrderDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_new_order_detail);
        if(fragment !=null){
            fragment.setPassData(mRestaurante_pedido);
        }
    }
    private void reciveDataIntent(){
        if(getIntent().getSerializableExtra(RESTAURANTE_PEDIDO) !=null){
            mRestaurante_pedido=(Restaurante_Pedido) getIntent().getSerializableExtra(RESTAURANTE_PEDIDO);
        }
        if(getIntent().getSerializableExtra(POSITION) !=null){
            position=getIntent().getIntExtra(POSITION,100000);
        }


        numeroOrden="#"+mRestaurante_pedido.getIdempresa()+""+
                mRestaurante_pedido.getIdpedido()+""+
                mRestaurante_pedido.getIdventa();

        System.out.println( mRestaurante_pedido.getUsuario_nombre() + " el objeto llego  y la possicion " + position +" ##");

    }


    public static Intent newIntentOrderDetail(Context context, Restaurante_Pedido restaurante_pedido,int position){
        Intent intent= new Intent(context,NewOrderDetailActivity.class);
        intent.putExtra(RESTAURANTE_PEDIDO,restaurante_pedido);
        intent.putExtra(POSITION,position);
        return intent;
    }


    @Override
    public void onDataPass(boolean agregar) {
        this.agregar=agregar;
        //RETURN DATA
        returnData();
    }



}
