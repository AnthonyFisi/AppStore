package com.mimiperla.empresayego.View.OrderUI.ProcessOrder.Detail.Ayuda.CancelarPedido;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Empresa;
import com.mimiperla.empresayego.Repository.Modelo.Orden_estado_restaurante;
import com.mimiperla.empresayego.Repository.Modelo.Orden_estado_restaurantePK;
import com.mimiperla.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.mimiperla.empresayego.Repository.Modelo.Venta;
import com.mimiperla.empresayego.View.ProcesoOrdenActivity;
import com.mimiperla.empresayego.ViewModel.Orden_estado_restauranteViewModel;

import org.jetbrains.annotations.NotNull;


public class EliminarFragment extends Fragment {


    private OnDataPassEliminar dataPasser;
    private  Restaurante_Pedido mRestaurante_pedido;
    private String comentario="";
   // private Button fragment_increment_UPDATE_PEDIDO;
    private EditText comentario_cancelar;
    private TextView text_aceptar_pedido;

    private Orden_estado_restauranteViewModel viewModel;
    private LinearLayout linearlayout_ACTUALIZAR_OK,linearLayout_ELIMINAR_PEDIDO;

    private ProgressBar progres_aceptar_pedido;

    private TextView numero_venta;

    private CardView cerrar_increment,cardview_aceptar_pedido;


    public EliminarFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle=getArguments();
            mRestaurante_pedido=(Restaurante_Pedido) bundle.getSerializable("EMPRESA");
        }

        viewModel = new ViewModelProvider(this).get(Orden_estado_restauranteViewModel.class);
        viewModel.init();
        viewModel.getOrden_estado_restauranteLiveData().observe(this, new Observer<Orden_estado_restaurante>() {
            @Override
            public void onChanged(Orden_estado_restaurante orden_estado_restaurante) {
               // progres.setVisibility(View.GONE);
                if(orden_estado_restaurante !=null){
                    linearLayout_ELIMINAR_PEDIDO.setVisibility(View.GONE);

                    linearlayout_ACTUALIZAR_OK.setVisibility(View.VISIBLE);

                    Toast.makeText(getContext(),"Pedido fue cancelado",Toast.LENGTH_LONG).show();

                    numero_venta.setText("#"+mRestaurante_pedido.getIdventa());

                    //fragment_increment_UPDATE_PEDIDO.setVisibility(View.INVISIBLE);

                    passData(true);



                }else{
                    text_aceptar_pedido.setVisibility(View.VISIBLE);

                    progres_aceptar_pedido.setVisibility(View.VISIBLE);

                    linearlayout_ACTUALIZAR_OK.setVisibility(View.VISIBLE);

                   // fragment_increment_UPDATE_PEDIDO.setVisibility(View.INVISIBLE);

                    Toast.makeText(getContext(),"Tuvimos fallos ,intentarlo otra vez",Toast.LENGTH_LONG).show();

                    passData(false);
                }

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_eliminar, container, false);
        declararWidgets(view);
       // setVisibility();
        updateTime();
        return view;
    }


    private void declararWidgets(View view){

       // fragment_increment_UPDATE_PEDIDO=view.findViewById(R.id.fragment_eliminar_UPDATE_PEDIDO);
        cardview_aceptar_pedido=view.findViewById(R.id.cardview_aceptar_pedido);
        /*fragment_increment_ACTUALIZAR_ERROR=view.findViewById(R.id.fragment_increment_ACTUALIZAR_ERROR);
        fragment_increment_ACTUALIZAR_OK=view.findViewById(R.id.fragment_increment_ACTUALIZAR_OK);
        fragment_increment_LOAD=view.findViewById(R.id.fragment_increment_LOAD);
        linearLayout2=view.findViewById(R.id.linearLayout2_eliminar);
        linearLayout15=view.findViewById(R.id.linearLayout15_eliminar);*/
        comentario_cancelar=view.findViewById(R.id.comentario_cancelar);
        linearlayout_ACTUALIZAR_OK=view.findViewById(R.id.linearlayout_ACTUALIZAR_OK);
        linearLayout_ELIMINAR_PEDIDO=view.findViewById(R.id.linearLayout_ELIMINAR_PEDIDO);
        progres_aceptar_pedido=view.findViewById(R.id.progres_aceptar_pedido);
        text_aceptar_pedido=view.findViewById(R.id.text_aceptar_pedido);

        numero_venta=view.findViewById(R.id.numero_venta);

        cerrar_increment=view.findViewById(R.id.cerrar_increment);


    }

  /*  private void setVisibility(){
        fragment_increment_ACTUALIZAR_ERROR.setVisibility(View.GONE);
        fragment_increment_ACTUALIZAR_OK.setVisibility(View.GONE);
        fragment_increment_LOAD.setVisibility(View.GONE);
    }*/

    private void updateTime(){

        cardview_aceptar_pedido.setOnClickListener( view -> {

            text_aceptar_pedido.setVisibility(View.GONE);

            progres_aceptar_pedido.setVisibility(View.VISIBLE);

            comentario=comentario_cancelar.getText().toString();

            System.out.println(comentario +" cometario de la eliminacion");


            Venta venta= new Venta();

            venta.setIdestado_venta(mRestaurante_pedido.getIdventa());
            venta.setComentario(comentario);

            //viewModel.updateEstadoVentaCostoCancelar(venta);

           // fragment_increment_LOAD.setVisibility(View.VISIBLE);

            cancelarPedido();

        });
    }


    private void cancelarPedido(){
        Orden_estado_restaurantePK pk=new Orden_estado_restaurantePK();
        pk.setIdventa(mRestaurante_pedido.getIdventa());
        pk.setIdestado_empresa(9);

        Orden_estado_restaurante estado= new Orden_estado_restaurante();
        estado.setId(pk);
        estado.setIdempresa(Empresa.sEmpresa.getIdempresa());
        estado.setDetalle(comentario);
        estado.setFecha(null);

        viewModel.updateEstadoCancelar(estado,mRestaurante_pedido.getIdusuario());

    }

    private void cerrar(){
        cerrar_increment.setOnClickListener(v->{
            Intent intent= ProcesoOrdenActivity.startIntentProcesoOrdenActivity(getContext(),true);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            progres_aceptar_pedido.setVisibility(View.GONE);

            startActivity(intent);

            requireActivity().finish();

        });
    }


    private void passData(boolean eliminar) {
        dataPasser.onDataPassEliminar(eliminar);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        dataPasser = (EliminarFragment.OnDataPassEliminar) context;
    }


    public interface OnDataPassEliminar {
        void onDataPassEliminar(boolean eliminar);
    }

    public void setPassData(Restaurante_Pedido restaurante_pedido){
        this.mRestaurante_pedido=restaurante_pedido;
    }



}
