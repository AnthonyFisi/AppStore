package com.example.empresayego.View.OrderUI.ProcessOrder.Detail.Ayuda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;

public class AyudaActivity extends AppCompatActivity implements  IncrementTiemFragment.OnDataPass,EliminarFragment.OnDataPassEliminar{

    public final static String RESTAURANTE_PEDIDO_AYUDA="com.example.empresayego.View.OrderUI.NewOrder.Detail.RestauranteObjeto";

    public final static String POSITION_AYUDA="com.example.empresayego.View.OrderUI.NewOrder.Detail.posicion";

    public final static String POSITION_COUNT="com.example.empresayego.View.OrderUI.NewOrder.Detail.posicionCount";

    private int positionCount;

    private Restaurante_Pedido mRestaurante_pedido;

    private int position;

    private boolean eliminar=false;


    private CardView activity_ayuda_DEMORAR_PEDIDO,activity_ayuda_AUMENTAR_PRECIO,activity_ayuda_CANCELAR_PEDIDO,activity_ayuda_CONTACTAR_EMPRESA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
        reciveDataIntent();
        declararWidget();


        clickCardViewAyuda();


    }



    private void clickCardViewAyuda(){

        activity_ayuda_DEMORAR_PEDIDO.setOnClickListener( v->{

            IncrementTiemFragment addPhotoBottomDialogFragment =
                    IncrementTiemFragment.newInstance(mRestaurante_pedido);
            addPhotoBottomDialogFragment.show(getSupportFragmentManager(),
                    IncrementTiemFragment.TAG);

        });

        activity_ayuda_AUMENTAR_PRECIO.setOnClickListener( v->{
            IncrementTiemFragment1 addPhotoBottomDialogFragment =
                    IncrementTiemFragment1.newInstance(mRestaurante_pedido);
            addPhotoBottomDialogFragment.show(getSupportFragmentManager(),
                    IncrementTiemFragment1.TAG);
        });

        activity_ayuda_CANCELAR_PEDIDO.setOnClickListener(v->{
            EliminarFragment addPhotoBottomDialogFragment =
                    EliminarFragment.newInstance(mRestaurante_pedido);
            addPhotoBottomDialogFragment.show(getSupportFragmentManager(),
                    IncrementTiemFragment1.TAG);
        });


    }


    private void reciveDataIntent(){
        if(getIntent().getSerializableExtra(RESTAURANTE_PEDIDO_AYUDA) !=null){
            mRestaurante_pedido=(Restaurante_Pedido) getIntent().getSerializableExtra(RESTAURANTE_PEDIDO_AYUDA);
        }
        if(getIntent().getSerializableExtra(POSITION_AYUDA) !=null){
            position=getIntent().getIntExtra(POSITION_AYUDA,100000);
        }

        if(getIntent().getSerializableExtra(POSITION_COUNT) !=null){
            positionCount=getIntent().getIntExtra(POSITION_COUNT,100000);
        }


        System.out.println( mRestaurante_pedido.getUsuario_nombre() + " el objeto llego  y la possicion " + position +" ##");

    }



    public static Intent newIntentOrderProcesDetail(Context context, Restaurante_Pedido restaurante_pedido, int position,int positionCount){
        Intent intent= new Intent(context, AyudaActivity.class);
        intent.putExtra(RESTAURANTE_PEDIDO_AYUDA,restaurante_pedido);
        intent.putExtra(POSITION_AYUDA,position);
        intent.putExtra(POSITION_COUNT,positionCount);
        return intent;
    }


    private void declararWidget(){
        activity_ayuda_DEMORAR_PEDIDO=findViewById(R.id.activity_ayuda_DEMORAR_PEDIDO);
        activity_ayuda_AUMENTAR_PRECIO=findViewById(R.id.activity_ayuda_AUMENTAR_PRECIO);
        activity_ayuda_CANCELAR_PEDIDO=findViewById(R.id.activity_ayuda_CANCELAR_PEDIDO);
        activity_ayuda_CONTACTAR_EMPRESA=findViewById(R.id.activity_ayuda_CONTACTAR_EMPRESA);
    }


    private void returnData(boolean updateTime,int tiempo,boolean eliminar){

        System.out.println( mRestaurante_pedido.getUsuario_nombre() + " los datos estan retornando del FRAGMENT INCREMENTTIME" + position +" ##");

        Intent returnIntent = new Intent();
        Bundle bundle= new Bundle();
        bundle.putBoolean("updateTime",updateTime);
        bundle.putInt("position",position);
        bundle.putInt("positionCount",positionCount);
        bundle.putSerializable("objeto",mRestaurante_pedido);
        bundle.putInt("cantidadTiempo",tiempo);
        bundle.putBoolean("eliminar",eliminar);

        returnIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK,returnIntent);
    }


    @Override
    public void onDataPass(boolean agregar,int tiempo) {
        returnData(agregar,tiempo,false);
    }

    @Override
    public void onDataPassEliminar(boolean agregar, int tiempo) {
        returnData(false,tiempo,agregar);
    }
}
