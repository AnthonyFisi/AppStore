package com.mimiperla.empresayego.Proof;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


public class RxBus {

    private static RxBus mInstance;

    public static RxBus getInstance() {
        if (mInstance == null) {
            mInstance = new RxBus();
        }
        return mInstance;
    }
    private RxBus() {
    }


    private PublishSubject<Boolean> publisherStep2Fragment=PublishSubject.create();

    public void publishStep2Fragment(boolean request){
        publisherStep2Fragment.onNext(request);
    }

    public Observable<Boolean> listenStep2Fragment(){
        return publisherStep2Fragment;
    }

}