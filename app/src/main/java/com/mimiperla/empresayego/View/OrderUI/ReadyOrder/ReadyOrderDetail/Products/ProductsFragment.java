package com.mimiperla.empresayego.View.OrderUI.ReadyOrder.ReadyOrderDetail.Products;

import android.Manifest;
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
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Empresa;
import com.mimiperla.empresayego.Repository.Modelo.Orden_estado_restaurante;
import com.mimiperla.empresayego.Repository.Modelo.Orden_estado_restaurantePK;
import com.mimiperla.empresayego.Repository.Modelo.ProductoJOINregistroPedidoJOINpedido;
import com.mimiperla.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.mimiperla.empresayego.View.OrderUI.ReadyOrder.ReadyOrderDetail.SupervisarOrden.RastrearRepartidorActivity;
import com.mimiperla.empresayego.ViewModel.Orden_estado_restauranteViewModel;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.CALL_PHONE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static androidx.core.content.ContextCompat.checkSelfPermission;


public class ProductsFragment extends Fragment {

    private List<ProductoJOINregistroPedidoJOINpedido> listaProducto ;

    private RecyclerView fragment_products_RECYCLER_VIEW_PRODUCTOS;

    private TextView fragment_products_COSOTO_TOTAL;

    private ImageView fragment_person_delivery_IMAGEN_DELIVERY;

    private TextView fragment_person_delivery_NOMBRE_DELIVERY,fragment_person_delivery_ID_DELIVERY,fragment_person_delivery_TELEFONO;

    private ImageView fragment_person_delivery_LLAMAR;

    private LinearLayout fragment_person_delivery_BUTON_MENSAJE,linearlayout_BUSCANDO_REPARTIDOR,linearlayout_REPARTIDOR;

    private CardView fragment_person_delivery_RASTREAR_UBICACION,CONFIRMAR_ENTREGA;

    private TextView fragment_products_NUMERO_ORDEN,fragment_products_HORA_LLEGADA,fragment_products_NOMBRE_CLIENTE,
            fragment_products_TIPO_ENVIO,fragment_products_ESTADO_PAGO,fragment_new_order_detail_FECHA_ENTREGA,
            activity_carrito_CANTIDAD_DELIVERY,activity_carrito_CANTIDAD_SUBTOTAL,load_text;

    private Orden_estado_restauranteViewModel viewModel;

    private ProductsResultsAdapter adapter;

    private OnDataPass dataPasser;

    private Restaurante_Pedido mRestaurante_pedido;

    private ProgressBar progres_confirmar;


