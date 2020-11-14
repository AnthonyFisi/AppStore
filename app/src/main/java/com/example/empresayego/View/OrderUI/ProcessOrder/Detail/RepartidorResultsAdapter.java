package com.example.empresayego.View.OrderUI.ProcessOrder.Detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Repartidor;
import com.example.empresayego.Repository.Modelo.Repartidor_Bi;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RepartidorResultsAdapter extends  RecyclerView.Adapter<RepartidorResultsAdapter.RepartidorResultsHolder>{

    private List<Repartidor_Bi> results=new ArrayList<>();
    private RepartidorClick mRepartidorClick;


    @NonNull
    @Override
    public RepartidorResultsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repartidor,parent,false);
        return new RepartidorResultsAdapter.RepartidorResultsHolder(view,mRepartidorClick);
    }

    @Override
    public void onBindViewHolder(@NonNull RepartidorResultsHolder holder, int position) {
        Repartidor_Bi repartidor_bi=results.get(position);
        holder.textView_nombre.setText(repartidor_bi.getNombre_usuario());
        holder.textView_codigo.setText(String.valueOf(repartidor_bi.getIdrepartidor()));

        if (repartidor_bi.getFoto()!= null) {
            String imageUrl =repartidor_bi.getFoto()
                    .replace("http://", "https://");

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.imageView_foto);
        }


    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Repartidor_Bi> results,RepartidorClick repartidorClick){
        this.mRepartidorClick=repartidorClick;
        this.results=results;
        notifyDataSetChanged();
    }

    public class RepartidorResultsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RepartidorClick mRepartidorClick;

        ImageView imageView_foto;

        TextView textView_nombre,textView_codigo;

        public RepartidorResultsHolder(@NonNull View itemView,RepartidorClick repartidorClick) {
            super(itemView);
            this.mRepartidorClick=repartidorClick;
            imageView_foto=itemView.findViewById(R.id.imageView_foto);
            textView_nombre=itemView.findViewById(R.id.textView_nombre);
            textView_codigo=itemView.findViewById(R.id.textView_codigo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mRepartidorClick.selectRepartidor(results.get(getAdapterPosition()));
        }
    }


    public interface RepartidorClick{
        void selectRepartidor(Repartidor_Bi repartidor);
    }
}
