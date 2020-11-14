package com.example.empresayego.View.DrawerUI.dataEmpresa;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.ViewModel.EmpresaViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DialogUpdateTelefonoFragment extends AppCompatDialogFragment {

    private EditText dialog_update_TELEFONO;

    private DialogUpdateTelefono listener;

    private EmpresaViewModel viewModel;

    private Empresa mEmpresaResponse;

    private Empresa mEmpresa;

    private String numeroTelefono;


    public static  DialogUpdateTelefonoFragment newInstance(Empresa empresa){
        DialogUpdateTelefonoFragment fragment= new DialogUpdateTelefonoFragment();
        Bundle bundle= new Bundle();
        bundle.putSerializable("empresa",empresa);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments()!=null){
            Bundle bundle=getArguments();
            mEmpresa=(Empresa) bundle.getSerializable("empresa");
        }

        numeroTelefono=mEmpresa.getTelefono_empresa();

        viewModel = ViewModelProviders.of(this).get(EmpresaViewModel.class);
        viewModel.init();
        viewModel.getEmpresaLiveData().observe(this, new Observer<Empresa>() {
            @Override
            public void onChanged(Empresa empresa) {

                if(empresa !=null){
                    mEmpresaResponse=empresa;
                    Empresa.sEmpresa=mEmpresaResponse;
                }
            }
        });

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());

        LayoutInflater inflater= getActivity().getLayoutInflater();

        View view=inflater.inflate(R.layout.dialog_update_telefono,null);
/*
        dialog_update_TELEFONO=view.findViewById(R.id.dialog_update_TELEFONO);

        dialog_update_TELEFONO.setText(numeroTelefono);


        builder.setView(view)
                .setTitle("Actualiza")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        numeroTelefono =dialog_update_TELEFONO.getText().toString();

                        System.out.println("NUMERO DE TELEFONO" + numeroTelefono);
                        sendData();
                        viewModel.updateNumeroTelefono(mEmpresa.getIdempresa(),numeroTelefono);

                        Empresa.sEmpresa.setTelefono_empresa(numeroTelefono);

                    }
                });
        */


        return builder.create();


    }


    private void sendData(){
        listener.clickUpdate(dialog_update_TELEFONO.getText().toString());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (DialogUpdateTelefonoFragment.DialogUpdateTelefono) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FormDialogListener");
        }
    }

    public interface DialogUpdateTelefono{
        void clickUpdate(String numeroTelefono);
    }
}
