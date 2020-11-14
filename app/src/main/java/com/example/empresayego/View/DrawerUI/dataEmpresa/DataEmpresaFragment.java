package com.example.empresayego.View.DrawerUI.dataEmpresa;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.bumptech.glide.Glide;
import com.example.empresayego.Proof.RxBus;
import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.View.DrawerUI.soporte.SoporteFragment;

import org.jetbrains.annotations.NotNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class DataEmpresaFragment extends Fragment {



    private Empresa mEmpresa;
    private TextView fragment_data_NOMBRE,fragment_data_UBICACION,fragment_data_empresa_TELEFONO,fragment_data_empresa_CELULAR,
            fragment_data_empresa_HORARIO,fragment_data_empresa_TIEMPO,fragment_data_empresa_DESCRIPCION;
    private ImageView fragment_data_empresa_IMAGEN,fragment_data_empresa_EDITAR_TELEFONO,fragment_data_empresa_EDITAR_CELULAR,fragment_data_empresa_EDITAR_HORARIO,
            fragment_data_empresa_EDITAR_TIEMPO,fragment_data_empresa_EDITAR_DESCRIPCION;
    private BackToInicio mBackToInicio;
    private ImageView ic_back;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEmpresa=Empresa.sEmpresa;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_data_empresa, container, false);
        declararWidget(root);
        setDataWidget(mEmpresa);
        clickUpdateDataEmpresa();

        ic_back.setOnClickListener(v->{
            backToInicio();
        });

        resetData();

        return root;
    }

    private void declararWidget(View view){

        fragment_data_NOMBRE=view.findViewById(R.id.fragment_data_NOMBRE);
        fragment_data_UBICACION=view.findViewById(R.id.fragment_data_UBICACION);
        fragment_data_empresa_TELEFONO=view.findViewById(R.id.fragment_data_empresa_TELEFONO);
        fragment_data_empresa_CELULAR=view.findViewById(R.id.fragment_data_empresa_CELULAR);
        fragment_data_empresa_HORARIO=view.findViewById(R.id.fragment_data_empresa_HORARIO);
        fragment_data_empresa_TIEMPO=view.findViewById(R.id.fragment_data_empresa_TIEMPO);
        fragment_data_empresa_DESCRIPCION=view.findViewById(R.id.fragment_data_empresa_DESCRIPCION);


        fragment_data_empresa_IMAGEN=view.findViewById(R.id.fragment_data_empresa_IMAGEN);
        fragment_data_empresa_EDITAR_TELEFONO=view.findViewById(R.id.fragment_data_empresa_EDITAR_TELEFONO);
        fragment_data_empresa_EDITAR_CELULAR=view.findViewById(R.id.fragment_data_empresa_EDITAR_CELULAR);
        fragment_data_empresa_EDITAR_HORARIO=view.findViewById(R.id.fragment_data_empresa_EDITAR_HORARIO);
        fragment_data_empresa_EDITAR_TIEMPO=view.findViewById(R.id.fragment_data_empresa_EDITAR_TIEMPO);
        fragment_data_empresa_EDITAR_DESCRIPCION=view.findViewById(R.id.fragment_data_empresa_EDITAR_DESCRIPCION);

        ic_back=view.findViewById(R.id.ic_back);


    }

    private  void setDataWidget(Empresa empresa){


        if (empresa.getUrlfoto_empresa()!= null) {
            String imageUrl =empresa.getUrlfoto_empresa()
                    .replace("http://", "https://");

            Glide.with(this)
                    .load(imageUrl)
                    .into(fragment_data_empresa_IMAGEN);
        }


        fragment_data_NOMBRE.setText(empresa.getNombre_empresa());
        fragment_data_UBICACION.setText(empresa.getDireccion_empresa());
        fragment_data_empresa_TELEFONO.setText(empresa.getTelefono_empresa());
        fragment_data_empresa_CELULAR.setText(empresa.getCelular_empresa());

        fragment_data_empresa_HORARIO.setText(empresa.getHorario_inicio()+ " - "+ empresa.getHorario_fin());
        fragment_data_empresa_TIEMPO.setText(empresa.getTiempo_aproximado_entrega());
        fragment_data_empresa_DESCRIPCION.setText(empresa.getDescripcion_empresa());

    }

    private void clickUpdateDataEmpresa(){
        fragment_data_empresa_EDITAR_TELEFONO.setOnClickListener( v->{
            updateTelefono();
           // updateTiempo();

        });


        fragment_data_empresa_EDITAR_CELULAR.setOnClickListener(v->{
           updateCelular();
            //updateDescripcion();

        });

        fragment_data_empresa_EDITAR_HORARIO.setOnClickListener( v->{
            //updateHorario();
        });

        fragment_data_empresa_EDITAR_TIEMPO.setOnClickListener( v->{
            updateTiempo();
        });

        fragment_data_empresa_EDITAR_DESCRIPCION.setOnClickListener(v->{
            updateDescripcion();
        });

    }



    private void updateTelefono(){
        FragmentManager fm =getFragmentManager();
        DialogUpdateTelephoneFragment updateTelefonoFragment= DialogUpdateTelephoneFragment.newInstance(mEmpresa);
        updateTelefonoFragment.setTargetFragment(DataEmpresaFragment.this,300);
        updateTelefonoFragment.show(fm,"update");
    }
    private void updateCelular(){
        FragmentManager fm =getParentFragmentManager();
        DialogUpdateCelularFragment updateTelefonoFragment= DialogUpdateCelularFragment.newInstance(mEmpresa);
        updateTelefonoFragment.setTargetFragment(DataEmpresaFragment.this,300);
        updateTelefonoFragment.show(fm,"update");
    }

    private void updateHorario(){
        FragmentManager fm =getFragmentManager();
        DialogUpdateHorarioFragment updateHorarioFragment= DialogUpdateHorarioFragment.newInstance(mEmpresa);
        updateHorarioFragment.setTargetFragment(DataEmpresaFragment.this,300);
        updateHorarioFragment.show(fm,"update");
    }

    private void updateTiempo(){
        FragmentManager fm =getFragmentManager();
        DialogUpdateTiempoFragment updateTiempoFragment= DialogUpdateTiempoFragment.newInstance(mEmpresa);
        updateTiempoFragment.setTargetFragment(DataEmpresaFragment.this,300);
        updateTiempoFragment.show(fm,"update");
    }

    private void updateDescripcion(){
        FragmentManager fm =getParentFragmentManager();
        DialogUpdateDescripcionFragment updateDescripcionFragment=  DialogUpdateDescripcionFragment.newInstance(mEmpresa);
        updateDescripcionFragment.setTargetFragment(DataEmpresaFragment.this,300);
        updateDescripcionFragment.show(fm,"update");
    }



    private void backToInicio() {
        mBackToInicio.back();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mBackToInicio = (DataEmpresaFragment.BackToInicio) context;
    }

    public interface BackToInicio{
        void  back();
    }

    private void resetData(){
        RxBus.getInstance().listenStep2Fragment().subscribe(new io.reactivex.Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {

                if (aBoolean) {

                    fragment_data_empresa_TELEFONO.setText(Empresa.sEmpresa.getTelefono_empresa());
                  //  fragment_data_empresa_HORARIO.setText(inicio + "- " +fin);
                    fragment_data_empresa_CELULAR.setText(Empresa.sEmpresa.getCelular_empresa());
                    fragment_data_empresa_TIEMPO.setText(Empresa.sEmpresa.getTiempo_aproximado_entrega());
                    fragment_data_empresa_DESCRIPCION.setText(Empresa.sEmpresa.getDescripcion_empresa());


                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
