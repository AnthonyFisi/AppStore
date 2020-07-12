package com.example.empresayego.View.OrderUI.NewOrder;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Restaurante_PedidoResultsAdapter  extends  RecyclerView.Adapter<Restaurante_PedidoResultsAdapter.Restaurante_PedidoResultsHolder>{

    private List<Restaurante_Pedido> results= new ArrayList<>();
    private ClickPedidoReciente mClickPedidoReciente;
    private boolean addItem=false;

    @NonNull
    @Override
    public Restaurante_PedidoResultsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_new_orders_item,parent,false);
        return  new Restaurante_PedidoResultsAdapter.Restaurante_PedidoResultsHolder(view,mClickPedidoReciente);
    }

    @Override
    public void onBindViewHolder(@NonNull Restaurante_PedidoResultsHolder holder, int position) {

        Restaurante_Pedido restaurante_pedido=results.get(position);

        if(restaurante_pedido.getIdestado_venta()==1){

            holder.linear_change_state.setBackgroundColor(Color.rgb(59,191,130));
        }

        holder.fragment_home_item_orden_CANTIDAD_PRODUCTOS.setText(String.valueOf(restaurante_pedido.getPedido_cantidadtotal()));
        holder.fragment_home_item_orden_CANTIDAD_PRODUCTOS.setTextColor(Color.WHITE);


        String pattern = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        String fecha=dateFormat.format(restaurante_pedido.getVenta_fecha());
        //String.valueOf(restaurante_pedido.getVenta_fecha())

        System.out.println(String.valueOf(restaurante_pedido.getVenta_fecha()));
        System.out.println(fecha);
        System.out.println("------------------");

        holder.fragment_home_item_orden_HORA_DE_LLEGADA.setText(fecha);
        holder.fragment_home_item_orden_HORA_DE_LLEGADA.setTextColor(Color.WHITE);


        holder.fragment_home_item_orden_NOMBRE_CLIENTE.setText(restaurante_pedido.getUsuario_nombre());
        holder.fragment_home_item_orden_NOMBRE_CLIENTE.setTextColor(Color.WHITE);

        holder.fragment_home_item_orden_NUMERO_ORDEN.setText("#"+restaurante_pedido.getIdempresa()+""+
                restaurante_pedido.getIdpedido()+""+
                restaurante_pedido.getIdventa());

        holder.fragment_home_item_orden_NUMERO_ORDEN.setTextColor(Color.WHITE);

        holder.fragment_home_item_orden_COSTO_TOTAL.setText(String.valueOf(restaurante_pedido.getVenta_costototal()));


    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Restaurante_Pedido> results, ClickPedidoReciente clickPedidoReciente){
        this.mClickPedidoReciente=clickPedidoReciente;
        this.results=results;
        System.out.println("cantidad" +results.size());
        notifyDataSetChanged();
    }

    public  void addResults(Restaurante_Pedido restaurante_pedido,boolean addItem){

        if(results.size()==0){
            List<Restaurante_Pedido> lista= new ArrayList<>();
            lista.add(restaurante_pedido);
            this.results=lista;
            System.out.println("lista " + restaurante_pedido.getIdventa() + " " +restaurante_pedido.getIdempresa());
            notifyItemInserted(0);
        }else{

              results.add(results.size(),restaurante_pedido);
            System.out.println("lista  2 " + restaurante_pedido.getIdventa() + " " +restaurante_pedido.getIdempresa());

        }
        notifyDataSetChanged();


        for(Restaurante_Pedido p:results){
            System.out.println("lista  add" + p.getIdventa() + " " +p.getIdempresa());

        }

    }

    public void removeItem(Restaurante_Pedido restaurante_pedido,int position){
        System.out.println(results.size() + " tamaño antes  de eliminar");
        results.remove(position);
       // results.remove(restaurante_pedido);
        System.out.println(results.size() + " tamaño despues de eliminar");

        notifyItemRemoved(position);
       notifyItemRangeChanged(position, results.size());
    }


    public int resultSize(){
        return results.size();
    }

    public class Restaurante_PedidoResultsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ClickPedidoReciente mClickPedidoReciente;
        private TextView fragment_home_item_orden_COSTO_TOTAL,fragment_home_item_orden_HORA_DE_LLEGADA,fragment_home_item_orden_NUMERO_ORDEN,fragment_home_item_orden_NOMBRE_CLIENTE,fragment_home_item_orden_CANTIDAD_PRODUCTOS;
        private LinearLayout linear_change_state;
        public Restaurante_PedidoResultsHolder(@NonNull View itemView,ClickPedidoReciente clickPedidoReciente) {
            super(itemView);
            this.mClickPedidoReciente=clickPedidoReciente;
            fragment_home_item_orden_HORA_DE_LLEGADA=itemView.findViewById(R.id.fragment_home_item_orden_HORA_DE_LLEGADA);
            fragment_home_item_orden_NUMERO_ORDEN=itemView.findViewById(R.id.fragment_home_item_orden_NUMERO_ORDEN);
            fragment_home_item_orden_NOMBRE_CLIENTE=itemView.findViewById(R.id.fragment_home_item_orden_NOMBRE_CLIENTE);
            fragment_home_item_orden_CANTIDAD_PRODUCTOS=itemView.findViewById(R.id.fragment_home_item_orden_CANTIDAD_PRODUCTOS);
            fragment_home_item_orden_COSTO_TOTAL=itemView.findViewById(R.id.fragment_home_item_orden_COSTO_TOTAL);
            linear_change_state=itemView.findViewById(R.id.linear_change_state);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            System.out.println(results.get(getAdapterPosition()).getUsuario_nombre() + "zona click" + " position"+ getAdapterPosition());
            mClickPedidoReciente.clickPedido(results.get(getAdapterPosition()),getAdapterPosition());
        }
    }

    public interface ClickPedidoReciente{

        void clickPedido(Restaurante_Pedido restaurante_pedido,int posisiton);
    }
}
