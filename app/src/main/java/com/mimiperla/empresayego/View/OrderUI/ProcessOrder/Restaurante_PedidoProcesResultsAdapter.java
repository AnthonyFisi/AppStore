package com.mimiperla.empresayego.View.OrderUI.ProcessOrder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Restaurante_Pedido;
import com.mimiperla.empresayego.View.ProcesoOrdenActivity;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Restaurante_PedidoProcesResultsAdapter extends  RecyclerView.Adapter<Restaurante_PedidoProcesResultsAdapter.Restaurante_PedidoResultsHolder> implements Filterable {


    private List<Restaurante_Pedido> results= new ArrayList<>();
    private ClickPedidoReciente mClickPedidoReciente;


    private List<Restaurante_Pedido> resultsFiltered= new ArrayList<>();


    Date date1;


    //private static  SparseArray<CountDownTimer> countDownMap;
    private Context context;


    @NonNull
    @Override
    public Restaurante_PedidoResultsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_proces_orders_item,parent,false);
        return  new Restaurante_PedidoProcesResultsAdapter.Restaurante_PedidoResultsHolder(view,mClickPedidoReciente);
    }

    @Override
    public void onBindViewHolder(@NonNull Restaurante_PedidoResultsHolder holder, int position) {

        Restaurante_Pedido restaurante_pedido=resultsFiltered.get(position);


        System.out.println("PINTAMOS "+restaurante_pedido.getHoraservidor()+" id "+restaurante_pedido.getIdventa());

            if (restaurante_pedido.isMesa()) {


                holder.linear_change_state.setVisibility(View.GONE);
                holder.mesa.setVisibility(View.VISIBLE);

                String patternInit = "hh:mm:ss a";
                @SuppressLint("SimpleDateFormat") DateFormat dateFormatInit2 = new SimpleDateFormat(patternInit);

                String fechaVenta2 = dateFormatInit2.format(Timestamp.valueOf(restaurante_pedido.getFechaAceptado()));


                ProductosProcesResultsAdapter adapter= new ProductosProcesResultsAdapter();
                adapter.setResults(restaurante_pedido.getListaProductos());

                String numeromesa = "Mesa " + restaurante_pedido.getNumeromesa();
                holder.fragment_home_item_orden_NUMERO_MESA.setText(numeromesa);
                holder.fragment_home_item_orden_FECHA.setText(fechaVenta2);
                holder.fragment_home_item_orden_NOMBRE_CLIENTE_2.setText(restaurante_pedido.getNombre());

                 holder.recycler_productos.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                holder.recycler_productos.setAdapter(adapter);

              //  holder.activity_carrito_CANTIDAD_SUBTOTAL.setText(String.valueOf(restaurante_pedido.getPedido_cantidadtotal()));
               // holder.activity_carrito_COSTO_DELIVERY.setText(String.valueOf(restaurante_pedido.getDescuento_mesa()));
                //holder.activity_carrito_CANTIDAD_TOTAL.setText(String.valueOf(restaurante_pedido.getVenta_costototal()));

                //holder.activity_carrito_CANTIDAD_DELIVERY.setText(String.valueOf(restaurante_pedido.getVenta_costodelivery()));

            } else {



                holder.mesa.setVisibility(View.GONE);
                holder.linear_change_state.setVisibility(View.VISIBLE);

                holder.fragment_proces_order_item_CANTIDAD_PRODUCTOS.setText(String.valueOf(restaurante_pedido.getPedido_cantidadtotal()));

                String patternInit = "hh:mm:ss a";

                @SuppressLint("SimpleDateFormat") DateFormat dateFormatInit = new SimpleDateFormat(patternInit);
                String fechaVenta = dateFormatInit.format(restaurante_pedido.getVentafecha());
                holder.fragment_proces_order_item_HORA_DE_LLEGADA.setText(fechaVenta);


                holder.fragment_proces_order_item_NOMBRE_CLIENTE.setText(restaurante_pedido.getNombre());


                String tiempo = "#"+restaurante_pedido.getIdventa();

                holder.fragment_proces_order_item_NUMERO_ORDEN.setText(tiempo);


                String tiempoAceptado = restaurante_pedido.getFechaAceptado();

                Timestamp timeStart = Timestamp.valueOf(tiempoAceptado);

                //Timestamp timeNow=restaurante_pedido.getHoraservidor();

                Timestamp timeNow=new Timestamp(System.currentTimeMillis());

                System.out.println("timeStart " + timeStart + " |  timeNow " + timeNow);


                long difference = timeNow.getTime() - timeStart.getTime();


                System.out.println("diferencia  " + difference);


                long tiempoTotal = (long) (Integer.valueOf(restaurante_pedido.getTiempototal_espera()));

                System.out.println("diferencia  " + tiempoTotal);


                long tiempoRestante = tiempoTotal - difference;

                //holder.fragment_proces_order_item_TIME_PROGRES.setProgress(  );
                holder.fragment_proces_order_item_TIME_PROGRES.setMax(100);

                // holder.fragment_proces_order_item_TIME_PROGRES.setProgress((int)((difference*100)/tiempoTotal));

                System.out.println("TIEMPO RESTANTE DE ID VENTA" + restaurante_pedido.getIdventa() + " porcentaje" + (int) ((difference * 100) / tiempoTotal));


                holder.linear_change_state.setBackgroundColor(Color.rgb(255, 255, 255));

                if(!findByIdVentaTimer(restaurante_pedido.getIdventa())) {

                    holder.countDownTimer = new CountDownTimer(tiempoRestante, 1000) {
                        public void onTick(long millisUntilFinished) {

                            String tiempoTranscurrido = hmsTimeFormatter(millisUntilFinished,holder);
                            holder.fragment_proces_order_item_TIME.setText(tiempoTranscurrido);
                            holder.fragment_proces_order_item_TIME_PROGRES.setProgress((int) ((millisUntilFinished * 100) / tiempoTotal));
                            System.out.println("milisegundos "+millisUntilFinished+" porcentaje " + (int) ((millisUntilFinished * 100) / tiempoTotal)+ " idventa" + restaurante_pedido.getIdventa() );

                        }

                        public void onFinish() {

                            //mClickPedidoReciente.updateStateToReadyOrder(restaurante_pedido, holder.getAdapterPosition());
                            holder.pedido_time.setVisibility(View.GONE);

                            holder.linear_change_state.setBackground(context.getResources().getDrawable(R.drawable.border_general_redlight));

                            deleteCounter(restaurante_pedido.getIdventa());

                        }
                    }.start();


                    System.out.println("ENTRAMOS A COUNTER "+restaurante_pedido.getIdventa());

                    ProcesoOrdenActivity.countDownMap.put(restaurante_pedido.getIdventa(), holder.countDownTimer);


                }else{
                    Log.e("TAG",  "No entramos o trave vez :  " + restaurante_pedido.getIdventa());

                }
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

    public void setResults(List<Restaurante_Pedido> results, ClickPedidoReciente clickPedidoReciente,Context context){
        this.mClickPedidoReciente=clickPedidoReciente;
        this.results=results;
        this.resultsFiltered=results;
        this.context=context;
        notifyDataSetChanged();
    }


    public List<Restaurante_Pedido> getList(){
        return resultsFiltered;
    }






    int  resultsSize(){
        return resultsFiltered.size();
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
                resultsFiltered= (ArrayList<Restaurante_Pedido>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }




    public class Restaurante_PedidoResultsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ClickPedidoReciente mClickPedidoReciente;
        private TextView fragment_proces_order_item_TIME,fragment_proces_order_item_HORA_DE_LLEGADA,
                fragment_proces_order_item_NUMERO_ORDEN,fragment_proces_order_item_NOMBRE_CLIENTE,fragment_proces_order_item_CANTIDAD_PRODUCTOS;
        private CardView linear_change_state;
        private ProgressBar fragment_proces_order_item_TIME_PROGRES;

        /* MESA*/
        private TextView fragment_home_item_orden_NUMERO_MESA,fragment_home_item_orden_FECHA,fragment_home_item_orden_NOMBRE_CLIENTE_2;
       // private TextView activity_carrito_CANTIDAD_SUBTOTAL,activity_carrito_CANTIDAD_DELIVERY;
        //activity_carrito_COSTO_DELIVERY,activity_carrito_CANTIDAD_TOTAL,
        private RecyclerView recycler_productos;
        private CardView mesa;

       // private Button pedido_listo;

        CountDownTimer countDownTimer;

        LinearLayout pedido_time;

        private TextView format_time;


        Restaurante_PedidoResultsHolder(@NonNull View itemView, ClickPedidoReciente clickPedidoReciente) {
            super(itemView);
            this.mClickPedidoReciente=clickPedidoReciente;
            fragment_proces_order_item_HORA_DE_LLEGADA=itemView.findViewById(R.id.fragment_proces_order_item_HORA_DE_LLEGADA);
            fragment_proces_order_item_NUMERO_ORDEN=itemView.findViewById(R.id.fragment_proces_order_item_NUMERO_ORDEN);
            fragment_proces_order_item_NOMBRE_CLIENTE=itemView.findViewById(R.id.fragment_proces_order_item_NOMBRE_CLIENTE);
            fragment_proces_order_item_CANTIDAD_PRODUCTOS=itemView.findViewById(R.id.fragment_proces_order_item_CANTIDAD_PRODUCTOS);
            fragment_proces_order_item_TIME=itemView.findViewById(R.id.fragment_proces_order_item_TIME);
            linear_change_state=itemView.findViewById(R.id.linear_change_state);
            fragment_proces_order_item_TIME_PROGRES=itemView.findViewById(R.id.fragment_proces_order_item_TIME_PROGRES);

            /*MESA*/
            fragment_home_item_orden_NUMERO_MESA=itemView.findViewById(R.id.fragment_home_item_orden_NUMERO_MESA);
            fragment_home_item_orden_FECHA=itemView.findViewById(R.id.fragment_home_item_orden_FECHA);
            fragment_home_item_orden_NOMBRE_CLIENTE_2=itemView.findViewById(R.id.fragment_home_item_orden_NOMBRE_CLIENTE_2);
            recycler_productos=itemView.findViewById(R.id.recycler_productos);

           // activity_carrito_CANTIDAD_SUBTOTAL=itemView.findViewById(R.id.activity_carrito_CANTIDAD_SUBTOTAL);
            //activity_carrito_COSTO_DELIVERY=itemView.findViewById(R.id.activity_carrito_COSTO_DELIVERY);
            //activity_carrito_CANTIDAD_TOTAL=itemView.findViewById(R.id.activity_carrito_CANTIDAD_TOTAL);
            //activity_carrito_CANTIDAD_DELIVERY=itemView.findViewById(R.id.activity_carrito_CANTIDAD_DELIVERY);

            //pedido_listo=itemView.findViewById(R.id.pedido_listo);
            mesa=itemView.findViewById(R.id.mesa);

            format_time=itemView.findViewById(R.id.format_time);

            pedido_time=itemView.findViewById(R.id.pedido_time);

            //linear_change_state.setOnClickListener(this);

           // pedido_listo.setOnClickListener(this);

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


    private String hmsTimeFormatter(long milliSeconds,Restaurante_PedidoResultsHolder holder) {

        String hms="";
        int hora=3600000;

        int minutos=60000;

        if(milliSeconds>=hora){

            int formatHour= (int)(milliSeconds)/hora;

            hms=String.valueOf(formatHour);

            holder.format_time.setText("h");

        }else{
            int formatHour= (int)(milliSeconds)/minutos;

            hms=String.valueOf(formatHour);

            holder.format_time.setText("min");


        }

        return hms;


    }


    private int tiempoRestante(Restaurante_Pedido pedido){


        Timestamp ts = Timestamp.valueOf(pedido.getFechaAceptado());

        String pattern = "HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        String time1=dateFormat.format(ts);

        DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String time2 = dateFormat2.format(date);

        System.out.println("dateformated  " +time1+" |  fecha1 "+time2);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = null;
        Date date2 =null;
        try {
            date1 = format.parse(time1);
            date2=format.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long difference = date2.getTime() - date1.getTime();

        int tiempo=(int)(difference/60000);

        return tiempo;


    }



    void addResults(Restaurante_Pedido restaurante_pedido, ClickPedidoReciente clickPedidoReciente, Timestamp fechaServidor){

        this.mClickPedidoReciente=clickPedidoReciente;
        resultsFiltered.add(0,restaurante_pedido);
        updateFechaServidor(fechaServidor);
        cancelAllTimers();
        notifyItemInserted(0);

    }


    public static void cancelAllTimers() {
        if (ProcesoOrdenActivity.countDownMap == null) {
            return;
        }
        Log.e("TAG",  "size :  " + ProcesoOrdenActivity.countDownMap.size());
        for (int i = 0,length = ProcesoOrdenActivity.countDownMap.size(); i < length; i++) {

            CountDownTimer cdt = ProcesoOrdenActivity.countDownMap.get(ProcesoOrdenActivity.countDownMap.keyAt(i));

            if (cdt != null) {
                cdt.cancel();
                System.out.println("ESTAMOS ELIMINANDO ");
            }



        }
        ProcesoOrdenActivity.countDownMap.clear();


    }


    private boolean findByIdVentaTimer(int idVenta) {

        for (int i = 0,length = ProcesoOrdenActivity.countDownMap.size(); i < length; i++) {

            if(ProcesoOrdenActivity.countDownMap.keyAt(i)==idVenta){

                Log.e("TAG",  "Encontramos  ventas que existen:  " + idVenta);

                return true;
            }
        }

        return false;
    }

    private void deleteCounter(int idVenta){

        for (int i = 0,length = ProcesoOrdenActivity.countDownMap.size(); i < length; i++) {

            if(ProcesoOrdenActivity.countDownMap.keyAt(i)==idVenta){

                CountDownTimer cdt = ProcesoOrdenActivity.countDownMap.get(ProcesoOrdenActivity.countDownMap.keyAt(i));

                cdt.cancel();


                ProcesoOrdenActivity.countDownMap.remove(ProcesoOrdenActivity.countDownMap.keyAt(i));

                Log.e("TAG",  "Eliminamos el counter de la venta :  " + idVenta);

            }
        }
    }

    void removeItem(Restaurante_Pedido restaurante_pedido, String newFechaServidor){

        int i=0;
        int posi=0;

        for(Restaurante_Pedido pedido:resultsFiltered){

           DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date d = null;
            try {
                d = formatter.parse(newFechaServidor);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            pedido.setHoraservidor(Timestamp.valueOf(formatter.format(d)));

            if(pedido.getIdventa()==restaurante_pedido.getIdventa()){
               posi=i;
            }
            i++;
        }

       // deleteCounter(restaurante_pedido.getIdventa());
        cancelAllTimers();

        resultsFiltered.remove(posi);
        notifyItemRemoved(posi);
        notifyItemRangeChanged(posi, resultsFiltered.size());
        notifyDataSetChanged();

    }

    private void updateFechaServidor(Timestamp fechaServidor){
        for(Restaurante_Pedido pedido:ProcesoOrdenActivity.lista){
            pedido.setHoraservidor(fechaServidor);

        }
    }

}
