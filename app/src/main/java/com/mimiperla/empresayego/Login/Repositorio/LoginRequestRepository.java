package com.mimiperla.empresayego.Login.Repositorio;

import com.mimiperla.empresayego.Login.Modelo.JwtResponse;
import com.mimiperla.empresayego.Login.Modelo.LoginRequest;
import com.mimiperla.empresayego.Login.Service.LoginRequestService;

import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRequestRepository {

    private static final String RESTAURANTE_PEDIDO_SERVICE_URL_BASE= "http://192.168.100.34:8080/";
            //"https://deliverymimi.herokuapp.com/";
            //"https://backend-tiend-go.herokuapp.com/";

    private LoginRequestService mLoginRequestService;

    private MutableLiveData<JwtResponse> mJwtResponseMutableLiveData;

    public LoginRequestRepository(){
        mJwtResponseMutableLiveData= new MutableLiveData<>();
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(RESTAURANTE_PEDIDO_SERVICE_URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

       mLoginRequestService = retrofit.create(LoginRequestService.class);
    }


    public void registrarUsuarioEmpresa(LoginRequest loginRequest ){
        mLoginRequestService.registrarUsuarioEmpresa(loginRequest).enqueue(new Callback<JwtResponse>() {
            @Override
            public void onResponse(Call<JwtResponse> call, Response<JwtResponse> response) {

                if(response.code()==200 && response.body()!=null){
                    mJwtResponseMutableLiveData.postValue(response.body());
                }else {
                    mJwtResponseMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<JwtResponse> call, Throwable t) {
                mJwtResponseMutableLiveData.postValue(null);

            }
        });
    }


    public void signoutUsuarioEmpresa(LoginRequest loginRequest ){
        mLoginRequestService.signoutUsuarioEmpresa(loginRequest).enqueue(new Callback<JwtResponse>() {
            @Override
            public void onResponse(Call<JwtResponse> call, Response<JwtResponse> response) {

                if(response.code()==200 && response.body()!=null){
                    mJwtResponseMutableLiveData.postValue(response.body());
                }else {
                    mJwtResponseMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<JwtResponse> call, Throwable t) {
                mJwtResponseMutableLiveData.postValue(null);

            }
        });
    }
    public MutableLiveData<JwtResponse> getJwtResponseMutableLiveData(){
        return mJwtResponseMutableLiveData;
    }
}
