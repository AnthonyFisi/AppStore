package com.example.empresayego.View.OrderUI.ProcessOrder.Detail;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Delivery_Pedido;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Modelo.Gson.GsonDelivery_Pedido;
import com.example.empresayego.Repository.Modelo.Orden_estado_restaurante;
import com.example.empresayego.Repository.Modelo.Orden_estado_restaurantePK;
import com.example.empresayego.Repository.Modelo.ProductoJOINregistroPedidoJOINpedido;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.example.empresayego.View.OrderUI.NewOrder.Detail.ProductosResultsAdapter;
import com.example.empresayego.ViewModel.Delivery_PedidoViewModel;
import com.example.empresayego.ViewModel.Orden_estado_restauranteViewModel;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.CALL_PHONE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static androidx.core.content.ContextCompat.checkSelfPermission;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProcesOrderDetailFragment extends Fragment {

    private Orden_estado_restauranteViewModel viewModel;

    private Orden_estado_restauranteViewModel viewModelMesa;

    private Delivery_PedidoViewModel viewModelDelivery;

    private OnDataPass dataPasser;

    //private Button fragment_proces_order_detail_PEDIDO_LISTO;
    private TextView fragment_proces_order_detail_NUMERO_ORDEN,fragment_proces_order_detail_HORA_LLEGADA,fragment_proces_order_detail_NOMBRE_CLIENTE,
            fragment_proces_order_detail_TIPO_ENVIO,fragment_proces_order_detail_ESTADO_PAGO,
            fragment_proces_order_detail_COSOTO_TOTAL,fragment_proces_order_detail_NUMERO_TELEFONO,fragment_new_order_detail_FECHA_ENTREGA,
            activity_carrito_CANTIDAD_DELIVERY,activity_carrito_CANTIDAD_SUBTOTAL,load_text;


    private RecyclerView fragment_proces_order_detail_RECYCLER_VIEW_PRODUCTOS;

    private Restaurante_Pedido mRestaurante_pedido;

    private List<ProductoJOINregistroPedidoJOINpedido> listaProducto ;

    private int cantidadTiempo;

    private ProductosResultsAdapter adapter;

    private ProgressBar progres_confirmar;

    private CardView fragment_new_order_detail_ACEPTAR_PEDIDO;

    private CardView cardView_mensaje,cardView_llamar;


    public ProcesOrderDetailFragment() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter= new ProductosResultsAdapter();
        cantidadTiempo = 5;
        listaProducto = new ArrayList<>();

        if(getArguments()!=null){
            Bundle bundle=getArguments();
            mRestaurante_pedido=(Restaurante_Pedido) bundle.getSerializable("EMPRESA");
            listaProducto.addAll(mRestaurante_pedido.getListaProductos());
        }

        viewModelDelivery= new ViewModelProvider(this).get(Delivery_PedidoViewModel.class);
        viewModelDelivery.init();



        viewModel = ViewModelProviders.of(this).get(Orden_estado_restauranteViewModel.class);
        viewModel.init();


        viewModelMesa = ViewModelProviders.of(this).get(Orden_estado_restauranteViewModel.class);
        viewModelMesa.init();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_proces_orders_detail, container, false);


        //DECLARAR WIDGETS
        declararWidgets(view);
        //CLICK BUTTON

        clickCallPhoneAndMessage();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setDataWidget();
        updateStateOrden();
    }

    private void declararWidgets(View view){




        fragment_proces_order_detail_NUMERO_ORDEN=view.findViewById(R.id.fragment_proces_order_detail_NUMERO_ORDEN);
        fragment_proces_order_detail_HORA_LLEGADA=view.findViewById(R.id.fragment_proces_order_detail_HORA_LLEGADA);
        fragment_proces_order_detail_NOMBRE_CLIENTE=view.findViewById(R.id.fragment_proces_order_detail_NOMBRE_CLIENTE);
        fragment_proces_order_detail_TIPO_ENVIO=view.findViewById(R.id.fragment_proces_order_detail_TIPO_ENVIO);
        fragment_proces_order_detail_ESTADO_PAGO=view.findViewById(R.id.fragment_proces_order_detail_ESTADO_PAGO);
        fragment_proces_order_detail_COSOTO_TOTAL=view.findViewById(R.id. fragment_proces_order_detail_COSOTO_TOTAL);
        //fragment_proces_order_detail_PEDIDO_LISTO=view.findViewById(R.id.fragment_proces_order_detail_PEDIDO_LISTO);
        fragment_proces_order_detail_RECYCLER_VIEW_PRODUCTOS=view.findViewById(R.id.fragment_proces_order_detail_RECYCLER_VIEW_PRODUCTOS);
        fragment_proces_order_detail_NUMERO_TELEFONO=view.findViewById(R.id.fragment_proces_order_detail_NUMERO_TELEFONO);
        fragment_new_order_detail_FECHA_ENTREGA=view.findViewById(R.id.fragment_new_order_detail_FECHA_ENTREGA);

        activity_carrito_CANTIDAD_DELIVERY=view.findViewById(R.id.activity_carrito_CANTIDAD_DELIVERY);
        activity_carrito_CANTIDAD_SUBTOTAL=view.findViewById(R.id.activity_carrito_CANTIDAD_SUBTOTAL);

        fragment_new_order_detail_ACEPTAR_PEDIDO=view.findViewById(R.id.fragment_proces_order_detail_PEDIDO_LISTO);

        progres_confirmar=view.findViewById(R.id.progres_confirmar);

        load_text=view.findViewById(R.id.load_text);

        cardView_mensaje=view.findViewById(R.id.cardView_mensaje);

        cardView_llamar=view.findViewById(R.id.cardView_llamar);


    }

    private void setDataWidget(){

        if(mRestaurante_pedido!=null) {

            String numeroOrden = "#" + mRestaurante_pedido.getIdventa();
            fragment_proces_order_detail_NUMERO_ORDEN.setText(numeroOrden);

            String pattern = "hh:mm:ss a";
            DateFormat dateFormat = new SimpleDateFormat(pattern);
            String fecha = dateFormat.format(mRestaurante_pedido.getVentafecha());

            fragment_proces_order_detail_HORA_LLEGADA.setText(fecha);

            fragment_proces_order_detail_NOMBRE_CLIENTE.setText(mRestaurante_pedido.getNombre());

            fragment_proces_order_detail_TIPO_ENVIO.setText(mRestaurante_pedido.getNombre_tipo_envio());

            fragment_proces_order_detail_ESTADO_PAGO.setText(mRestaurante_pedido.getNombre_estadopago());

            fragment_proces_order_detail_COSOTO_TOTAL.setText(String.valueOf(mRestaurante_pedido.getVenta_costototal()));


            adapter.setResults(listaProducto);
            fragment_proces_order_detail_RECYCLER_VIEW_PRODUCTOS.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            fragment_proces_order_detail_RECYCLER_VIEW_PRODUCTOS.setAdapter(adapter);

            fragment_proces_order_detail_NUMERO_TELEFONO.setText(mRestaurante_pedido.getCelular());

            Timestamp timeAceptado = Timestamp.valueOf(mRestaurante_pedido.getFechaAceptado());


            String day = (new SimpleDateFormat("EEEE")).format(timeAceptado.getTime()); // "Tuesday"

            String month = (new SimpleDateFormat("MMMM")).format(timeAceptado.getTime()); // "April"

            String year = (new SimpleDateFormat("yyyy")).format(timeAceptado.getTime()); // "April"

            int numberDay = timeAceptado.getDate();

            String fechaEntrega = day + " " + numberDay + " de " + month + "," + year + " " + mRestaurante_pedido.getHorario_nombre();

            fragment_new_order_detail_FECHA_ENTREGA.setText(fechaEntrega);

            activity_carrito_CANTIDAD_DELIVERY.setText(String.valueOf(mRestaurante_pedido.getVenta_costodelivery()));

            activity_carrito_CANTIDAD_SUBTOTAL.setText(String.valueOf(mRestaurante_pedido.getVenta_costototal() - mRestaurante_pedido.getVenta_costodelivery()));

        }


    }





    private void updateStateOrden(){



        fragment_new_order_detail_ACEPTAR_PEDIDO.setOnClickListener( v->{


            if(mRestaurante_pedido.isMesa()){

                progres_confirmar.setVisibility(View.VISIBLE);

                load_text.setVisibility(View.GONE);

                Orden_estado_restaurantePK pk =new Orden_estado_restaurantePK();
                pk.setIdventa(mRestaurante_pedido.getIdventa());
                pk.setIdestado_empresa(4);

                Orden_estado_restaurante ordenEstado=new Orden_estado_restaurante();
                ordenEstado.setId(pk);
                ordenEstado.setIdempresa(Empresa.sEmpresa.getIdempresa());
                ordenEstado.setFecha(null);
                ordenEstado.setDetalle("");
                viewModelMesa.updateEstadoReady(ordenEstado,mRestaurante_pedido.getIdusuario());
                responseDataPedidoMesa();

            }else{
                Orden_estado_restaurantePK pk=new Orden_estado_restaurantePK();
                pk.setIdventa(mRestaurante_pedido.getIdventa());
                pk.setIdestado_empresa(3);

                Orden_estado_restaurante estado= new Orden_estado_restaurante();
                estado.setId(pk);
                estado.setIdempresa(Empresa.sEmpresa.getIdempresa());
                estado.setDetalle("");
                estado.setFecha(null);

                //viewModel.updateEstadoProcesMarketPlace(estado,mRestaurante_pedido.getIdusuario());

                responseDataPedidoListo();


                FragmentManager fm = getChildFragmentManager();
                FragmentComentarioVentaDialog alertDialog = FragmentComentarioVentaDialog.newInstance(estado,mRestaurante_pedido.getIdusuario());
                alertDialog.show(fm, "fragment_alert");

            }




        });
    }

    private void responseDataPedidoListo(){

        viewModel.getOrden_estado_restauranteLiveData().observe(getViewLifecycleOwner(), new Observer<Orden_estado_restaurante>() {
            @Override
            public void onChanged(Orden_estado_restaurante orden_estado_restaurante) {
                progres_confirmar.setVisibility(View.GONE);
                load_text.setVisibility(View.VISIBLE);

                if(orden_estado_restaurante !=null){
                    passData(true,orden_estado_restaurante.getFecha());


                    getActivity().finish();

                }else{

                    passData(false,orden_estado_restaurante.getFecha());

                }
            }
        });

    }
    private void  responseDataPedidoMesa(){
        viewModelMesa.getOrden_estado_restauranteLiveData().observe(getViewLifecycleOwner(), new Observer<Orden_estado_restaurante>() {
            @Override
            public void onChanged(Orden_estado_restaurante orden_estado_restaurante) {

                progres_confirmar.setVisibility(View.GONE);
                load_text.setVisibility(View.VISIBLE);

                if(orden_estado_restaurante !=null){
                    passData(true,orden_estado_restaurante.getFecha());


                    getActivity().finish();

                }else{

                    passData(false,orden_estado_restaurante.getFecha());

                }

            }
        });
    }

    void updatePrice(float precioTotal){
        fragment_proces_order_detail_COSOTO_TOTAL.setText(String.valueOf(precioTotal));
    }


    private void passData(boolean agregar, Timestamp fechaServidor) {
        dataPasser.onDataPass(agregar,fechaServidor);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        dataPasser = (ProcesOrderDetailFragment.OnDataPass) context;
    }


    public interface OnDataPass {
        void onDataPass(boolean agregar,Timestamp fechaServidor);
    }


    private void clickCallPhoneAndMessage(){

        cardView_mensaje.setOnClickListener(v->{

            boolean installed= appInstalledOrNot("com.whatsapp");

            if(installed){
                Intent intent= new Intent(Intent.ACTION_VIEW);
                String message="Pedido  #"+mRestaurante_pedido.getIdventa();
                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+51"+mRestaurante_pedido.getCelular()+"&text="+message));
                startActivity(intent);
            }else {

                Toast.makeText(getContext(),"Whatsapp no esta instalado",Toast.LENGTH_SHORT).show();

            }
        });


        cardView_llamar.setOnClickListener(v->{
            Intent intent= new Intent((Intent.ACTION_CALL));
            intent.setData(Uri.parse("tel:"+mRestaurante_pedido.getCelular().trim()));

            if(validarPermiso()){

                startActivity(intent);

            }
        });
    }




    private boolean validarPermiso() {
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }

        if((checkSelfPermission(getContext(),CALL_PHONE)== PERMISSION_GRANTED)){
            return true;
        }

        if((shouldShowRequestPermissionRationale(CALL_PHONE)) ){
            cargarDialogoRecomendacion();
        }else{
            requestPermissions(new String[]{CALL_PHONE},100);
        }

        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==100){
            if(grantResults.length==1 && grantResults[0]== PERMISSION_GRANTED){

                Toast.makeText(getContext(),"Ahora si puedes llamar",Toast.LENGTH_LONG).show();

            }else{
                solicitarPermisosManual();
            }
        }

    }

    private void solicitarPermisosManual() {
        final CharSequence[] opciones={"si","no"};
        final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(getContext());
        alertOpciones.setTitle("¿Desea configurar los permisos de forma manual?");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("si")){
                    Intent intent=new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri=Uri.fromParts("package",getActivity().getPackageName(),null);
                    intent.setData(uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(),"Los permisos no fueron aceptados",Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            }
        });
        alertOpciones.show();
    }

    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialogo=new AlertDialog.Builder(getContext());
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{CALL_PHONE},100);
                }
            }
        });
        dialogo.show();
    }

    private boolean appInstalledOrNot(String url){
        PackageManager packageManager=getContext().getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
            app_installed=true;

        }catch (Exception e){
            app_installed=false;
        }

        return  app_installed;
    }

}

