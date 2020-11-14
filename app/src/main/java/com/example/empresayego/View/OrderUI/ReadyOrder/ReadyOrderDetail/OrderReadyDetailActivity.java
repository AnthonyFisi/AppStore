package com.example.empresayego.View.OrderUI.ReadyOrder.ReadyOrderDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.example.empresayego.View.OrderUI.ReadyOrder.ReadyOrderDetail.Products.ProductsFragment;
import com.example.empresayego.ViewModel.Orden_estado_restauranteViewModel;

import java.sql.Timestamp;

public class OrderReadyDetailActivity extends AppCompatActivity implements  ProductsFragment.OnDataPass{


    public final static String RESTAURANTE_PEDIDO="com.example.empresayego.View.OrderUI.NewOrder.Detail.RestauranteObjeto";

    public final static String POSITION="com.example.empresayego.View.OrderUI.NewOrder.Detail.posicion";

    private Restaurante_Pedido mRestaurante_pedido;

    private int position;

    private boolean agregar=false;

   // private Button CONFIRMAR_ENTREGA;
    private Orden_estado_restauranteViewModel viewModel;
    private boolean answerUpdateState=false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ready_detail);


        //RECIVE DATA
        reciveDataIntent();

      //  declararWidgets();
       // clickButton();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v->{
            onBackPressed();
        });


        //SEND DATA
        passDataFragemnt();

        //return data
       // returnData();
    }

    private void returnData(boolean agregar){

       // System.out.println( mRestaurante_pedido.getUsuario_nombre() + " los datos estan retornando" + position +" ##");

        Intent returnIntent = new Intent();
        Bundle bundle= new Bundle();
        bundle.putBoolean("agregar",agregar);
        bundle.putInt("position",position);
        bundle.putSerializable("objeto",mRestaurante_pedido);
        returnIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK,returnIntent);
    }


    private void passDataFragemnt(){

        Bundle bundle=new Bundle();
        bundle.putSerializable("EMPRESA",mRestaurante_pedido);
        ProductsFragment fragment=new ProductsFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_ready_order_detail_PRODUCTS,fragment).commit();



    }


    private void reciveDataIntent(){
        if(getIntent().getSerializableExtra(RESTAURANTE_PEDIDO) !=null){
            mRestaurante_pedido=(Restaurante_Pedido) getIntent().getSerializableExtra(RESTAURANTE_PEDIDO);
           // System.out.println( "DATOS" +mRestaurante_pedido.getUsuario_nombre());

        }else{
            System.out.println( "NO HAY DATOSSSSSSSSS EN RESTAURANTE");

        }
        if(getIntent().getSerializableExtra(POSITION) !=null){
            position=getIntent().getIntExtra(POSITION,100000);
        }else{
            System.out.println( "NO HAY DATOSSSSSSSSS EN POSITITON");

        }

    }


    public static Intent newIntentOrderReadyDetail(Context context, Restaurante_Pedido restaurante_pedido, int position){
        Intent intent= new Intent(context, OrderReadyDetailActivity.class);
        intent.putExtra(RESTAURANTE_PEDIDO,restaurante_pedido);
        intent.putExtra(POSITION,position);
        return intent;
    }

    @Override
    public void onDataPass(boolean agregar) {
        returnData(agregar);
    }

}

