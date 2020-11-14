package com.example.empresayego.View.DrawerUI.productos.AddProduct;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Categoria_producto_empresa;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Modelo.Producto;
import com.example.empresayego.View.DrawerUI.productos.ProductoDetail.CategoriaDetailActivity;
import com.example.empresayego.ViewModel.ProductoViewModel;

import java.io.File;

public class NewProductActivity extends AppCompatActivity {

    private static final String CATEGORIA_PRODUCTO_EMPRESA = "categoriaproducto";

    private static final String TIPOMENU = "tipomenu";

    private EditText editText_nombre_producto,editText_detalle_producto,editText_precio_producto;

    private CardView button_agregar_producto,insert_product,cerrar_product;

    private Categoria_producto_empresa categoria_producto_empresa;

    private ProgressBar progres_confirmar;

    private TextView load_text,nombre_producto,error_nombre_producto;

    private int tipomenu;

    private ProductoViewModel viewModel;

    private LinearLayout linearlayout_ACTUALIZAR_OK,layout_nombre_producto,layout_detalle_producto,background_photo;

    private NestedScrollView nestedScrollView;

    private Toolbar mToolbar;

    private Button escoger_galeria,tomar_foto;

    private ImageView imageView_producto;

    //PRICE
    private TextView price_VALUE,price1_VALUE;

    private ImageButton price_menu_INCREMENT,price_menu_DISMISS,price1_menu_INCREMENT,price1_menu_DISMISS;

    private int price,price1;

    private float precioUpdate;

    int TOMAR_FOTO = 100;
    int SELEC_IMAGEN = 200;

    String CARPETA_RAIZ = "MisFotosApp";
    String CARPETAS_IMAGENES = "imagenes";
    String RUTA_IMAGEN = CARPETA_RAIZ + CARPETAS_IMAGENES;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        reciveDataIntent();
        declararWidgets();
        initViewModel();
        insertProduct();
        clickFollowInsertProduct();
        cerrarInsertProduct();

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(v->{
            onBackPressed();
        });


