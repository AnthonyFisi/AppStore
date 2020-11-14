package com.example.empresayego.Login;

import android.app.Application;

public class MyApp extends Application {
    private static MyApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    public static synchronized MyApp getInstance(){
        return mInstance;
    }
    public void setConnectivityListener(MyReceiver.ConnectivityReciverListener listener){
        MyReceiver.connectivityReciverListener=listener;
    }
}
