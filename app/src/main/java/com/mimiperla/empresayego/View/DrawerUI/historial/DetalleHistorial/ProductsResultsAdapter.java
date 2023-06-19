package com.mimiperla.empresayego.View.DrawerUI.historial.DetalleHistorial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.ProductoJOINregistroPedidoJOINpedido;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductsResultsAdapter extends RecyclerView.Adapter<ProductsResultsAdapter.ProductosResultsHolder> {

    private List<ProductoJOINregistroPedidoJOINpedido> results = new ArrayList<>();


    @NonNull
    @Override
    public ProductosResultsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_new_orders_item_detail,parent,false);
        return new ProductsResultsAdapter.ProductosResultsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosResultsHolder holder, int position) {

        ProductoJOINregistroPedidoJOINpedido producto= results.get(position);

        holder.fragment_new_order_item_detail_CANTIDAD_PRODUCTO.setText(String.valueOf(producto.getRegistropedido_cantidadtotal()));
        holder.fragment_new_order_item_detail_NOMBRE_PRODUCTO.setText(producto.getProducto_nombre());
        holder.fragment_new_order_item_detail_PRECIO_PRODUCTO.setText(String.valueOf(producto.getRegistropedido_preciototal()));
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
