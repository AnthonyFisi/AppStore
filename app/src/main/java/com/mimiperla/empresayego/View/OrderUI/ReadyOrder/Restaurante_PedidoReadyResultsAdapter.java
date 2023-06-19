package com.mimiperla.empresayego.View.OrderUI.ReadyOrder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Restaurante_Pedido;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Restaurante_PedidoReadyResultsAdapter extends  RecyclerView.Adapter<Restaurante_PedidoReadyResultsAdapter.Restaurante_PedidoResultsHolder> implements Filterable {

    private List<Restaurante_Pedido> results= new ArrayList<>();
    private ClickPedidoReciente mClickPedidoReciente;
    private List<Restaurante_Pedido> resultsFiltered=new ArrayList<>();


    @NonNull
    @Override
    public Restaurante_PedidoResultsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_ready_order_item,parent,false);
        return  new Restaurante_PedidoReadyResultsAdapter.Restaurante_PedidoResultsHolder(view,mClickPedidoReciente);
    }

    @Override
    public void onBindViewHolder(@NonNull Restaurante_PedidoResultsHolder holder, int position) {

        Restaurante_Pedido restaurante_pedido=resultsFiltered.get(position);

        if(restaurante_pedido.getRepartidor_bi()!=null){
            holder.linear_change_state.setVisibility(View.VISIBLE);
            holder.fragment_ready_order_item_BUSCANDO_REPARTIDOR.setVisibility(View.GONE);

        holder. fragment_ready_order_item_NUMERO_ORDEN.setText("#"+restaurante_pedido.getIdventa());


        holder.fragment_ready_order_item_NOMBRE_DELIVERY.setText(restaurante_pedido.getRepartidor_bi().getNombre_usuario());


        holder.fragment_ready_order_item_CODIGO_IDENTIFACION.setText("ID "+restaurante_pedido.getRepartidor_bi().getIdrepartidor());

        String pattern = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        String fecha=dateFormat.format(restaurante_pedido.getVentafechaentrega());



        holder.fragment_ready_order_item_HORA_DE_LLEGADA.setText(fecha);


        if (restaurante_pedido.getRepartidor_bi().getFoto()!= null) {
            String imageUrl =restaurante_pedido.getRepartidor_bi().getFoto()
                    .replace("http://", "https://");

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.fragment_ready_order_item_IMAGEN_DELIVERY);
        }

        }else {
            holder.linear_change_state.setVisibility(View.GONE);

            holder.fragment_ready_order_item_BUSCANDO_REPARTIDOR.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return resultsFiltered.size();
    }

    public void setResults(List<Restaurante_Pedido> results, ClickPedidoReciente clickPedidoReciente){
        this.mClickPedidoReciente=clickPedidoReciente;
        this.results=results;
        this.resultsFiltered=results;
        notifyDataSetChanged();
   }

    public  void addResults(Restaurante_Pedido restaurante_pedido,boolean addItem){
        results.add(0,restaurante_pedido);
        notifyDataSetChanged();
    }

    public void removeItem(Restaurante_Pedido restaurante_pedido,int position){
        System.out.println(results.size() + " tamaño antes  de eliminar");
        results.remove(position);
        results.remove(restaurante_pedido);
        System.out.println(results.size() + " tamaño despues de eliminar");

        notifyItemRemoved(position);
       notifyItemRangeChanged(position, results.size());
    }

    public int resultSize(){
      return results.size();
    }

    public class Restaurante_PedidoResultsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ClickPedidoReciente mClickPedidoReciente;
        private TextView fragment_ready_order_item_NUMERO_ORDEN,fragment_ready_order_item_NOMBRE_DELIVERY,fragment_ready_order_item_CODIGO_IDENTIFACION,fragment_ready_order_item_HORA_DE_LLEGADA;
        private ImageView fragment_ready_order_item_IMAGEN_DELIVERY;

        private LinearLayout linear_change_state,fragment_ready_order_item_BUSCANDO_REPARTIDOR;

        public Restaurante_PedidoResultsHolder(@NonNull View itemView,ClickPedidoReciente clickPedidoReciente) {
            super(itemView);
            this.mClickPedidoReciente=clickPedidoReciente;
            fragment_ready_order_item_NUMERO_ORDEN=itemView.findViewById(R.id.fragment_ready_order_item_NUMERO_ORDEN);
            fragment_ready_order_item_IMAGEN_DELIVERY=itemView.findViewById(R.id.fragment_ready_order_item_IMAGEN_DELIVERY);
            fragment_ready_order_item_NOMBRE_DELIVERY=itemView.findViewById(R.id.fragment_ready_order_item_NOMBRE_DELIVERY);
            fragment_ready_order_item_CODIGO_IDENTIFACION=itemView.findViewById(R.id.fragment_ready_order_item_CODIGO_IDENTIFACION);
            fragment_ready_order_item_HORA_DE_LLEGADA=itemView.findViewById(R.id.fragment_ready_order_item_HORA_DE_LLEGADA);

            linear_change_state=itemView.findViewById(R.id.linear_change_state);
            fragment_ready_order_item_BUSCANDO_REPARTIDOR=itemView.findViewById(R.id.fragment_ready_order_item_BUSCANDO_REPARTIDOR);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mClickPedidoReciente.clickPedido(resultsFiltered.get(getAdapterPosition()),getAdapterPosition());
        }
    }

    public interface ClickPedidoReciente{
        void clickPedido(Restaurante_Pedido restaurante_pedido, int posisiton);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    resultsFiltered = results;
                } else {
                    List<Restaurante_Pedido> filteredList = new ArrayList<>();
                    for (Restaurante_Pedido row : results) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (String.valueOf(row.getIdventa()).toLowerCase().contains(charString.toLowerCase())) {
                            System.out.println("PASASMOS POR ACA ");
                            filteredList.add(row);
                        }
                    }

                    resultsFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = resultsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                resultsFiltered= (List<Restaurante_Pedido>) filterResults.values;
                System.out.println(resultsFiltered.size()+"CANTIDAD FINAL");
                for(Restaurante_Pedido p:resultsFiltered)
                {
                    System.out.println("EL ID VENT FINAL "+ p.getIdventa());
                }
                notifyDataSetChanged();
            }
        };
    }

}