    private CardView cardView_mensaje,cardView_llamar,cardView_llamar_cliente,cardView_mensaje_cliente;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments()!=null){
            Bundle bundle=getArguments();
            mRestaurante_pedido=(Restaurante_Pedido) bundle.getSerializable("EMPRESA");
            System.out.println("SI HAY DATOS ");

        }else {
            System.out.println("NO AY DATPS");
        }

        adapter= new ProductsResultsAdapter();

        listaProducto= new ArrayList<>();

        viewModel = ViewModelProviders.of(this).get(Orden_estado_restauranteViewModel.class);
        viewModel.init();
        viewModel.getOrden_estado_restauranteLiveData().observe(this, new Observer<Orden_estado_restaurante>() {
            @Override
            public void onChanged(Orden_estado_restaurante orden_estado_restaurante) {


                progres_confirmar.setVisibility(View.GONE);
                if(orden_estado_restaurante !=null){

                    Toast.makeText(getContext(),"Exitoso pedido",Toast.LENGTH_SHORT).show();

                    passData(true);

                    getActivity().finish();

                }else {

                    CONFIRMAR_ENTREGA.setEnabled(true);

                    load_text.setVisibility(View.VISIBLE);

                    Toast.makeText(getContext(),"Tuvimos un error",Toast.LENGTH_SHORT).show();

                    passData(false);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_products, container, false);
        declararWidgets(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setDataWidget();

        clickButton();
        llamarRepartidor();
        rastrearUbicacion();

        clickCallPhoneAndMessage();
    }

    public void declararWidgets(View view){
        fragment_products_RECYCLER_VIEW_PRODUCTOS=view.findViewById(R.id.fragment_products_RECYCLER_VIEW_PRODUCTOS);
        fragment_products_COSOTO_TOTAL=view.findViewById(R.id.fragment_products_COSOTO_TOTAL);





        fragment_products_NUMERO_ORDEN=view.findViewById(R.id.fragment_products_NUMERO_ORDEN);
        fragment_products_HORA_LLEGADA=view.findViewById(R.id.fragment_products_HORA_LLEGADA);
        fragment_products_NOMBRE_CLIENTE=view.findViewById(R.id.fragment_products_NOMBRE_CLIENTE);
        fragment_products_TIPO_ENVIO=view.findViewById(R.id.fragment_products_TIPO_ENVIO);
        fragment_products_ESTADO_PAGO=view.findViewById(R.id.fragment_products_ESTADO_PAGO);
        fragment_products_COSOTO_TOTAL=view.findViewById(R.id. fragment_products_COSOTO_TOTAL);
        fragment_products_RECYCLER_VIEW_PRODUCTOS=view.findViewById(R.id.fragment_products_RECYCLER_VIEW_PRODUCTOS);
        //fragment_products_NUMERO_TELEFONO=view.findViewById(R.id.fragment_products_NUMERO_TELEFONO);




        fragment_person_delivery_IMAGEN_DELIVERY=view.findViewById(R.id.fragment_person_delivery_IMAGEN_DELIVERY);
        fragment_person_delivery_NOMBRE_DELIVERY=view.findViewById(R.id.fragment_person_delivery_NOMBRE_DELIVERY);
        fragment_person_delivery_ID_DELIVERY=view.findViewById(R.id.fragment_person_delivery_ID_DELIVERY);
        fragment_person_delivery_LLAMAR=view.findViewById(R.id.fragment_person_delivery_LLAMAR);
        fragment_person_delivery_RASTREAR_UBICACION=view.findViewById(R.id.fragment_person_delivery_RASTREAR_UBICACION);


        CONFIRMAR_ENTREGA=view.findViewById(R.id.CONFIRMAR_ENTREGA);


        fragment_new_order_detail_FECHA_ENTREGA=view.findViewById(R.id.fragment_new_order_detail_FECHA_ENTREGA);

        activity_carrito_CANTIDAD_DELIVERY=view.findViewById(R.id.activity_carrito_CANTIDAD_DELIVERY);

        activity_carrito_CANTIDAD_SUBTOTAL=view.findViewById(R.id.activity_carrito_CANTIDAD_SUBTOTAL);

        load_text=view.findViewById(R.id.load_text);

        progres_confirmar=view.findViewById(R.id.progres_confirmar);

        linearlayout_BUSCANDO_REPARTIDOR=view.findViewById(R.id.linearlayout_BUSCANDO_REPARTIDOR);

        linearlayout_REPARTIDOR=view.findViewById(R.id.linearlayout_REPARTIDOR);


        cardView_mensaje=view.findViewById(R.id.cardView_mensaje);
        cardView_llamar=view.findViewById(R.id.cardView_llamar);
        cardView_llamar_cliente=view.findViewById(R.id.cardView_llamar_cliente);
        cardView_mensaje_cliente=view.findViewById(R.id.cardView_mensaje_cliente);

    }

    private void setDataWidget(){


        if(mRestaurante_pedido!=null) {


            listaProducto.addAll(mRestaurante_pedido.getListaProductos());


            fragment_products_COSOTO_TOTAL.setText(String.valueOf(mRestaurante_pedido.getVenta_costototal()));


            String numeroOrden = "#" + mRestaurante_pedido.getIdempresa() + "" +
                    mRestaurante_pedido.getIdpedido() + "" +
                    mRestaurante_pedido.getIdventa();
            fragment_products_NUMERO_ORDEN.setText(numeroOrden);

            String pattern = "hh:mm:ss a";
            DateFormat dateFormat = new SimpleDateFormat(pattern);
            String fecha = dateFormat.format(mRestaurante_pedido.getVentafecha());
            fragment_products_HORA_LLEGADA.setText(fecha);

            fragment_products_NOMBRE_CLIENTE.setText(mRestaurante_pedido.getNombre());

            fragment_products_TIPO_ENVIO.setText(mRestaurante_pedido.getNombre_tipo_envio());

            fragment_products_ESTADO_PAGO.setText(mRestaurante_pedido.getNombre_estadopago());

            fragment_products_COSOTO_TOTAL.setText(String.valueOf(mRestaurante_pedido.getVenta_costototal()));


            adapter.setResults(listaProducto);
            fragment_products_RECYCLER_VIEW_PRODUCTOS.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

            new Handler().postDelayed(() -> {

                fragment_products_RECYCLER_VIEW_PRODUCTOS.setAdapter(adapter);

            }, 1000);

            //fragment_products_NUMERO_TELEFONO.setText(mRestaurante_pedido.getCelular());

            Timestamp timeAceptado = Timestamp.valueOf(mRestaurante_pedido.getFechaAceptado());


            String day = (new SimpleDateFormat("EEEE")).format(timeAceptado.getTime()); // "Tuesday"

            String month = (new SimpleDateFormat("MMMM")).format(timeAceptado.getTime()); // "April"

            String year = (new SimpleDateFormat("yyyy")).format(timeAceptado.getTime()); // "April"

            int numberDay = timeAceptado.getDate();

            String fechaEntrega = day + " " + numberDay + " de " + month + "," + year + " " + mRestaurante_pedido.getHorario_nombre();

            fragment_new_order_detail_FECHA_ENTREGA.setText(fechaEntrega);

            activity_carrito_CANTIDAD_DELIVERY.setText(String.valueOf(mRestaurante_pedido.getVenta_costodelivery()));

            activity_carrito_CANTIDAD_SUBTOTAL.setText(String.valueOf(mRestaurante_pedido.getVenta_costototal() - mRestaurante_pedido.getVenta_costodelivery()));




            if (mRestaurante_pedido.getRepartidor_bi()!= null) {
                String imageUrl = mRestaurante_pedido.getRepartidor_bi().getFoto()
                        .replace("http://", "https://");

                Glide.with(this)
                        .load(imageUrl)
                        .into(fragment_person_delivery_IMAGEN_DELIVERY);

                fragment_person_delivery_NOMBRE_DELIVERY.setText(mRestaurante_pedido.getRepartidor_bi().getNombre_usuario());
                fragment_person_delivery_ID_DELIVERY.setText(String.valueOf(mRestaurante_pedido.getRepartidor_bi().getIdrepartidor()));
            }else {
                linearlayout_REPARTIDOR.setVisibility(View.GONE);
                linearlayout_BUSCANDO_REPARTIDOR.setVisibility(View.VISIBLE);
            }

        }

    }

    private void rastrearUbicacion(){
        fragment_person_delivery_RASTREAR_UBICACION.setOnClickListener(v->{
            Intent intent= RastrearRepartidorActivity.newIntentRastrearRepartidorActivity(getContext(),mRestaurante_pedido);
            startActivity(intent);
        });
    }

    private void llamarRepartidor(){
        fragment_person_delivery_LLAMAR.setOnClickListener(v->{
            Intent intent= new Intent((Intent.ACTION_CALL));
            intent.setData(Uri.parse("tel"+mRestaurante_pedido.getRepartidor_bi().getCelular()));
            if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE )!= PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getContext(),"Habilitar el permiso",Toast.LENGTH_SHORT).show();
                requestPermission();
            }else{
                startActivity(intent);
            }
        });
    }


    private void requestPermission(){
        ActivityCompat.requestPermissions(getActivity(), new String []{Manifest.permission.CALL_PHONE},1);
    }

    private void clickButton(){
        CONFIRMAR_ENTREGA.setOnClickListener( v->{

            if(mRestaurante_pedido.getRepartidor_bi()!=null){
                progres_confirmar.setVisibility(View.VISIBLE);


                Orden_estado_restaurantePK pk=new Orden_estado_restaurantePK();
                pk.setIdventa(mRestaurante_pedido.getIdventa());
                pk.setIdestado_empresa(4);

                Orden_estado_restaurante estado= new Orden_estado_restaurante();
                estado.setId(pk);
                estado.setIdempresa(Empresa.sEmpresa.getIdempresa());
                estado.setDetalle("");
                estado.setFecha(null);

                viewModel.updateEstadoReady(estado,mRestaurante_pedido.getIdusuario());

                CONFIRMAR_ENTREGA.setEnabled(false);

                load_text.setVisibility(View.GONE);
            }else {
                Toast.makeText(getContext(),"No cuentas con repartidor",Toast.LENGTH_LONG).show();
            }



        });
    }




    public void passData(boolean agregar) {
        dataPasser.onDataPass(agregar);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        dataPasser = (ProductsFragment.OnDataPass) context;
    }


    public interface OnDataPass {
        void onDataPass(boolean agregar);
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


        cardView_mensaje_cliente.setOnClickListener(v->{

            boolean installed= appInstalledOrNot("com.whatsapp");

            if(installed){
                Intent intent= new Intent(Intent.ACTION_VIEW);
                String message="Pedido  #"+mRestaurante_pedido.getIdventa();
                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+51"+mRestaurante_pedido.getRepartidor_bi().getCelular().trim()+"&text="+message));
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


        cardView_llamar_cliente.setOnClickListener(v->{
            Intent intent= new Intent((Intent.ACTION_CALL));
            intent.setData(Uri.parse("tel:"+mRestaurante_pedido.getRepartidor_bi().getCelular().trim()));

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
