package com.example.empresayego.View.OrderUI.ProcessOrder;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.empresayego.MainActivity;
import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Restaurante_Pedido;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Restaurante_PedidoProcesResultsAdapter extends  RecyclerView.Adapter<Restaurante_PedidoProcesResultsAdapter.Restaurante_PedidoResultsHolder> implements Filterable {

    private List<Restaurante_Pedido> results= new ArrayList<>();
    private ClickPedidoReciente mClickPedidoReciente;


    private List<Restaurante_Pedido> resultsFiltered= new ArrayList<>();


    Date date1;


    private static  SparseArray<CountDownTimer> countDownMap;



    @NonNull
    @Override
    public Restaurante_PedidoResultsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_proces_orders_item,parent,false);
        return  new Restaurante_PedidoProcesResultsAdapter.Restaurante_PedidoResultsHolder(view,mClickPedidoReciente);
    }

    @Override
    public void onBindViewHolder(@NonNull Restaurante_PedidoResultsHolder holder, int position) {

        Restaurante_Pedido restaurante_pedido=resultsFiltered.get(position);


        holder.fragment_proces_order_item_CANTIDAD_PRODUCTOS.setText(String.valueOf(restaurante_pedido.getPedido_cantidadtotal()));
        holder.fragment_proces_order_item_CANTIDAD_PRODUCTOS.setTextColor(Color.WHITE);





        String patternInit = "hh:mm:ss a";
        @SuppressLint("SimpleDateFormat") DateFormat dateFormatInit = new SimpleDateFormat(patternInit);
        String fechaVenta=dateFormatInit.format(restaurante_pedido.getVenta_fecha());
        holder.fragment_proces_order_item_HORA_DE_LLEGADA.setText(fechaVenta);
        holder.fragment_proces_order_item_HORA_DE_LLEGADA.setTextColor(Color.WHITE);


        holder.fragment_proces_order_item_NOMBRE_CLIENTE.setText(restaurante_pedido.getUsuario_nombre());
        holder.fragment_proces_order_item_NOMBRE_CLIENTE.setTextColor(Color.WHITE);


        String tiempo="#"+restaurante_pedido.getIdempresa()+""+
                restaurante_pedido.getIdpedido()+""+
                restaurante_pedido.getIdventa();

        holder.fragment_proces_order_item_NUMERO_ORDEN.setText(tiempo);

        holder.fragment_proces_order_item_NUMERO_ORDEN.setTextColor(Color.WHITE);


        String tiempo1=restaurante_pedido.getCodigo_repartidor();

        Timestamp ts = Timestamp.valueOf(tiempo1);

        String pattern = "HH:mm:ss";
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat(pattern);
        String time1=dateFormat.format(ts);





        @SuppressLint("SimpleDateFormat") DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String time2 = dateFormat2.format(date);



        System.out.println("dateformated  " +time1+" |  fecha1 "+time2);



        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        date1 = null;
        Date date2 =null;
        try {
            date1 = format.parse(time1);
            date2=format.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long difference = date2.getTime() - date1.getTime();


        System.out.println("date2  " +date2.getTime());
        System.out.println("date1 " +date1.getTime());


        System.out.println("diferencia  " +difference);


        long tiempoTotal= (long) (Integer.valueOf(restaurante_pedido.getTiempo_espera()) * 60000);

        System.out.println("diferencia  " +tiempoTotal);


        long tiempoRestante=tiempoTotal-difference;

        //holder.fragment_proces_order_item_TIME_PROGRES.setProgress(  );
        holder.fragment_proces_order_item_TIME_PROGRES.setMax(100);

       // holder.fragment_proces_order_item_TIME_PROGRES.setProgress((int)((difference*100)/tiempoTotal));

        System.out.println("TIEMPO RESTANTE DE ID VENTA" +restaurante_pedido.getIdventa()+ " porcentaje" + (int)((difference*100)/tiempoTotal));


        holder.fragment_proces_order_item_TIME.setTextColor(Color.WHITE);


                holder.countDownTimer=new CountDownTimer(tiempoRestante, 1000){
                    public void onTick(long millisUntilFinished){

                        String tiempoTranscurrido=hmsTimeFormatter(millisUntilFinished);
                       holder.fragment_proces_order_item_TIME.setText(tiempoTranscurrido);
                        holder.fragment_proces_order_item_TIME_PROGRES.setProgress((int) ((millisUntilFinished*100) / tiempoTotal));
                        System.out.println("porcentaje mas "+ restaurante_pedido.getIdventa() +" idventa"+(int) ((millisUntilFinished*100 )/ tiempoTotal));

                    }
                    public  void onFinish(){

                        mClickPedidoReciente.updateStateToReadyOrder(restaurante_pedido,holder.getAdapterPosition());

                        holder.linear_change_state.setBackgroundColor(Color.rgb(236,67,67));


                    }
                }.start();


                countDownMap.put(holder.fragment_proces_order_item_CANTIDAD_PRODUCTOS.hashCode(), holder.countDownTimer);


    }

    @Override
    public int getItemCount() {
        return resultsFiltered.size();
    }

    public void setResults(List<Restaurante_Pedido> results, ClickPedidoReciente clickPedidoReciente){
        this.mClickPedidoReciente=clickPedidoReciente;
        this.results=results;
        this.resultsFiltered=results;
        countDownMap = new SparseArray<>();
        notifyDataSetChanged();
    }


    public List<Restaurante_Pedido> getList(){
        return results;
    }



    public void clear() {
        int size = results.size();
        results.clear();
        notifyItemRangeRemoved(0, size);

        notifyDataSetChanged();
    }

    public void cancelAllTimers(int positionCount,int cantTiempo) {
        if (countDownMap == null) {
            return;
        }
        Log.e("TAG",  "size :  " + countDownMap.size());
        for (int i = 0,length = countDownMap.size(); i < length; i++) {
            if(positionCount==countDownMap.keyAt(i)){
                System.out.println("REMUEVO COUNTDOWN" + countDownMap.keyAt(i)+" cantidad de tiempo" + cantTiempo*60000);
                CountDownTimer cdt = countDownMap.get(countDownMap.keyAt(i));
                if (cdt != null) {
                   // cdt.onTick((long) cantTiempo*60000);
                    System.out.println("CANCELAOS EN CDT");
                    cdt.onFinish();
                    countDownMap.remove(countDownMap.keyAt(i));
                }
            }
        }
    }

    public void modified(int tiempo,int idVenta){

        for(Restaurante_Pedido data:results){
            System.out.println(data.getIdventa()+ "idventa " + data.getTiempo_espera()+"tiempo");
        }
        for(Restaurante_Pedido pedido:results){

            if(idVenta==pedido.getIdventa()){
                pedido.setTiempo_espera(String.valueOf(tiempo));
            }

        }
        System.out.println("------------------------");

        for(Restaurante_Pedido data:results){
            System.out.println(data.getIdventa()+ "idventa " + data.getTiempo_espera()+"tiempo");
        }



        notifyDataSetChanged();
    }

    public static void cancelAllTimers2() {
        if (countDownMap == null) {
            return;
        }
        Log.e("TAG",  "size :  " + countDownMap.size());
        for (int i = 0,length = countDownMap.size(); i < length; i++) {

                CountDownTimer cdt = countDownMap.get(countDownMap.keyAt(i));
                if (cdt != null) {
                    cdt.cancel();
                    System.out.println("ESTAMOS ELIMINANDO ");
                }



        }
        countDownMap.clear();
    }

    public int  resultsSize(){
        return results.size();
    }

    public boolean searchObject(Restaurante_Pedido pedido){

        System.out.println("TAMAÑOI DE LA LISTA" + results.size() + " DATOS");


        boolean respuesta=false;

        for(Restaurante_Pedido data:results){
            System.out.println("DATA DE LA LISTA" +data.getIdventa());

            if(pedido.getIdventa()==data.getIdventa()){
                respuesta=true;
            }
        }
        return respuesta;
    }

    public void removeItem(Restaurante_Pedido restaurante_pedido,int position){
        System.out.println(results.size() + " tamaño antes  de eliminar" + position);

        if(results.size()==1){
            position=0;
        }

        results.remove(position);
        System.out.println(results.size() + " tamaño despues de eliminar");
        //if(results.size()!=0){
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, results.size());


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
        private LinearLayout linear_change_state;
        private ProgressBar fragment_proces_order_item_TIME_PROGRES;
        public CountDownTimer countDownTimer;


        public Restaurante_PedidoResultsHolder(@NonNull View itemView,ClickPedidoReciente clickPedidoReciente) {
            super(itemView);
            this.mClickPedidoReciente=clickPedidoReciente;
            fragment_proces_order_item_HORA_DE_LLEGADA=itemView.findViewById(R.id.fragment_proces_order_item_HORA_DE_LLEGADA);
            fragment_proces_order_item_NUMERO_ORDEN=itemView.findViewById(R.id.fragment_proces_order_item_NUMERO_ORDEN);
            fragment_proces_order_item_NOMBRE_CLIENTE=itemView.findViewById(R.id.fragment_proces_order_item_NOMBRE_CLIENTE);
            fragment_proces_order_item_CANTIDAD_PRODUCTOS=itemView.findViewById(R.id.fragment_proces_order_item_CANTIDAD_PRODUCTOS);
            fragment_proces_order_item_TIME=itemView.findViewById(R.id.fragment_proces_order_item_TIME);
            linear_change_state=itemView.findViewById(R.id.linear_change_state);
            fragment_proces_order_item_TIME_PROGRES=itemView.findViewById(R.id.fragment_proces_order_item_TIME_PROGRES);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {



            if(!updateStateReady(results.get(getAdapterPosition()))){
                mClickPedidoReciente.clickPedido(resultsFiltered.get(getAdapterPosition()),getAdapterPosition(),countDownMap.keyAt(getAdapterPosition()));

            }


        }
    }

    public interface ClickPedidoReciente{

        void clickPedido(Restaurante_Pedido restaurante_pedido, int posisiton,int positionCount);

        void updateStateToReadyOrder(Restaurante_Pedido pedido,int position);
    }


    private String hmsTimeFormatter(long milliSeconds) {

        String hms = String.format("%02d",
             //   TimeUnit.MILLISECONDS.toHours(milliSeconds),
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)));
              //  TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));
        return hms;


    }

    private boolean updateStateReady (Restaurante_Pedido pedido){

        boolean respuesta=false;

        Timestamp ts = Timestamp.valueOf(pedido.getCodigo_repartidor());

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

        Long tiempoTotal= new Long(Integer.valueOf(pedido.getTiempo_espera())*60000);


        if((difference)>=tiempoTotal) {
            respuesta=true;
        }


        return respuesta;

    }

    private int tiempoRestante(Restaurante_Pedido pedido){


        Timestamp ts = Timestamp.valueOf(pedido.getCodigo_repartidor());

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

        int tiempo=(int)(difference/1000);

        return tiempo;


    }




}
