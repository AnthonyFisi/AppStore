package com.mimiperla.empresayego.View.OrderUI.ProcessOrder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.ProductoJOINregistroPedidoJOINpedido;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductosProcesResultsAdapter extends RecyclerView.Adapter<ProductosProcesResultsAdapter.ProductosResultsHolder> {

    private List<ProductoJOINregistroPedidoJOINpedido> results = new ArrayList<>();


    @NonNull
    @Override
    public ProductosResultsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_proces_order_mesa,parent,false);
        return new ProductosProcesResultsAdapter.ProductosResultsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosResultsHolder holder, int position) {

        ProductoJOINregistroPedidoJOINpedido producto= results.get(position);

        /*if(producto.getComentario().trim().length()>0){
            holder.fragment_new_order_item_detail_COMENTARIO_PRODUCTO.setVisibility(View.VISIBLE);
            holder.fragment_new_order_item_detail_COMENTARIO_PRODUCTO.setText(producto.getComentario());

        }*/

        holder.fragment_new_order_item_detail_CANTIDAD_PRODUCTO.setText(String.valueOf(producto.getRegistropedido_cantidadtotal()));
        holder.fragment_new_order_item_detail_NOMBRE_PRODUCTO.setText(producto.getProducto_nombre());
        holder.fragment_new_order_item_detail_PRECIO_PRODUCTO.setText(String.valueOf(producto.getRegistropedido_preciototal()));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public  void setResults(List<ProductoJOINregistroPedidoJOINpedido> results){
        this.results=results;
        notifyDataSetChanged();
    }

    public  class  ProductosResultsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView fragment_new_order_item_detail_CANTIDAD_PRODUCTO,fragment_new_order_item_detail_NOMBRE_PRODUCTO,fragment_new_order_item_detail_PRECIO_PRODUCTO,fragment_new_order_item_detail_COMENTARIO_PRODUCTO;

        private LinearLayout  mLinearLayout;
        public ProductosResultsHolder(@NonNull View itemView) {
            super(itemView);
            fragment_new_order_item_detail_CANTIDAD_PRODUCTO=itemView.findViewById(R.id.fragment_new_order_item_detail_CANTIDAD_PRODUCTO);
            fragment_new_order_item_detail_NOMBRE_PRODUCTO=itemView.findViewById(R.id.fragment_new_order_item_detail_NOMBRE_PRODUCTO);
            fragment_new_order_item_detail_PRECIO_PRODUCTO=itemView.findViewById(R.id.fragment_new_order_item_detail_PRECIO_PRODUCTO);
            fragment_new_order_item_detail_COMENTARIO_PRODUCTO=itemView.findViewById(R.id.fragment_new_order_item_detail_COMENTARIO_PRODUCTO);
          //  mLinearLayout=itemView

        }

        @Override
        public void onClick(View view) {

        }
    }

}
