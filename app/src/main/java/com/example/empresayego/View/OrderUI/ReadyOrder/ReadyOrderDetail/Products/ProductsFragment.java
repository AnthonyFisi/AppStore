package com.example.empresayego.View.OrderUI.ReadyOrder.ReadyOrderDetail.Products;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.ProductoJOINregistroPedidoJOINpedido;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class ProductsFragment extends Fragment {

    private List<ProductoJOINregistroPedidoJOINpedido> listaProducto ;
    private RecyclerView fragment_products_RECYCLER_VIEW_PRODUCTOS;
    private TextView fragment_products_COSOTO_TOTAL;



    private TextView fragment_products_NUMERO_ORDEN,fragment_products_HORA_LLEGADA,fragment_products_NOMBRE_CLIENTE,
            fragment_products_TIPO_ENVIO,fragment_products_ESTADO_PAGO,fragment_products_NUMERO_TELEFONO;






    private ProductsResultsAdapter adapter;

    //private OnDataPass dataPasser;

    private Restaurante_Pedido mRestaurante_pedido;


    public ProductsFragment() {

    }


    public static ProductsFragment newInstance(String param1, String param2) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        adapter= new ProductsResultsAdapter();
        listaProducto= new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_products, container, false);
        declararWidgets(view);
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
        fragment_products_NUMERO_TELEFONO=view.findViewById(R.id.fragment_products_NUMERO_TELEFONO);








    }

    private void setDataWidget(){
        adapter.setResults(listaProducto);
        fragment_products_RECYCLER_VIEW_PRODUCTOS.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        fragment_products_RECYCLER_VIEW_PRODUCTOS.setAdapter(adapter);

        fragment_products_COSOTO_TOTAL.setText(String.valueOf(mRestaurante_pedido.getVenta_costototal()));



        String numeroOrden="#"+mRestaurante_pedido.getIdempresa()+""+
                mRestaurante_pedido.getIdpedido()+""+
                mRestaurante_pedido.getIdventa();
        fragment_products_NUMERO_ORDEN.setText(numeroOrden);

        String pattern = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        String fecha=dateFormat.format(mRestaurante_pedido.getVenta_fecha());
        fragment_products_HORA_LLEGADA.setText(fecha);

        fragment_products_NOMBRE_CLIENTE.setText(mRestaurante_pedido.getUsuario_nombre());

        fragment_products_TIPO_ENVIO.setText(mRestaurante_pedido.getNombre_tipo_envio());

        fragment_products_ESTADO_PAGO.setText(mRestaurante_pedido.getTipo_estado());

        fragment_products_COSOTO_TOTAL.setText(String.valueOf(mRestaurante_pedido.getVenta_costototal()));


        adapter.setResults(listaProducto);
        fragment_products_RECYCLER_VIEW_PRODUCTOS.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        fragment_products_RECYCLER_VIEW_PRODUCTOS.setAdapter(adapter);

        fragment_products_NUMERO_TELEFONO.setText(mRestaurante_pedido.getUsuario_celular());



    }


    public void setPassData(Restaurante_Pedido restaurante_pedido){
        mRestaurante_pedido=restaurante_pedido;
        listaProducto.addAll(restaurante_pedido.getListaProductos());
        setDataWidget();
    }

  /*  public void passData(boolean agregar) {
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

*/

}
