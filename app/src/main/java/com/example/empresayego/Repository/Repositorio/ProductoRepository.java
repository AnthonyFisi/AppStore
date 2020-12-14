package com.example.empresayego.Repository.Repositorio;

import com.example.empresayego.Repository.Modelo.Gson.GsonProducto;
import com.example.empresayego.Repository.Modelo.Producto;
import com.example.empresayego.Repository.Service.ProductoService;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoRepository {


    private static final String RESTAURANTE_PEDIDO_SERVICE_URL_BASE="https://backend-tiend-go.herokuapp.com/";

    private ProductoService mProductoService;
    private MutableLiveData<GsonProducto> mGsonProductoMutableLiveData;
    private MutableLiveData<Producto>  mProductoMutableLiveData;

    public ProductoRepository(){
        mGsonProductoMutableLiveData=new MutableLiveData<>();
        mProductoMutableLiveData= new MutableLiveData<>();
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(RESTAURANTE_PEDIDO_SERVICE_URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mProductoService= retrofit.create(ProductoService.class);
    }

    public void searchProductoById(String token,int idEmpresa){
        mProductoService.searchProductoById(token,idEmpresa).enqueue(new Callback<GsonProducto>() {
            @Override
            public void onResponse(Call<GsonProducto> call, Response<GsonProducto> response) {
                if(response.code()==200 && response.body()!=null){
                    System.out.println("GOOD 1");

                    mGsonProductoMutableLiveData.postValue(response.body());
                }else {
                    System.out.println("GOOD 2");

                    mGsonProductoMutableLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<GsonProducto> call, Throwable t) {
                mGsonProductoMutableLiveData.setValue(null);
                System.out.println("GOOD 3");



            }
        });
    }

    public void updateStateProduto(String token,int idProducto,int idEmpresa,boolean state){
        mProductoService.updateStateProducto(token,idProducto,idEmpresa,state).enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {

                if(response.code()==200 && response.body()!=null){

                   mProductoMutableLiveData.postValue(response.body());

                    System.out.println("GOOD 1");
                }else {
                    System.out.println("BAD 2");

                    mProductoMutableLiveData.postValue(null);

                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                System.out.println("BAD 3");

                System.out.println(t.getCause()+"/"+t.getLocalizedMessage()+"/"+t.getStackTrace());

                mProductoMutableLiveData.postValue(null);

            }
        });
    }

    public void searchListProducto(String token,int idcategoriaproducto, int idempresa){
        mProductoService.searchListProducto(token,idcategoriaproducto,idempresa).enqueue(new Callback<GsonProducto>() {
            @Override
            public void onResponse(Call<GsonProducto> call, Response<GsonProducto> response) {


                if(response.code()==400 || response.code()==401 || response.code()==500){
                    mGsonProductoMutableLiveData.postValue(null);
                }

                if(response.body() !=null && response.code()==200){
                    mGsonProductoMutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<GsonProducto> call, Throwable t) {
                System.out.println("ESTOY EN DATOS NULOSSSSSS");

                mGsonProductoMutableLiveData.postValue(null);
            }
        });
    }

    public void insertProduct(String token,Producto producto){
        mProductoService.insertProducto(token,producto).enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {

                if(response.code()==400 || response.code()==401 || response.code()==500){
                    mProductoMutableLiveData.postValue(null);
                }

                if(response.body() !=null && response.code()==200){
                    mProductoMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                mProductoMutableLiveData.postValue(null);

            }
        });
    }

    public LiveData<GsonProducto> getProductoGsonDataLiveData(){ return  mGsonProductoMutableLiveData; }

    public LiveData<Producto> getProductoDataLiveData(){
        return  mProductoMutableLiveData;
    }
}
