package com.mimiperla.empresayego.View.OrderUI.NewOrder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Restaurante_Pedido;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Restaurante_PedidoResultsAdapter  extends  RecyclerView.Adapter<Restaurante_PedidoResultsAdapter.Restaurante_PedidoResultsHolder>{

    public List<Restaurante_Pedido> results= new ArrayList<>();
    private ClickPedidoReciente mClickPedidoReciente;

    @NonNull
    @Override
    public Restaurante_PedidoResultsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_new_orders_item,parent,false);
        return  new Restaurante_PedidoResultsAdapter.Restaurante_PedidoResultsHolder(view,mClickPedidoReciente);
    }

    @Override
    public void onBindViewHolder(@NonNull Restaurante_PedidoResultsHolder holder, int position) {

        Restaurante_Pedido restaurante_pedido=results.get(position);
        holder.fragment_home_item_orden_CANTIDAD_PRODUCTOS.setText(String.valueOf(restaurante_pedido.getPedido_cantidadtotal()));


        String pattern = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        String fecha=dateFormat.format(restaurante_pedido.getVentafecha());

        holder.fragment_home_item_orden_HORA_DE_LLEGADA.setText(fecha);

        holder.fragment_home_item_orden_NOMBRE_CLIENTE.setText(restaurante_pedido.getNombre());

        holder.fragment_home_item_orden_NUMERO_ORDEN.setText("#"+restaurante_pedido.getIdventa());

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

    public  void addResults(Restaurante_Pedido restaurante_pedido,ClickPedidoReciente clickPedidoReciente){

        this.mClickPedidoReciente=clickPedidoReciente;
        results.add(results.size(),restaurante_pedido);
        notifyDataSetChanged();

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
        public Restaurante_PedidoResultsHolder(@NonNull View itemView,ClickPedidoReciente clickPedidoReciente) {
            super(itemView);
            this.mClickPedidoReciente=clickPedidoReciente;
            fragment_home_item_orden_HORA_DE_LLEGADA=itemView.findViewById(R.id.fragment_home_item_orden_HORA_DE_LLEGADA);
            fragment_home_item_orden_NUMERO_ORDEN=itemView.findViewById(R.id.fragment_home_item_orden_NUMERO_ORDEN);
            fragment_home_item_orden_NOMBRE_CLIENTE=itemView.findViewById(R.id.fragment_home_item_orden_NOMBRE_CLIENTE);
            fragment_home_item_orden_CANTIDAD_PRODUCTOS=itemView.findViewById(R.id.fragment_home_item_orden_CANTIDAD_PRODUCTOS);
            fragment_home_item_orden_COSTO_TOTAL=itemView.findViewById(R.id.fragment_home_item_orden_COSTO_TOTAL);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mClickPedidoReciente.clickPedido(results.get(getAdapterPosition()),getAdapterPosition());
        }
    }

    public interface ClickPedidoReciente{

        void clickPedido(Restaurante_Pedido restaurante_pedido,int posisiton);
    }
}
