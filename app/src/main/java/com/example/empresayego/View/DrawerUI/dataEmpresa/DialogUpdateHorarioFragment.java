package com.example.empresayego.View.DrawerUI.dataEmpresa;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.ViewModel.EmpresaViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DialogUpdateHorarioFragment extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener {


    private DialogUpdateHorario listener;

    private EmpresaViewModel viewModel;

    private Empresa mEmpresaResponse;

    private Empresa mEmpresa;

    private Spinner spinner_inicio,spinner_fin;

    private List<String> listaHorarios;

    private List<String> listaHorario24;

    private String inicio,fin,start,end;

    public static DialogUpdateHorarioFragment newInstance(Empresa empresa){
        DialogUpdateHorarioFragment fragment= new DialogUpdateHorarioFragment();
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

        inicio=String.valueOf(mEmpresa.getHorario_inicio());
        fin=String.valueOf(mEmpresa.getHorario_fin());

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

        View view=inflater.inflate(R.layout.dialog_update_horario,null);


      declararWidgets(view);
      setDataWidget();

        builder.setView(view)
                .setTitle("Actualiza")
                .setNegativeButton("Cancelar", (dialogInterface, i) -> {

                })
                .setPositiveButton("Aceptar", (dialogInterface, i) -> {

                    viewModel.updateHorarioInicioFin(mEmpresa.getIdempresa(),Integer.valueOf(inicio),Integer.valueOf(fin));
                    sendData(start,end);
                });




        return builder.create();


    }

    private void declararWidgets(View view){
        spinner_fin=view.findViewById(R.id.spinner_fin);
        spinner_inicio=view.findViewById(R.id.spinner_inicio);

    }

    private void setDataWidget(){

        spinner_inicio.setOnItemSelectedListener(this);


        spinner_fin.setOnItemSelectedListener(this);

        listaHorarios();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,listaHorarios);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_inicio.setAdapter(dataAdapter);



        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,listaHorarios);

        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_fin.setAdapter(dataAdapter2);



    }


    private void sendData(String inicio,String fin){
        listener.clickUpdateHorario(inicio,fin);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (DialogUpdateHorarioFragment.DialogUpdateHorario) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FormDialogListener");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



        if(adapterView.getId()==spinner_inicio.getId()){

            inicio=listaHorario24.get(i);
            start=listaHorarios.get(i);

        }
        if(adapterView.getId()==spinner_fin.getId()){

            fin=listaHorario24.get(i);
            end=listaHorarios.get(i);



        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public interface DialogUpdateHorario{
        void clickUpdateHorario(String inicio,String fin);
    }


    private void listaHorarios() {

         listaHorarios= new ArrayList<>();
        listaHorarios.add("1:00 AM");
        listaHorarios.add("2:00 AM");
        listaHorarios.add("3:00 AM");
        listaHorarios.add("4:00 AM");
        listaHorarios.add("5:00 AM");
        listaHorarios.add("6:00 AM");
        listaHorarios.add("7:00 AM");
        listaHorarios.add("8:00 AM");
        listaHorarios.add("9:00 AM");
        listaHorarios.add("10:00 AM");
        listaHorarios.add("11:00 AM");
        listaHorarios.add("12:00 PM");
        listaHorarios.add("1:00 PM");
        listaHorarios.add("2:00 PM");
        listaHorarios.add("3:00 PM");
        listaHorarios.add("4:00 PM");
        listaHorarios.add("5:00 PM");
        listaHorarios.add("6:00 PM");
        listaHorarios.add("7:00 PM");
        listaHorarios.add("8:00 PM");
        listaHorarios.add("9:00 PM");
        listaHorarios.add("10:00 PM");
        listaHorarios.add("11:00 PM");
        listaHorarios.add("12:00 AM");

        listaHorario24=new ArrayList<>();

        for(int i=1;i<=24;i++){
            listaHorario24.add(String.valueOf(i));
        }

    }


}
