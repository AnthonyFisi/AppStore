package com.example.empresayego.Login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MyReceiver extends BroadcastReceiver {

    public static ConnectivityReciverListener connectivityReciverListener;

    @Override
    public void onReceive(Context context, Intent intent) {


       ConnectivityManager cm =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork= cm.getActiveNetworkInfo();

        boolean isConnected=false;
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI && activeNetwork.isConnectedOrConnecting()) {
               isConnected=true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE && activeNetwork.isConnectedOrConnecting()) {
                isConnected=true;
            }
        }

        if(connectivityReciverListener!=null){
            connectivityReciverListener.onNetworkConnectionChanged(isConnected);
        }




    }

    public static boolean isConnected(){
        ConnectivityManager cm= (ConnectivityManager) MyApp.getInstance()
                .getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo activeNetwork= cm.getActiveNetworkInfo();

        boolean isConnected=false;
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI && activeNetwork.isConnectedOrConnecting()) {
                isConnected=true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE && activeNetwork.isConnectedOrConnecting()) {
                isConnected=true;
            }
        }

        return isConnected;

    }


    public interface ConnectivityReciverListener{
        void onNetworkConnectionChanged(boolean isConnected);
    }
}