package com.mimiperla.empresayego.View.DrawerUI.historial;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Empresa_historial;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Historial_PedidoResultsAdapter extends  RecyclerView.Adapter<Historial_PedidoResultsAdapter.Restaurante_PedidoResultsHolder> implements Filterable {

    private List<Empresa_historial> results= new ArrayList<>();
    private ClickPedidoReciente mClickPedidoReciente;
    private List<Empresa_historial> resultsFiltered= new ArrayList<>();

    @NonNull
    @Override
    public Restaurante_PedidoResultsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_historial_item,parent,false);
        return  new Historial_PedidoResultsAdapter.Restaurante_PedidoResultsHolder(view,mClickPedidoReciente);
    }

    @Override
    public void onBindViewHolder(@NonNull Restaurante_PedidoResultsHolder holder, int position) {

        Empresa_historial restaurante_pedido=resultsFiltered.get(position);


        if(position>=1){

            Empresa_historial restaurante_pedido1=resultsFiltered.get(position-1);

            String patternFecha = "yyyy-MM-dd";


            @SuppressLint("SimpleDateFormat") DateFormat dateFormat1 = new SimpleDateFormat(patternFecha);
            String fechaPosition1=dateFormat1.format(restaurante_pedido1.getFechahistorial());


            @SuppressLint("SimpleDateFormat") DateFormat dateFormat2 = new SimpleDateFormat(patternFecha);
            String fechaPosititon2=dateFormat2.format(restaurante_pedido.getFechahistorial());
            System.out.println(fechaPosition1+" / "+fechaPosititon2+" 1 ");

            if(!fechaPosition1.equals(fechaPosititon2)){

                System.out.println(fechaPosition1+" / "+fechaPosititon2+ " 2 ");

                //ESCRIBIR EL DIA DE HOY
                String day = (new SimpleDateFormat("EEEE")).format(restaurante_pedido.getFechahistorial().getTime()); // "Tuesday"
                holder.fecha_historial.setVisibility(View.VISIBLE);
                String dayUpper =day.substring(0, 1).toUpperCase() + day.substring(1);
                holder.dia_semana.setText(dayUpper);
            }


        }else {

            //restaurante_pedido.getFechahistorial().

            String patternFecha = "yyyy-MM-dd";

            @SuppressLint("SimpleDateFormat") DateFormat dateFormat2 = new SimpleDateFormat(patternFecha);
            String fechaPosititon2=dateFormat2.format(restaurante_pedido.getFechahistorial());
            String fechaNow= Calendar.getInstance().getTime().toString();

            System.out.println(fechaPosititon2+" 3 ");

            if(fechaNow.equals(fechaPosititon2)){
                //ESCRIBIR EL DIA DE HOY
                holder.fecha_historial.setVisibility(View.VISIBLE);
                holder.dia_semana.setText("Hoy");
            }else {
                String day = (new SimpleDateFormat("EEEE")).format(restaurante_pedido.getFechahistorial().getTime()); // "Tuesday"
                holder.fecha_historial.setVisibility(View.VISIBLE);
                holder.dia_semana.setText(day);
            }
        }





        String pattern = "hh:mm:ss a";
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat(pattern);
        String fecha=dateFormat.format(restaurante_pedido.getVentafecha());

        if(restaurante_pedido.isMesa()){
            String orden="Mesa #"+restaurante_pedido.getNumeromesa();
            holder.fragment_home_item_orden_NUMERO_ORDEN.setText(orden);

        }else {
            String orden="Orden #"+restaurante_pedido.getIdpedido();
            holder.fragment_home_item_orden_NUMERO_ORDEN.setText(orden);

        }

        holder.fragment_home_item_orden_CANTIDAD_PRODUCTOS.setText(String.valueOf(restaurante_pedido.getPedido_cantidadtotal()));


        holder.fragment_home_item_orden_HORA_DE_LLEGADA.setText(fecha);


        holder.fragment_home_item_orden_NOMBRE_CLIENTE.setText(restaurante_pedido.getNombre());


        holder.fragment_home_item_orden_COSTO_TOTAL.setText(String.valueOf(restaurante_pedido.getVenta_costototal()));


    }

    @Override
    public int getItemCount() {
        return resultsFiltered.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setResults(List<Empresa_historial> results, ClickPedidoReciente clickPedidoReciente){
        this.mClickPedidoReciente=clickPedidoReciente;
        this.results=results;
        this.resultsFiltered=results;
        System.out.println("cantidad" +results.size());
        notifyDataSetChanged();
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
                    List<Empresa_historial> filteredList = new ArrayList<>();
                    for (Empresa_historial row : results) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        String numeroOrden=row.getIdempresa()+""+
                                row.getIdpedido()+""+
                                row.getIdventa();
                        if (numeroOrden.toLowerCase().contains(charString.toLowerCase())) {
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
                resultsFiltered= (ArrayList<Empresa_historial>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class Restaurante_PedidoResultsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ClickPedidoReciente mClickPedidoReciente;
        private CardView item_historial;
        private TextView dia_semana;
        private LinearLayout fecha_historial;
        private TextView fragment_home_item_orden_COSTO_TOTAL,fragment_home_item_orden_HORA_DE_LLEGADA,fragment_home_item_orden_NUMERO_ORDEN,fragment_home_item_orden_NOMBRE_CLIENTE,fragment_home_item_orden_CANTIDAD_PRODUCTOS;
        Restaurante_PedidoResultsHolder(@NonNull View itemView, ClickPedidoReciente clickPedidoReciente) {
            super(itemView);
            this.mClickPedidoReciente=clickPedidoReciente;
            fragment_home_item_orden_HORA_DE_LLEGADA=itemView.findViewById(R.id.fragment_home_item_orden_HORA_DE_LLEGADA);
            fragment_home_item_orden_NUMERO_ORDEN=itemView.findViewById(R.id.fragment_home_item_orden_NUMERO_ORDEN);
            fragment_home_item_orden_NOMBRE_CLIENTE=itemView.findViewById(R.id.fragment_home_item_orden_NOMBRE_CLIENTE);
            fragment_home_item_orden_CANTIDAD_PRODUCTOS=itemView.findViewById(R.id.fragment_home_item_orden_CANTIDAD_PRODUCTOS);
            fragment_home_item_orden_COSTO_TOTAL=itemView.findViewById(R.id.fragment_home_item_orden_COSTO_TOTAL);
            dia_semana=itemView.findViewById(R.id.dia_semana);
            item_historial=itemView.findViewById(R.id.item_historial);
            //itemView.setOnClickListener(this);
            fecha_historial=itemView.findViewById(R.id.fecha_historial);
            item_historial.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            System.out.println(resultsFiltered.get(getAdapterPosition()).getNombre() + "zona click" + " position"+ getAdapterPosition());

            if(view.findViewById(R.id.item_historial)==item_historial){
                mClickPedidoReciente.clickPedido(resultsFiltered.get(getAdapterPosition()),getAdapterPosition());

            }

        }
    }

    public interface ClickPedidoReciente{

        void clickPedido(Empresa_historial restaurante_pedido, int posisiton);
    }
}
