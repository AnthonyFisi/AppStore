package com.example.empresayego.View.DrawerUI.productos;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Producto;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductoResultsAdapter extends  RecyclerView.Adapter<ProductoResultsAdapter.ProductoResultsHolder>{

    private List<Producto> results= new ArrayList<>();

    private ItemClickProducto mItemClickProducto;


    @NonNull
    @Override
    public ProductoResultsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto,parent,false);
        return new ProductoResultsAdapter.ProductoResultsHolder(view,mItemClickProducto);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoResultsHolder holder, int position) {

        Producto producto=results.get(position);

        holder.fragment_producto_NOMBRE.setText(producto.getProducto_nombre());

        if(producto.isDisponible()){
            holder.fragment_producto_LOCK.setBackgroundColor(Color.rgb(250,215,45));
        }

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Producto> results,ItemClickProducto itemClickProducto){
        this.results=results;
        this.mItemClickProducto=itemClickProducto;
        notifyDataSetChanged();
    }

    public class ProductoResultsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemClickProducto mItemClickProducto;
        TextView fragment_producto_NOMBRE;
        ImageButton fragment_producto_LOCK;

        public ProductoResultsHolder(@NonNull View itemView,ItemClickProducto itemClickProducto) {
            super(itemView);
            mItemClickProducto=itemClickProducto;
            fragment_producto_NOMBRE=itemView.findViewById(R.id.fragment_producto_NOMBRE);
            fragment_producto_LOCK=itemView.findViewById(R.id.fragment_producto_LOCK);

            fragment_producto_LOCK.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if(fragment_producto_LOCK==view.findViewById(R.id.fragment_producto_LOCK)){
                mItemClickProducto.clickItem(results.get(getAdapterPosition()));
                if(results.get(getAdapterPosition()).isDisponible()){
                    fragment_producto_LOCK.setBackgroundColor(Color.rgb(82,85,80));
                }else{
                   fragment_producto_LOCK.setBackgroundColor(Color.rgb(250,215,45));
                }
            }

        }
    }

    public interface ItemClickProducto{

        void clickItem(Producto producto);
    }
}
