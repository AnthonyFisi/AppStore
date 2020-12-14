package com.example.empresayego.View.DrawerUI.productos.AddProduct;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Categoria_producto_empresa;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class TipoMenuFragment extends BottomSheetDialogFragment {

    public static final String TAG = "tag";
    private static final String CATEGORIA_PRODUCTO = "categoria_producto";

    private Categoria_producto_empresa mCategoria_producto_empresa;

    public TipoMenuFragment() {
        // Required empty public constructor
    }

    public static TipoMenuFragment newInstance(Categoria_producto_empresa categoria_producto_empresa) {
        TipoMenuFragment fragment = new TipoMenuFragment();
        Bundle args = new Bundle();
        args.putSerializable(CATEGORIA_PRODUCTO,categoria_producto_empresa);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategoria_producto_empresa=(Categoria_producto_empresa) getArguments().getSerializable(CATEGORIA_PRODUCTO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tipo_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CardView entrada = view.findViewById(R.id.entreda);
        CardView menu = view.findViewById(R.id.menu);
        ImageView imageView=view.findViewById(R.id.imageView);

        entrada.setOnClickListener(v->{
            //Intent intent=NewProductActivity.startIntentCategoriaDetailActivity(getContext(),mCategoria_producto_empresa,1);
           // startActivity(intent);
        });

        menu.setOnClickListener(v->{
        //    Intent intent=NewProductActivity.startIntentCategoriaDetailActivity(getContext(),mCategoria_producto_empresa,2);
          //  startActivity(intent);
        });

        imageView.setOnClickListener(v->{
            dismiss();
        });

    }
}
