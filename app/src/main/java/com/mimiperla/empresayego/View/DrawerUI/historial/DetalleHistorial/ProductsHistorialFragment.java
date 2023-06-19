package com.mimiperla.empresayego.View.DrawerUI.historial.DetalleHistorial;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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
import com.mimiperla.empresayego.Repository.Modelo.Empresa_historial;
import com.mimiperla.empresayego.Repository.Modelo.Gson.GsonEmpresa_historialDetail;
import com.mimiperla.empresayego.Repository.Modelo.ProductoJOINregistroPedidoJOINpedido;
import com.mimiperla.empresayego.Repository.Modelo.Repartidor_Bi;
import com.mimiperla.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.mimiperla.empresayego.ViewModel.Empresa_historialDetailViewModel;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.Manifest.permission.CALL_PHONE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static androidx.core.content.ContextCompat.checkSelfPermission;


public class ProductsHistorialFragment extends Fragment {


    private static final String PEDIDO = "pedido";

    private ProductsResultsAdapter adapter;

    private Empresa_historialDetailViewModel viewModel;

    private Repartidor_Bi mRepartidor_bi;

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


    private Restaurante_Pedido mRestaurante_pedido;

    private ProgressBar progres_historial;

    private Empresa_historial mEmpresa_historial;

    private NestedScrollView information_pedido;

    private CardView cardView_mensaje,cardView_llamar,cardView_llamar_cliente,cardView_mensaje_cliente;


