package com.example.empresayego.View.OrderUI.ProcessOrder.Detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.ProductoJOINregistroPedidoJOINpedido;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductosProcesResultsAdapter extends RecyclerView.Adapter<ProductosProcesResultsAdapter.ProductosResultsHolder> {

    private List<ProductoJOINregistroPedidoJOINpedido> results = new ArrayList<>();


    @NonNull
    @Override
    public ProductosResultsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_new_orders_item_detail,parent,false);
        return new ProductosProcesResultsAdapter.ProductosResultsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosResultsHolder holder, int position) {

        ProductoJOINregistroPedidoJOINpedido producto= results.get(position);

        holder.fragment_new_order_item_detail_CANTIDAD_PRODUCTO.setText(String.valueOf(producto.getPedido_cantidadtotal()));
        holder.fragment_new_order_item_detail_NOMBRE_PRODUCTO.setText(producto.getProducto_nombre());
        holder.fragment_new_order_item_detail_PRECIO_PRODUCTO.setText(String.valueOf(producto.getPedido_montototal()));
        holder.fragment_new_order_item_detail_COMENTARIO_PRODUCTO.setText(producto.getComentario());
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

        public ProductosResultsHolder(@NonNull View itemView) {
            super(itemView);
            fragment_new_order_item_detail_CANTIDAD_PRODUCTO=itemView.findViewById(R.id.fragment_new_order_item_detail_CANTIDAD_PRODUCTO);
            fragment_new_order_item_detail_NOMBRE_PRODUCTO=itemView.findViewById(R.id.fragment_new_order_item_detail_NOMBRE_PRODUCTO);
            fragment_new_order_item_detail_PRECIO_PRODUCTO=itemView.findViewById(R.id.fragment_new_order_item_detail_PRECIO_PRODUCTO);
            fragment_new_order_item_detail_COMENTARIO_PRODUCTO=itemView.findViewById(R.id.fragment_new_order_item_detail_COMENTARIO_PRODUCTO);

        }

        @Override
        public void onClick(View view) {

        }
    }

}
