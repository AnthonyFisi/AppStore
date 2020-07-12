package com.example.empresayego.View.OrderUI.ProcessOrder.Detail;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.example.empresayego.View.OrderUI.ProcessOrder.Detail.Ayuda.AyudaActivity;
import com.example.empresayego.View.OrderUI.ProcessOrder.Detail.Ayuda.EliminarFragment;

public class ProcesOrderActivity extends AppCompatActivity implements ProcesOrderDetailFragment.OnDataPass  {

    public final static String RESTAURANTE_PEDIDO="com.example.empresayego.View.OrderUI.NewOrder.Detail.RestauranteObjeto";

    public final static String POSITION="com.example.empresayego.View.OrderUI.NewOrder.Detail.posicion";

    public final static String POSITION_COUNT="com.example.empresayego.View.OrderUI.NewOrder.Detail.posicionCount";


    private Restaurante_Pedido mRestaurante_pedido;

    private LinearLayout acticity_proces_order_HELP;

    private int position;

    private int positionCount;

    private boolean eliminar=false;

    private boolean agregar=false;

    private boolean updateTime=false;

    private int cantidadTiempo=0;



    public final static int CODE=546;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proces_order);

        //getSupportActionBar().hide();


        //RECIVE DATA
        reciveDataIntent();


        //SEND DATA
        passDataFragemnt();
     //  settingToolBar();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_order_proces, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {

            Intent intent= AyudaActivity.newIntentOrderProcesDetail(ProcesOrderActivity.this,mRestaurante_pedido,position,positionCount);
            startActivityForResult(intent,CODE);
        }

        return super.onOptionsItemSelected(item);
    }
/*
    private void settingToolBar(){

        acticity_proces_order_HELP=findViewById(R.id.acticity_proces_order_HELP);

        acticity_proces_order_HELP.setOnClickListener( v ->{

            Intent intent= AyudaActivity.newIntentOrderProcesDetail(ProcesOrderActivity.this,mRestaurante_pedido,position,positionCount);
            startActivityForResult(intent,CODE);
        });
    }
*/




    private void returnData(){

        System.out.println( mRestaurante_pedido.getUsuario_nombre() + " los datos estan retornando de PROCESOR ORDER ACTIVITY" + position +" ##"+cantidadTiempo);

        Intent returnIntent = new Intent();
        Bundle bundle= new Bundle();
        bundle.putBoolean("agregar",agregar);
        bundle.putInt("position",position);
        bundle.putSerializable("objeto",mRestaurante_pedido);
        bundle.putInt("positionCount",positionCount);
        bundle.putBoolean("updateTime",updateTime);
        bundle.putInt("cantidadTiempo",cantidadTiempo);
        bundle.putBoolean("eliminar",eliminar);
        returnIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK,returnIntent);
    }


    private void passDataFragemnt(){
        ProcesOrderDetailFragment fragment = (ProcesOrderDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_proces_order_detail);
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

        if(getIntent().getSerializableExtra(POSITION_COUNT) !=null){
            positionCount=getIntent().getIntExtra(POSITION_COUNT,100000);
        }

        System.out.println( mRestaurante_pedido.getUsuario_nombre() + " el objeto llego  y la possicion " + position +" ##");

    }


    public static Intent newIntentOrderProcesDetail(Context context, Restaurante_Pedido restaurante_pedido, int position,int positionCount){
        Intent intent= new Intent(context, ProcesOrderActivity.class);
        intent.putExtra(RESTAURANTE_PEDIDO,restaurante_pedido);
        intent.putExtra(POSITION,position);
        intent.putExtra(POSITION_COUNT,positionCount);
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
                positionCount=bundle.getInt("positionCount");
                mRestaurante_pedido=(Restaurante_Pedido) bundle.getSerializable("objeto");
                cantidadTiempo=bundle.getInt("cantidadTiempo");
                eliminar=bundle.getBoolean("eliminar");
                System.out.println( mRestaurante_pedido.getUsuario_nombre() + " los datos llegaron  a PROCESOR ACTIVIY" + position +" ##"+cantidadTiempo);

                returnData();


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                System.out.println( "LOS DATOS NO LLEGARON");

            }
        }
    }

    @Override
    public void onDataPass(boolean agregar) {
        this.agregar=agregar;
        //RETURN DATA
        returnData();
    }


}
/*
*
*
*   <LinearLayout
        android:elevation="5dp"
        android:id="@+id/activity_proces_order_TOOLBAR"
        android:orientation="horizontal"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:background="?attr/colorPrimary"
        android:minHeight="?attr/actionBarSize"
        app:layout_constraintBottom_toTopOf="@+id/fragment_proces_order_detail"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize">

            <LinearLayout
                android:id="@+id/linearLayout16"
                android:layout_width="60dp"
                android:layout_height="match_parent"
                android:layout_marginStart="8dp"
                android:layout_marginEnd="8dp"
                android:gravity="center"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toStartOf="@+id/linearLayout17"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent">

                <ImageButton
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:background="@android:color/transparent"
                    android:src="@drawable/ic_back"
                    tools:ignore="VectorDrawableCompat" />

            </LinearLayout>


            <LinearLayout
                android:id="@+id/linearLayout17"
                android:layout_width="200dp"
                android:layout_height="match_parent"
                android:layout_marginEnd="8dp"
                android:gravity="center"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toStartOf="@+id/acticity_proces_order_HELP"
                app:layout_constraintStart_toEndOf="@+id/linearLayout16"
                app:layout_constraintTop_toTopOf="parent">

                <TextView

                    android:layout_width="250dp"
                    android:layout_height="match_parent" />

            </LinearLayout>

            <LinearLayout
                android:id="@+id/acticity_proces_order_HELP"
                android:layout_width="60dp"
                android:layout_height="match_parent"
                android:layout_marginStart="5dp"
                android:gravity="center"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toEndOf="@+id/linearLayout17"
                app:layout_constraintTop_toTopOf="parent">

                <ImageButton
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:background="@android:color/transparent"
                    android:src="@drawable/ic_ayuda"
                    tools:ignore="VectorDrawableCompat" />

            </LinearLayout>

        </androidx.constraintlayout.widget.ConstraintLayout>






    </LinearLayout>
    *
    *
    *
    *
    *
    *
    *   <Toolbar
        android:id="@+id/activity_proces_order_TOOLBAR"
        android:layout_width="0dp"
        android:layout_height="wrap_content"

        android:minHeight="?attr/actionBarSize"
        app:layout_constraintBottom_toTopOf="@+id/fragment_proces_order_detail"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
*
* */