        if(ContextCompat.checkSelfPermission(NewProductActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NewProductActivity.this,
                    new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        tomar_foto.setOnClickListener(view -> tomarFoto());

        escoger_galeria.setOnClickListener(view -> seleccionarImagen());

        editText_nombre_producto.setCursorVisible(false);
        editText_detalle_producto.setCursorVisible(false);

        listenEditext();
        activeLayout();
        incrementPrice();
    }


    private void declararWidgets(){
        editText_nombre_producto=findViewById(R.id.editText_nombre_producto);
        editText_detalle_producto=findViewById(R.id.editText_detalle_producto);
        editText_precio_producto=findViewById(R.id.editText_precio_producto);
        button_agregar_producto=findViewById(R.id.button_agregar_producto);
        load_text=findViewById(R.id.load_text);
        progres_confirmar=findViewById(R.id.progres_confirmar);


        linearlayout_ACTUALIZAR_OK=findViewById(R.id.linearlayout_ACTUALIZAR_OK);
        insert_product=findViewById(R.id.insert_product);
        cerrar_product=findViewById(R.id.cerrar_product);

        mToolbar=findViewById(R.id.toolbar);

        nestedScrollView=findViewById(R.id.nestedScrollView);

        nombre_producto=findViewById(R.id.nombre_producto);

        escoger_galeria=findViewById(R.id.escoger_galeria);

        tomar_foto=findViewById(R.id.tomar_foto);

        imageView_producto=findViewById(R.id.imageView_producto);

        layout_nombre_producto=findViewById(R.id.layout_nombre_producto);
        error_nombre_producto=findViewById(R.id.error_nombre_producto);

        layout_detalle_producto=findViewById(R.id.layout_detalle_producto);


        //ADD PRICE

        price_menu_INCREMENT=findViewById(R.id.price_menu_INCREMENT);
        price_menu_DISMISS=findViewById(R.id.price_menu_DISMISS);
        price1_menu_INCREMENT=findViewById(R.id.price1_menu_INCREMENT);
        price1_menu_DISMISS=findViewById(R.id.price1_menu_DISMISS);

        price_VALUE=findViewById(R.id.price_VALUE);
        price1_VALUE=findViewById(R.id.price1_VALUE);


        background_photo=findViewById(R.id.background_photo);

    }

    private void initViewModel(){

        viewModel=new ViewModelProvider(this).get(ProductoViewModel.class);
        viewModel.init();
        viewModel.getProductoLiveData().observe(this, new Observer<Producto>() {
            @Override
            public void onChanged(Producto producto) {
                progres_confirmar.setVisibility(View.GONE);

                if(producto!=null){
                    nombre_producto.setText(producto.getProducto_nombre());
                    nestedScrollView.setVisibility(View.GONE);
                    mToolbar.setVisibility(View.GONE);

                    linearlayout_ACTUALIZAR_OK.setVisibility(View.VISIBLE);

                }else {
                    button_agregar_producto.setEnabled(true);


                    load_text.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void insertProduct(){
        button_agregar_producto.setOnClickListener(v->{



            if(editText_nombre_producto.getText().toString().length()>0){


                String fisrt_part="0."+price1_VALUE.getText().toString();

                String second_part=price_VALUE.getText().toString();

                precioUpdate=Float.valueOf(second_part)+Float.valueOf(fisrt_part);

                Producto producto=new Producto();
                producto.setIdcategoriaproducto(categoria_producto_empresa.getIdcategoriaproductoempresa());
                producto.setIdempresa(Empresa.sEmpresa.getIdempresa());
                producto.setProducto_nombre(editText_nombre_producto.getText().toString());
                producto.setProducto_precio(precioUpdate);
                producto.setProducto_stock(0);
                producto.setProducto_detalle(editText_detalle_producto.getText().toString());
                producto.setDisponible(true);
                producto.setTipomenu(tipomenu);
                producto.setProducto_uriimagen("");


                viewModel.insertProduct(Empresa.sEmpresa.getIdempresa(),categoria_producto_empresa.getIdcategoriaproductoempresa(),producto);

                progres_confirmar.setVisibility(View.VISIBLE);

                load_text.setVisibility(View.GONE);

                button_agregar_producto.setEnabled(false);
            }else {

                Toast.makeText(this,"Ingresar el nombre del producto",Toast.LENGTH_LONG).show();
                error_nombre_producto.setVisibility(View.VISIBLE);
            }



        });
    }

    private void clickFollowInsertProduct(){
        insert_product.setOnClickListener(v->{

            linearlayout_ACTUALIZAR_OK.setVisibility(View.GONE);

            nestedScrollView.setVisibility(View.VISIBLE);
            mToolbar.setVisibility(View.VISIBLE);

            button_agregar_producto.setEnabled(true);

            editText_nombre_producto.setText("");
            editText_detalle_producto.setText("");
            editText_precio_producto.setText("");


            price= 0;
            price1= 0;
            price_VALUE.setText(String.valueOf(price));

            price1_VALUE.setText(String.valueOf(price1));

            imageView_producto.setImageURI(null);

            editText_nombre_producto.setCursorVisible(false);
            editText_detalle_producto.setCursorVisible(false);
            imageView_producto.setVisibility(View.GONE);
            background_photo.setVisibility(View.VISIBLE);



        });
    }

    private void cerrarInsertProduct(){

        cerrar_product.setOnClickListener(v->{
            finish();
        });
    }

    private void reciveDataIntent() {
        if (getIntent().getSerializableExtra(CATEGORIA_PRODUCTO_EMPRESA) != null) {
            categoria_producto_empresa= (Categoria_producto_empresa) getIntent().getSerializableExtra(CATEGORIA_PRODUCTO_EMPRESA);
        }

        tipomenu=getIntent().getIntExtra(TIPOMENU,0);

    }

    public static Intent startIntentCategoriaDetailActivity(Context context, Categoria_producto_empresa categoria_producto_empresa,int tipomenu){
        Intent intent= new Intent(context, NewProductActivity.class);
        intent.putExtra(CATEGORIA_PRODUCTO_EMPRESA,categoria_producto_empresa);
        intent.putExtra(TIPOMENU,tipomenu);
        return intent;
    }

    private void listenEditext(){
        editText_nombre_producto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable.toString().length()>0){
                    layout_nombre_producto.setBackground(getResources().getDrawable(R.drawable.border_inicio_sesion_able));
                    error_nombre_producto.setVisibility(View.GONE);
                }else {
                    layout_nombre_producto.setBackground(getResources().getDrawable(R.drawable.border_inicio_sesion));
                }
            }
        });

        editText_detalle_producto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length()>0){
                    layout_detalle_producto.setBackground(getResources().getDrawable(R.drawable.border_inicio_sesion_able));
                }else {
                    layout_detalle_producto.setBackground(getResources().getDrawable(R.drawable.border_inicio_sesion));
                }
            }
        });
    }

    private void activeLayout(){
        editText_nombre_producto.setOnClickListener(v->{
            layout_nombre_producto.setBackground(getResources().getDrawable(R.drawable.border_inicio_sesion_able));
            layout_detalle_producto.setBackground(getResources().getDrawable(R.drawable.border_inicio_sesion));
            editText_nombre_producto.setCursorVisible(true);
        });

        editText_detalle_producto.setOnClickListener(v->{
            layout_nombre_producto.setBackground(getResources().getDrawable(R.drawable.border_inicio_sesion));
            layout_detalle_producto.setBackground(getResources().getDrawable(R.drawable.border_inicio_sesion_able));
            editText_detalle_producto.setCursorVisible(true);
        });
    }

    private void incrementPrice(){

        price= 0;
        price1= 0;
        price_VALUE.setText(String.valueOf(price));

        price1_VALUE.setText(String.valueOf(price1));

        price_menu_INCREMENT.setOnClickListener(v->{
            price++;
            price_VALUE.setText(String.valueOf(price));

        });

        price_menu_DISMISS.setOnClickListener(v->{
            if(price<=0){
                Toast.makeText(this, "El limite fue alcanzado", Toast.LENGTH_SHORT).show();
            }else {
                price--;
                price_VALUE.setText(String.valueOf(price));

            }
        });


        price1_menu_INCREMENT.setOnClickListener(v->{


            if(price1 < 90 ) {

                price1+=10;
                price1_VALUE.setText(String.valueOf(price1));

            }else{
                Toast.makeText(this, "El limite fue alcanzado", Toast.LENGTH_SHORT).show();
            }

        });

        price1_menu_DISMISS.setOnClickListener(v->{
            if(10 <= price1) {

                price1 -= 10;
                price1_VALUE.setText(String.valueOf(price1));

            }else{
                Toast.makeText(this, "El limite fue alcanzado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void tomarFoto() {
        String nombreImagen = "";
        File fileImagen = new File(Environment.getExternalStorageDirectory(), RUTA_IMAGEN);
        boolean isCreada = fileImagen.exists();

        if(isCreada == false) {
            isCreada = fileImagen.mkdirs();
        }

        if(isCreada == true) {
            nombreImagen = (System.currentTimeMillis() / 1000) + ".jpg";
        }

        path = Environment.getExternalStorageDirectory()+File.separator+RUTA_IMAGEN+File.separator+nombreImagen;
        File imagen = new File(path);

        Intent intent = null;
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String authorities = this.getPackageName()+".provider";
            Uri imageUri = FileProvider.getUriForFile(this, authorities, imagen);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
        }

        startActivityForResult(intent, TOMAR_FOTO);
    }

    public void seleccionarImagen() {
        Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeria, SELEC_IMAGEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == SELEC_IMAGEN) {
            Uri imagenUri = null;
            if (data != null) imagenUri = data.getData();
            background_photo.setVisibility(View.GONE);

            imageView_producto.setVisibility(View.VISIBLE);
            imageView_producto.setImageURI(imagenUri);
        } else if(resultCode == RESULT_OK && requestCode == TOMAR_FOTO) {
            MediaScannerConnection.scanFile(NewProductActivity.this, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String s, Uri uri) {

                }
            });
            background_photo.setVisibility(View.GONE);

            imageView_producto.setVisibility(View.VISIBLE);
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            imageView_producto.setImageBitmap(bitmap);
        }
    }


}
