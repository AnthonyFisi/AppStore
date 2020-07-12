package com.example.empresayego.View.OrderUI.ReadyOrder.ReadyOrderDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Orden_estado_restaurante;
import com.example.empresayego.Repository.Modelo.Orden_estado_restaurantePK;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.example.empresayego.View.OrderUI.ProcessOrder.Detail.ProcesOrderDetailFragment;
import com.example.empresayego.View.OrderUI.ReadyOrder.ReadyOrderDetail.MapLocation.MapLocationFragment;
import com.example.empresayego.View.OrderUI.ReadyOrder.ReadyOrderDetail.PersonDelivery.PersonDeliveryFragment;
import com.example.empresayego.View.OrderUI.ReadyOrder.ReadyOrderDetail.Products.ProductsFragment;
import com.example.empresayego.ViewModel.Orden_estado_restauranteViewModel;

import java.sql.Timestamp;

public class OrderReadyDetailActivity extends AppCompatActivity {


    public final static String RESTAURANTE_PEDIDO="com.example.empresayego.View.OrderUI.NewOrder.Detail.RestauranteObjeto";

    public final static String POSITION="com.example.empresayego.View.OrderUI.NewOrder.Detail.posicion";

    private Restaurante_Pedido mRestaurante_pedido;

    private int position;

    private boolean agregar=false;

    private Button CONFIRMAR_ENTREGA;
    private Orden_estado_restauranteViewModel viewModel;
    private boolean answerUpdateState=false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ready_detail);


        //RECIVE DATA
        reciveDataIntent();

        declararWidgets();
        clickButton();

        //SEND DATA
        passDataFragemnt();

        //return data
       // returnData();
    }

    private void declararWidgets(){
        CONFIRMAR_ENTREGA=findViewById(R.id.CONFIRMAR_ENTREGA);


    }

    private void clickButton(){
        CONFIRMAR_ENTREGA.setOnClickListener( v->{


            viewModel = ViewModelProviders.of(this).get(Orden_estado_restauranteViewModel.class);
            viewModel.init();
            viewModel.getOrden_estado_restauranteLiveData().observe(this, new Observer<Orden_estado_restaurante>() {
                @Override
                public void onChanged(Orden_estado_restaurante orden_estado_restaurante) {

                    if(orden_estado_restaurante !=null){
                        answerUpdateState=true;
                    }
                }
            });



            Timestamp time=new Timestamp(System.currentTimeMillis());

            Orden_estado_restaurantePK pk=new Orden_estado_restaurantePK();
            pk.setIdventa(mRestaurante_pedido.getIdventa());
            pk.setIdestado_venta(4);

            Orden_estado_restaurante estado= new Orden_estado_restaurante();
            estado.setId(pk);
            estado.setDetalle("");
            estado.setFecha(null);

            viewModel.updateEstadoReady(estado,mRestaurante_pedido.getIdusuario());
            Handler handler = new Handler();

            handler.postDelayed(() -> {

                if(answerUpdateState){
                    agregar=true;
                    returnData(agregar);
                    finish();
                    Toast.makeText(this,"PEDIDO EN EL HISTORIAL",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(this,"NO FUE ACTUALIZADO",Toast.LENGTH_SHORT).show();
                }
            }, 3000);
        });
    }


    private void returnData(boolean agregar){

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
     /*   MapLocationFragment fragment = (MapLocationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_ready_order_detail_MAP_LOCATION);
        if(fragment !=null){
            fragment.setPassData(mRestaurante_pedido);
        }*/
        PersonDeliveryFragment fragment1 = (PersonDeliveryFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_ready_order_detail_PERSON_DELIVERY);
        if(fragment1 !=null){
            fragment1.setPassData(mRestaurante_pedido);
        }

        ProductsFragment fragment2 = (ProductsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_ready_order_detail_PRODUCTS);
        if(fragment2 !=null){

            fragment2.setPassData(mRestaurante_pedido);


        }



    }


    private void reciveDataIntent(){
        if(getIntent().getSerializableExtra(RESTAURANTE_PEDIDO) !=null){
            mRestaurante_pedido=(Restaurante_Pedido) getIntent().getSerializableExtra(RESTAURANTE_PEDIDO);
            System.out.println( "DATOS" +mRestaurante_pedido.getUsuario_nombre());

        }else{
            System.out.println( "NO HAY DATOSSSSSSSSS EN RESTAURANTE");

        }
        if(getIntent().getSerializableExtra(POSITION) !=null){
            position=getIntent().getIntExtra(POSITION,100000);
        }else{
            System.out.println( "NO HAY DATOSSSSSSSSS EN POSITITON");

        }


        System.out.println( mRestaurante_pedido.getUsuario_nombre() + " el objeto llego  y la possicion " + position +" ##");

    }


    public static Intent newIntentOrderReadyDetail(Context context, Restaurante_Pedido restaurante_pedido, int position){
        Intent intent= new Intent(context, OrderReadyDetailActivity.class);
        intent.putExtra(RESTAURANTE_PEDIDO,restaurante_pedido);
        intent.putExtra(POSITION,position);
        return intent;
    }


    /*@Override
    public void onDataPass(boolean agregar) {

    }*/
}


/*
*
*
*  <fragment
            android:id="@+id/fragment_ready_order_detail_MAP_LOCATION"
            android:name="com.example.empresayego.View.OrderUI.ReadyOrder.ReadyOrderDetail.MapLocation.MapLocationFragment"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            app:layout_constraintBottom_toTopOf="@+id/fragment_ready_order_detail_PRODUCTS"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/fragment_ready_order_detail_PERSON_DELIVERY"
            tools:layout="@layout/fragment_map_location" />
*
* */
