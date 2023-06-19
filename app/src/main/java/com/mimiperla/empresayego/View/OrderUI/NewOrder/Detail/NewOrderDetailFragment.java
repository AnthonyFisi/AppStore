package com.mimiperla.empresayego.View.OrderUI.NewOrder.Detail;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Empresa;
import com.mimiperla.empresayego.Repository.Modelo.Orden_estado_restaurante;
import com.mimiperla.empresayego.Repository.Modelo.Orden_estado_restaurantePK;
import com.mimiperla.empresayego.Repository.Modelo.ProductoJOINregistroPedidoJOINpedido;
import com.mimiperla.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.mimiperla.empresayego.ViewModel.Orden_estado_restauranteViewModel;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class NewOrderDetailFragment extends Fragment {

    private Orden_estado_restauranteViewModel viewModel;

    private Orden_estado_restauranteViewModel viewModelCancelar;


    private OnDataPass dataPasser;

    private ProgressBar progres_confirmar,progres_cancelar;

    private ImageButton fragment_new_order_detail_DISMINUIR_TIEMPO,fragment_new_order_detail_INCREMENTAR_TIEMPO;
    private TextView fragment_new_order_detail_COMENTARIO_GLOBAL,fragment_new_order_detail_TIEMPO,fragment_new_order_detail_NUMERO_ORDEN,fragment_new_order_detail_HORA_LLEGADA,fragment_new_order_detail_NOMBRE_CLIENTE,
    fragment_new_order_detail_TIPO_ENVIO,fragment_new_order_detail_ESTADO_PAGO,
            fragment_new_order_detail_COSOTO_TOTAL,fragment_new_order_detail_FECHA_ENTREGA,
            activity_carrito_CANTIDAD_DELIVERY,activity_carrito_CANTIDAD_SUBTOTAL,load_text;
    ;

    private RecyclerView fragment_new_order_detail_RECYCLER_VIEW_PRODUCTOS;

    private Restaurante_Pedido mRestaurante_pedido;

    private List<ProductoJOINregistroPedidoJOINpedido> listaProducto ;

     private int cantidadTiempo;

     private ProductosResultsAdapter adapter;

     private CardView fragment_eliminar_CANCELAR_PEDIDO,fragment_new_order_detail_ACEPTAR_PEDIDO;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listaProducto=new ArrayList<>();
        if(getArguments()!=null){
            Bundle bundle=getArguments();
            mRestaurante_pedido=(Restaurante_Pedido) bundle.getSerializable("EMPRESA");
        }

        adapter= new ProductosResultsAdapter();

        viewModel = new ViewModelProvider(this).get(Orden_estado_restauranteViewModel.class);
        viewModel.init();

        viewModelCancelar=new ViewModelProvider(this).get(Orden_estado_restauranteViewModel.class);
        viewModelCancelar.init();

        cantidadTiempo=1;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       return  inflater.inflate(R.layout.fragment_new_order_detail, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //DECLARAR WIDGETS
        declararWidgets(view);
        //CLICK BUTTON

        setDataWidget();
        updateStateOrden();
        cancelarPedido();
        clickButton();
        cancelarPedido();
    }

    private void declararWidgets(View view){
        fragment_new_order_detail_DISMINUIR_TIEMPO=view.findViewById(R.id.fragment_new_order_detail_DISMINUIR_TIEMPO);
        fragment_new_order_detail_TIEMPO=view.findViewById(R.id.fragment_increment_TIEMPO);
        fragment_new_order_detail_INCREMENTAR_TIEMPO=view.findViewById(R.id.fragment_new_order_detail_INCREMENTAR_TIEMPO);
        fragment_new_order_detail_NUMERO_ORDEN=view.findViewById(R.id.fragment_new_order_detail_NUMERO_ORDEN);
        fragment_new_order_detail_HORA_LLEGADA=view.findViewById(R.id.fragment_new_order_detail_HORA_LLEGADA);
        fragment_new_order_detail_NOMBRE_CLIENTE=view.findViewById(R.id.fragment_new_order_detail_NOMBRE_CLIENTE);
        fragment_new_order_detail_TIPO_ENVIO=view.findViewById(R.id.fragment_new_order_detail_TIPO_ENVIO);
        fragment_new_order_detail_ESTADO_PAGO=view.findViewById(R.id.fragment_new_order_detail_ESTADO_PAGO);
        fragment_new_order_detail_COSOTO_TOTAL=view.findViewById(R.id. fragment_new_order_detail_COSOTO_TOTAL);
        fragment_new_order_detail_ACEPTAR_PEDIDO=view.findViewById(R.id.fragment_eliminar_UPDATE_PEDIDO);
        fragment_new_order_detail_RECYCLER_VIEW_PRODUCTOS=view.findViewById(R.id.fragment_new_order_detail_RECYCLER_VIEW_PRODUCTOS);
        fragment_new_order_detail_COMENTARIO_GLOBAL=view.findViewById(R.id.fragment_new_order_detail_COMENTARIO_GLOBAL);
        progres_confirmar=view.findViewById(R.id.progres_confirmar);
        progres_cancelar=view.findViewById(R.id.progres_cancelar);
        fragment_eliminar_CANCELAR_PEDIDO=view.findViewById(R.id.fragment_eliminar_CANCELAR_PEDIDO);
        fragment_new_order_detail_FECHA_ENTREGA=view.findViewById(R.id.fragment_new_order_detail_FECHA_ENTREGA);

        activity_carrito_CANTIDAD_DELIVERY=view.findViewById(R.id.activity_carrito_CANTIDAD_DELIVERY);
        activity_carrito_CANTIDAD_SUBTOTAL=view.findViewById(R.id.activity_carrito_CANTIDAD_SUBTOTAL);

        load_text=view.findViewById(R.id.load_text);


    }

    private void setDataWidget(){

        if(mRestaurante_pedido!=null) {

            listaProducto.addAll(mRestaurante_pedido.getListaProductos());


            fragment_new_order_detail_TIEMPO.setText(String.valueOf(cantidadTiempo));

            String numeroOrden = "#" + mRestaurante_pedido.getIdventa();
            fragment_new_order_detail_NUMERO_ORDEN.setText(numeroOrden);

            String pattern = "hh:mm:ss a";
            DateFormat dateFormat = new SimpleDateFormat(pattern);
            String fecha = dateFormat.format(mRestaurante_pedido.getVentafecha());
            fragment_new_order_detail_HORA_LLEGADA.setText(fecha);


            //mRestaurante_pedido.getve
            /*String day = (new SimpleDateFormat("EEEE")).format(mRestaurante_pedido.getVentafechaentrega().getTime()); // "Tuesday"

            String month = (new SimpleDateFormat("MMMM")).format(mRestaurante_pedido.getVentafechaentrega().getTime()); // "April"

            String year = (new SimpleDateFormat("yyyy")).format(mRestaurante_pedido.getVentafechaentrega().getTime()); // "April"

            int numberDay = mRestaurante_pedido.getVentafechaentrega().getDate();*/


            //String fecha_entrega = day + " " + numberDay + " de " + month + "," + year+" "+mRestaurante_pedido.getHorario_nombre();
            String fecha_entrega ="";

            fragment_new_order_detail_FECHA_ENTREGA.setText(fecha_entrega);


            fragment_new_order_detail_ESTADO_PAGO.setText(mRestaurante_pedido.getNombre_estadopago());

            fragment_new_order_detail_NOMBRE_CLIENTE.setText(mRestaurante_pedido.getNombre());

            fragment_new_order_detail_TIPO_ENVIO.setText(mRestaurante_pedido.getNombre_tipo_envio());

            //fragment_new_order_detail_ESTADO_PAGO.setText(mRestaurante_pedido.getTipoestado());

            String delivery="S/ "+mRestaurante_pedido.getVenta_costodelivery();

            activity_carrito_CANTIDAD_DELIVERY.setText(delivery);


            String subtotal="S/ "+(mRestaurante_pedido.getVenta_costototal() - mRestaurante_pedido.getVenta_costodelivery());

            activity_carrito_CANTIDAD_SUBTOTAL.setText(subtotal);

            String total="S/ "+mRestaurante_pedido.getVenta_costototal();

            fragment_new_order_detail_COSOTO_TOTAL.setText(total);


            adapter.setResults(listaProducto);
            fragment_new_order_detail_RECYCLER_VIEW_PRODUCTOS.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

            new Handler().postDelayed(() -> {

                fragment_new_order_detail_RECYCLER_VIEW_PRODUCTOS.setAdapter(adapter);

            }, 2000);

            fragment_new_order_detail_COMENTARIO_GLOBAL.setText(mRestaurante_pedido.getComentario());
        }

    }

    private void cancelarPedido(){

        fragment_eliminar_CANCELAR_PEDIDO.setOnClickListener(v->{
            Orden_estado_restaurantePK pk=new Orden_estado_restaurantePK();
            pk.setIdventa(mRestaurante_pedido.getIdventa());
            pk.setIdestado_empresa(9);

            Orden_estado_restaurante estado= new Orden_estado_restaurante();
            estado.setId(pk);
            estado.setIdempresa(Empresa.sEmpresa.getIdempresa());
            estado.setDetalle("");
            estado.setFecha(null);


            viewModelCancelar.updateEstadoCancelar(estado,mRestaurante_pedido.getIdusuario());

            lockIncrementAndDecrement(false);


            progres_cancelar.setVisibility(View.VISIBLE);
            responseCancelarPedido();

        });
    }


    private void clickButton(){

        fragment_new_order_detail_DISMINUIR_TIEMPO.setOnClickListener(v->{

            if(cantidadTiempo!=0){
                cantidadTiempo-=5;
            }
            fragment_new_order_detail_TIEMPO.setText(String.valueOf(cantidadTiempo));
        });

        fragment_new_order_detail_INCREMENTAR_TIEMPO.setOnClickListener(v->{
            cantidadTiempo+=5;
            fragment_new_order_detail_TIEMPO.setText(String.valueOf(cantidadTiempo));
        });




    }


    private void updateStateOrden(){



        fragment_new_order_detail_ACEPTAR_PEDIDO.setOnClickListener( v->{

            load_text.setVisibility(View.GONE);

            Orden_estado_restaurantePK pk=new Orden_estado_restaurantePK();
            pk.setIdventa(mRestaurante_pedido.getIdventa());
            pk.setIdestado_empresa(2);

            Orden_estado_restaurante estado= new Orden_estado_restaurante();
            estado.setId(pk);
            estado.setIdempresa(Empresa.sEmpresa.getIdempresa());
            estado.setDetalle("");
            estado.setFecha(null);

            viewModel.updateEstado(estado,String.valueOf(cantidadTiempo),mRestaurante_pedido.getIdusuario(),
                    mRestaurante_pedido.getHorario_nombre(),
                    mRestaurante_pedido.getVentafechaentrega().toString());

            lockIncrementAndDecrement(false);

            progres_confirmar.setVisibility(View.VISIBLE);
            responseConfirmarPedido();

        });
    }

    private void responseCancelarPedido(){
        viewModelCancelar.getOrden_estado_restauranteLiveData().observe(getViewLifecycleOwner(), new Observer<Orden_estado_restaurante>() {
            @Override
            public void onChanged(Orden_estado_restaurante orden_estado_restaurante) {
                progres_cancelar.setVisibility(View.GONE);
                load_text.setVisibility(View.VISIBLE);

                lockIncrementAndDecrement(true);
                if(orden_estado_restaurante !=null){

                    Toast.makeText(getContext(),"El pedido fue cancelado",Toast.LENGTH_SHORT).show();
                    passData(true);
                    requireActivity().finish();

                }else {
                    passData(false);
                    Toast.makeText(getContext(),"No se puedo cancelar,intentarlo nuevamente",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void responseConfirmarPedido(){
        viewModel.getOrden_estado_restauranteLiveData().observe(getViewLifecycleOwner(), new Observer<Orden_estado_restaurante>() {
            @Override
            public void onChanged(Orden_estado_restaurante orden_estado_restaurante) {
                progres_confirmar.setVisibility(View.GONE);
                load_text.setVisibility(View.VISIBLE);

                lockIncrementAndDecrement(true);

                if(orden_estado_restaurante !=null){

                    Toast.makeText(getContext(),"Pedido aceptado",Toast.LENGTH_SHORT).show();

                    passData(true);

                    requireActivity().finish();

                }else {

                    Toast.makeText(getContext(),"No se puedo confirmar,intentarlo nuevamente",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }



    private void passData(boolean agregar) {
        dataPasser.onDataPass(agregar);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        dataPasser = (NewOrderDetailFragment.OnDataPass) context;
    }


    public interface OnDataPass {
        void onDataPass(boolean agregar);
    }

    private void lockIncrementAndDecrement(boolean response){
        fragment_new_order_detail_DISMINUIR_TIEMPO.setEnabled(response);
        fragment_new_order_detail_INCREMENTAR_TIEMPO.setEnabled(response);
        fragment_new_order_detail_ACEPTAR_PEDIDO.setEnabled(response);
        fragment_eliminar_CANCELAR_PEDIDO.setEnabled(response);


    }

}
