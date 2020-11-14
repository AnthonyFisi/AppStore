package com.example.empresayego.View.OrderUI.NewOrder.Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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


        Toolbar toolbar2 = findViewById(R.id.toolbar2);

        setSupportActionBar(toolbar2);

        toolbar2.setNavigationOnClickListener(v->onBackPressed());

        Bundle bundle=new Bundle();
        bundle.putSerializable("EMPRESA",mRestaurante_pedido);
        NewOrderDetailFragment fragment=new NewOrderDetailFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_new_order_detail,fragment).commit();

    }




    private void returnData(){

        Intent returnIntent = new Intent();
        Bundle bundle= new Bundle();
        bundle.putBoolean("agregar",agregar);
        bundle.putInt("position",position);
        bundle.putSerializable("objeto",mRestaurante_pedido);
        returnIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK,returnIntent);
    }


    private void reciveDataIntent(){
        if(getIntent().getSerializableExtra(RESTAURANTE_PEDIDO) !=null){
            mRestaurante_pedido=(Restaurante_Pedido) getIntent().getSerializableExtra(RESTAURANTE_PEDIDO);
        }
        if(getIntent().getSerializableExtra(POSITION) !=null){
            position=getIntent().getIntExtra(POSITION,100000);
        }
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
        returnData();
    }



}
