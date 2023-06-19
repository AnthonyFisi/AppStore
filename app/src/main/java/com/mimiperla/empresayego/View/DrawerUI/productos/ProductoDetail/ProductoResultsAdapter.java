package com.mimiperla.empresayego.View.DrawerUI.productos.ProductoDetail;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Producto;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductoResultsAdapter extends  RecyclerView.Adapter<ProductoResultsAdapter.ProductoResultsHolder> implements Filterable {

    private List<Producto> results= new ArrayList<>();

    private ItemClickProducto mItemClickProducto;

    private List<Producto> resultsFiltered= new ArrayList<>();


    @NonNull
    @Override
    public ProductoResultsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto,parent,false);
        return new ProductoResultsAdapter.ProductoResultsHolder(view,mItemClickProducto);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoResultsHolder holder, int position) {

        Producto producto=resultsFiltered.get(position);

        holder.item_producto_NOMBRE.setText(producto.getProducto_nombre());

        String precio="S/ "+producto.getProducto_precio();

        holder.item_producto_PRECIO.setText(precio);

        if (producto.getProducto_uriimagen()!= null) {
            String imageUrl =producto.getProducto_uriimagen()
                    .replace("http://", "https://");

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.item_producto_IMAGEN);
        }

        if(producto.isDisponible()){
            holder.fragment_producto_LOCK.setColorFilter(Color.rgb(250,215,45));
        }else {
            holder.fragment_producto_LOCK.setColorFilter(Color.rgb(82,85,80));


        }

    }

    @Override
    public int getItemCount() {
        return resultsFiltered.size();
    }



    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setResults(List<Producto> results, ItemClickProducto itemClickProducto){
        System.out.println(results.size()+"CANTIDAD");
        this.results=results;
        this.mItemClickProducto=itemClickProducto;
        this.resultsFiltered=results;
        notifyDataSetChanged();
    }

     public void modifiedState(Producto producto){
        int position=0;
        int positionFound=0;
        for(Producto produ:resultsFiltered){
            if(produ.getIdproducto()==producto.getIdproducto()){
                produ.setDisponible(producto.isDisponible());
                positionFound=position;
            }
            position++;
        }

        notifyItemChanged(positionFound);

    }

    void addProduct(Producto producto){
        resultsFiltered.add(producto);
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
                    List<Producto> filteredList = new ArrayList<>();
                    for (Producto row : results) {

                        if (row.getProducto_nombre().toLowerCase().contains(charString.toLowerCase())) {
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
                resultsFiltered= (ArrayList<Producto>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

    public class ProductoResultsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemClickProducto mItemClickProducto;
        TextView item_producto_NOMBRE,item_producto_PRECIO;
        ImageView item_producto_IMAGEN;
        ImageButton fragment_producto_LOCK;

        ProductoResultsHolder(@NonNull View itemView, ItemClickProducto itemClickProducto) {
            super(itemView);
            mItemClickProducto=itemClickProducto;
            item_producto_NOMBRE=itemView.findViewById(R.id.item_producto_NOMBRE);
            item_producto_PRECIO=itemView.findViewById(R.id.item_producto_PRECIO);
            item_producto_IMAGEN=itemView.findViewById(R.id.item_producto_IMAGEN);
            fragment_producto_LOCK=itemView.findViewById(R.id.fragment_producto_LOCK);

            fragment_producto_LOCK.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if(fragment_producto_LOCK==view.findViewById(R.id.fragment_producto_LOCK)){
                mItemClickProducto.clickItem(resultsFiltered.get(getAdapterPosition()),!resultsFiltered.get(getAdapterPosition()).isDisponible());

                if(resultsFiltered.get(getAdapterPosition()).isDisponible()){
                    fragment_producto_LOCK.setColorFilter(Color.rgb(82,85,80));
                }else{
                   fragment_producto_LOCK.setColorFilter(Color.rgb(250,215,45));
                }
            }

        }
    }

    public interface ItemClickProducto{

        void clickItem(Producto producto,boolean newState);
    }
}
