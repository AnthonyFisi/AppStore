package com.mimiperla.empresayego.View.DrawerUI.productos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Categoria_producto_empresa;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductosResultsAdapter4 extends RecyclerView.Adapter<ProductosResultsAdapter4.ProductosResultsHolder4> {
    private List<Categoria_producto_empresa> results=new ArrayList<>();
    private ClickCategoria mClickCategoria;


    @NonNull
    @Override
    public ProductosResultsHolder4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_categoria,parent,false);

        return new ProductosResultsAdapter4.ProductosResultsHolder4(view,mClickCategoria);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosResultsHolder4 holder, int position) {

        Categoria_producto_empresa categoria_producto_empresa=results.get(position);

        holder.nuevo_cate.setText(categoria_producto_empresa.getNombre());


    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Categoria_producto_empresa> results,ClickCategoria clickCategoria) {
        this.results = results;
        this.mClickCategoria=clickCategoria;
        notifyDataSetChanged();
    }

    class ProductosResultsHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nuevo_cate;
        ClickCategoria mClickCategoria;


        ProductosResultsHolder4(@NonNull View itemView,ClickCategoria clickCategoria) {
            super(itemView);
            nuevo_cate=itemView.findViewById(R.id.nuevo_cate);
            this.mClickCategoria=clickCategoria;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mClickCategoria.itemClickCategoria(results.get(getAdapterPosition()),results);
        }
    }

    public interface ClickCategoria{
         void itemClickCategoria(Categoria_producto_empresa categoria, List<Categoria_producto_empresa> listaCategoria);
    }
}