    public static ProductsHistorialFragment newInstance(Empresa_historial empresa_historial) {
        ProductsHistorialFragment fragment = new ProductsHistorialFragment();
        Bundle args = new Bundle();
        args.putSerializable(PEDIDO,empresa_historial);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(getArguments()!=null){
            Bundle bundle=getArguments();
            mEmpresa_historial=(Empresa_historial) bundle.getSerializable(PEDIDO);
        }

        listaProducto=new ArrayList<>();
        mRestaurante_pedido=new Restaurante_Pedido();

        adapter= new ProductsResultsAdapter();

        viewModel= new ViewModelProvider(this).get(Empresa_historialDetailViewModel.class);
        viewModel.init();
        viewModel.getEmpresa_historialDetailLiveData().observe(this, new Observer<GsonEmpresa_historialDetail>() {
            @Override
            public void onChanged(GsonEmpresa_historialDetail gsonEmpresa_historialDetail) {
                progres_historial.setVisibility(View.GONE);
                if(gsonEmpresa_historialDetail !=null){

                    information_pedido.setVisibility(View.VISIBLE);

                    mRestaurante_pedido.setRepartidor_bi(gsonEmpresa_historialDetail.getRepartidor_bi());
                    mRestaurante_pedido.setListaProductos(gsonEmpresa_historialDetail.getListaProductos());

                    setDataWidget();
                    llamarRepartidor();
                    rastrearUbicacion();

                }else {
                    Toast.makeText(getContext(),"nothing",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_products2, container, false);
        declararWidgets(view);
        information_pedido.setVisibility(View.GONE);
        if(mEmpresa_historial!=null){

            viewModel.searchEmpresaHistorialDetailById(mEmpresa_historial.getIdempresa(),mEmpresa_historial.getIdpedido(),mEmpresa_historial.getIdrepartidor());

        }

        return view;
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

        progres_historial=view.findViewById(R.id.progres_historial);

        linearlayout_BUSCANDO_REPARTIDOR=view.findViewById(R.id.linearlayout_BUSCANDO_REPARTIDOR);

        linearlayout_REPARTIDOR=view.findViewById(R.id.linearlayout_REPARTIDOR);

        information_pedido=view.findViewById(R.id.information_pedido);



        cardView_mensaje=view.findViewById(R.id.cardView_mensaje);
        cardView_llamar=view.findViewById(R.id.cardView_llamar);
        cardView_llamar_cliente=view.findViewById(R.id.cardView_llamar_cliente);
        cardView_mensaje_cliente=view.findViewById(R.id.cardView_mensaje_cliente);

    }

    private void setDataWidget(){


        if(mRestaurante_pedido!=null) {




            listaProducto.addAll(mRestaurante_pedido.getListaProductos());


            fragment_products_COSOTO_TOTAL.setText(String.valueOf(mEmpresa_historial.getVenta_costototal()));


            String numeroOrden = "#" +mEmpresa_historial.getIdventa();
            fragment_products_NUMERO_ORDEN.setText(numeroOrden);

            String pattern = "hh:mm:ss a";
            DateFormat dateFormat = new SimpleDateFormat(pattern);
            String fecha = dateFormat.format(mEmpresa_historial.getVentafecha());
            fragment_products_HORA_LLEGADA.setText(fecha);

            fragment_products_NOMBRE_CLIENTE.setText(mEmpresa_historial.getNombre());

            fragment_products_TIPO_ENVIO.setText(mEmpresa_historial.getNombre_tipo_envio());

            fragment_products_ESTADO_PAGO.setText(mEmpresa_historial.getNombre_estadopago());

            fragment_products_COSOTO_TOTAL.setText(String.valueOf(mEmpresa_historial.getVenta_costototal()));


            adapter.setResults(listaProducto);
            fragment_products_RECYCLER_VIEW_PRODUCTOS.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

            new Handler().postDelayed(() -> {

                fragment_products_RECYCLER_VIEW_PRODUCTOS.setAdapter(adapter);

            }, 1000);

            //fragment_products_NUMERO_TELEFONO.setText(mRestaurante_pedido.getCelular());

            Timestamp timeAceptado = mEmpresa_historial.getVentafechaentrega();


            String day = (new SimpleDateFormat("EEEE")).format(timeAceptado.getTime()); // "Tuesday"

            String month = (new SimpleDateFormat("MMMM")).format(timeAceptado.getTime()); // "April"

            String year = (new SimpleDateFormat("yyyy")).format(timeAceptado.getTime()); // "April"

            int numberDay = timeAceptado.getDate();

            String fechaEntrega = day + " " + numberDay + " de " + month + "," + year + " " + mEmpresa_historial.getHorario_nombre();

            fragment_new_order_detail_FECHA_ENTREGA.setText(fechaEntrega);

            activity_carrito_CANTIDAD_DELIVERY.setText(String.valueOf(mEmpresa_historial.getVenta_costodelivery()));

            activity_carrito_CANTIDAD_SUBTOTAL.setText(String.valueOf(mEmpresa_historial.getVenta_costototal() - mEmpresa_historial.getVenta_costodelivery()));




            if (mRestaurante_pedido.getRepartidor_bi()!= null && !mEmpresa_historial.isMesa()) {
                String imageUrl = mRestaurante_pedido.getRepartidor_bi().getFoto()
                        .replace("http://", "https://");

                Glide.with(this)
                        .load(imageUrl)
                        .into(fragment_person_delivery_IMAGEN_DELIVERY);

                fragment_person_delivery_NOMBRE_DELIVERY.setText(mRestaurante_pedido.getRepartidor_bi().getNombre_usuario());
                fragment_person_delivery_ID_DELIVERY.setText(String.valueOf(mRestaurante_pedido.getRepartidor_bi().getIdrepartidor()));
            }else {
                linearlayout_REPARTIDOR.setVisibility(View.GONE);
            }

        }

    }

    private void rastrearUbicacion(){
        fragment_person_delivery_RASTREAR_UBICACION.setOnClickListener(v->{
            Intent intent= RastrearRepartidorHistorialActivity.newIntentRastrearRepartidorHistorialActivity(getContext(),mEmpresa_historial,mRestaurante_pedido.getRepartidor_bi());
            startActivity(intent);
        });
    }

    private void llamarRepartidor(){
        fragment_person_delivery_LLAMAR.setOnClickListener(v->{
            Intent intent= new Intent((Intent.ACTION_CALL));
            intent.setData(Uri.parse("tel"+mRepartidor_bi.getCelular()));
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
