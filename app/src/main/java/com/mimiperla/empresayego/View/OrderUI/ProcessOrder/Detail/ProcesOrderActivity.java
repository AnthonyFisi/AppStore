package com.mimiperla.empresayego.View.OrderUI.ProcessOrder.Detail;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.mimiperla.empresayego.View.OrderUI.ProcessOrder.Detail.Ayuda.AyudaActivity;

import java.sql.Timestamp;

public class ProcesOrderActivity extends AppCompatActivity implements ProcesOrderDetailFragment.OnDataPass,FragmentComentarioVentaDialog.FormDialogListener1  {

    public final static String RESTAURANTE_PEDIDO="com.example.empresayego.View.OrderUI.NewOrder.Detail.RestauranteObjeto";

    public final static String POSITION="com.example.empresayego.View.OrderUI.NewOrder.Detail.posicion";


    private Restaurante_Pedido mRestaurante_pedido;

    private Timestamp fechaServidor;

    private int position;

    private boolean eliminar=false;

    private boolean agregar=false;

    private boolean updateTime=false,price;

    private int cantidadTiempo=0;

    public final static int CODE=546;
    private float priceTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proces_order);

        initVariable();
        //getSupportActionBar().hide();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v->{
            onBackPressed();
        });

        //RECIVE DATA
        reciveDataIntent();


        //SEND DATA
        passDataFragemnt();
     //  settingToolBar();


    }

    private void initVariable(){

        eliminar=false;
        agregar=false;
       updateTime=false;
       price=false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_order_proces, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_add && tiempoRestante()) {

           Intent intent= AyudaActivity.newIntentOrderProcesDetail(ProcesOrderActivity.this,mRestaurante_pedido,position);
            startActivityForResult(intent,CODE);

        }else {

            Toast.makeText(this, "Ya no cuentas con tiempo", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }




    private void returnData(){


        Intent returnIntent = new Intent();
        Bundle bundle= new Bundle();
        bundle.putBoolean("agregar",agregar);
        bundle.putString("fechaServidor",fechaServidor.toString());
        bundle.putInt("position",position);
        bundle.putSerializable("objeto",mRestaurante_pedido);
       // bundle.putInt("positionCount",positionCount);
        bundle.putBoolean("updateTime",updateTime);
        bundle.putInt("cantidadTiempo",cantidadTiempo);
        bundle.putBoolean("eliminar",eliminar);
        bundle.putFloat("priceTotal",priceTotal);
        bundle.putBoolean("price",price);
        returnIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK,returnIntent);
    }


    private void passDataFragemnt(){
  /*      ProcesOrderDetailFragment fragment = (ProcesOrderDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_proces_order_detail);
        if(fragment !=null){
            fragment.setPassData(mRestaurante_pedido);
        }
*/

        Bundle bundle=new Bundle();
        bundle.putSerializable("EMPRESA",mRestaurante_pedido);
        ProcesOrderDetailFragment fragment=new ProcesOrderDetailFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_proces_order_detail,fragment).commit();
    }
    private void reciveDataIntent(){
        if(getIntent().getSerializableExtra(RESTAURANTE_PEDIDO) !=null){
            mRestaurante_pedido=(Restaurante_Pedido) getIntent().getSerializableExtra(RESTAURANTE_PEDIDO);
        }
        if(getIntent().getSerializableExtra(POSITION) !=null){
            position=getIntent().getIntExtra(POSITION,100000);
        }

    }


    public static Intent newIntentOrderProcesDetail(Context context, Restaurante_Pedido restaurante_pedido, int position){
        Intent intent= new Intent(context, ProcesOrderActivity.class);
        intent.putExtra(RESTAURANTE_PEDIDO,restaurante_pedido);
        intent.putExtra(POSITION,position);
      //  intent.putExtra(POSITION_COUNT,positionCount);
        return intent;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODE) {
            if(resultCode == Activity.RESULT_OK){

                // ProductoService producto=(ProductoService) data.getSerializableExtra("data");
                //agregar= data.getBooleanExtra("agregar",false);
                Bundle bundle= data.getExtras();
                updateTime=bundle.getBoolean("updateTime");
                position=bundle.getInt("position");
                //positionCount=bundle.getInt("positionCount");
                mRestaurante_pedido=(Restaurante_Pedido) bundle.getSerializable("objeto");
                cantidadTiempo=bundle.getInt("cantidadTiempo");
                eliminar=bundle.getBoolean("eliminar");
              //  System.out.println( mRestaurante_pedido.getUsuario_nombre() + " los datos llegaron  a PROCESOR ACTIVIY" + position +" ##"+cantidadTiempo);

                priceTotal=bundle.getFloat("priceTotal");
                price=bundle.getBoolean("price");

                returnData();


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                System.out.println( "LOS DATOS NO LLEGARON");

            }
        }
    }

    @Override
    public void onDataPass(boolean agregar, Timestamp fechaServidor) {
        this.agregar=agregar;
        this.fechaServidor=fechaServidor;
        //RETURN DATA
        returnData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(price){
            ProcesOrderDetailFragment fragment = (ProcesOrderDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_proces_order_detail);
            if(fragment !=null){
                fragment.updatePrice(priceTotal);
            }
        }
    }

    private boolean tiempoRestante(){

        Timestamp timeStart = Timestamp.valueOf(mRestaurante_pedido.getFechaAceptado());

        Timestamp timeNow=new Timestamp(System.currentTimeMillis());

        long difference = timeNow.getTime() - timeStart.getTime();

        long tiempoTotal = (long) (Integer.valueOf(mRestaurante_pedido.getTiempototal_espera()));

        long tiempoRestante = tiempoTotal - difference;

        return tiempoRestante > 0;
    }

    @Override
    public void update(boolean respuesta,Timestamp fecha) {
        this.agregar=respuesta;
        this.fechaServidor=fecha;
        //RETURN DATA
        returnData();

        new Handler().postDelayed(this::finish, 2000);
    }
}
