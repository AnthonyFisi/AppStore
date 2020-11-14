package com.example.empresayego.View.DrawerUI.dataEmpresa;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.empresayego.Proof.RxBus;
import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Modelo.EmpresaOficial;
import com.example.empresayego.ViewModel.EmpresaOficialViewModel;
import com.example.empresayego.ViewModel.EmpresaViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class DialogUpdateTiempoFragment extends AppCompatDialogFragment {


 //   private DialogUpdateTiempo listener;

    private EmpresaOficialViewModel viewModel;

    private Empresa mEmpresaResponse;

    private Empresa mEmpresa;

  //  private Spinner spinner_inicio,spinner_fin;

    private List<String> listaTiempo;

    private ImageButton dialog_update_horario_DISMINUIR_TIEMPO,dialog_update_horario_INCREMENTAR_TIEMPO;

    private TextView dialog_update_horario,textView_tiempo;

    private String tiempo="minutos";

    private int tiempoDefault;

    private String tipoTiempo;

    private AlertDialog alertDialog;

    private CardView cardView_cerrar,cardView_update_tiempo;

    private ImageView ic_back;

    private LinearLayout linearLayout_update_tiempo,linearlayout_progressbar,linearlayout_success;



    public static DialogUpdateTiempoFragment newInstance(Empresa empresa){
        DialogUpdateTiempoFragment fragment= new DialogUpdateTiempoFragment();
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

        String [] data=mEmpresa.getTiempo_aproximado_entrega().trim().split(" ");
        tiempoDefault=Integer.valueOf(data[0]);
        tipoTiempo=data[1];


        viewModel = new ViewModelProvider(this).get(EmpresaOficialViewModel.class);
        viewModel.init();
        viewModel.getEmpresaOficialLiveData().observe(this, new Observer<EmpresaOficial>() {
            @Override
            public void onChanged(EmpresaOficial empresa) {
                linearlayout_progressbar.setVisibility(View.GONE);

                if(empresa !=null){
                    linearlayout_success.setVisibility(View.VISIBLE);
                    Empresa.sEmpresa.setTiempo_aproximado_entrega(empresa.getTiempo_aproximado_entrega());
                    textView_tiempo.setText( Empresa.sEmpresa.getTiempo_aproximado_entrega());
                    RxBus.getInstance().publishStep2Fragment(true);

                }else {
                    Toast.makeText(getContext(),"Presentamos problemas,volver a intentarlo !",Toast.LENGTH_LONG).show();
                    linearLayout_update_tiempo.setVisibility(View.VISIBLE);
                    textView_tiempo.setText(Empresa.sEmpresa.getTiempo_aproximado_entrega());
                }
            }
        });

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());

        View view=LayoutInflater.from(getContext()).inflate(R.layout.dialog_update_tiempo,null);

        builder.setView(view);


        declararWidgets(view);
      setDataWidget();
      clickButton();

        cardView_update_tiempo.setOnClickListener(v->{

            String time=tiempoDefault+" minutos";

            linearLayout_update_tiempo.setVisibility(View.GONE);
            linearlayout_progressbar.setVisibility(View.VISIBLE);
            viewModel.updateiempoAproximado(Empresa.sEmpresa.getIdempresa(),time);

        });

        ic_back.setOnClickListener(v->{
            // sendData();
            dismiss();
        });

        cardView_cerrar.setOnClickListener(v->{
            dismiss();
        });
/*
        builder.setView(view)
                .setTitle("Actualiza")
                .setNegativeButton("Cancelar", (dialogInterface, i) -> {

                })
                .setPositiveButton("Aceptar", (dialogInterface, i) -> {

                    String tiempoTotal=tiempoDefault+" "+tiempo.trim();
                    viewModel.updateiempoAproximado(mEmpresa.getIdempresa(),tiempoTotal);
                    sendData(tiempoTotal);
                });

        */

        alertDialog = builder.create();
        //asegura que se muestre el teclado
        alertDialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return builder.create();

    }

    private void declararWidgets(View view){
        //spinner_inicio=view.findViewById(R.id.spinner_inicio);
        dialog_update_horario=view.findViewById(R.id.dialog_update_tiempo);
        dialog_update_horario_DISMINUIR_TIEMPO=view.findViewById(R.id.dialog_update_tiempo_DISMINUIR_TIEMPO);
        dialog_update_horario_INCREMENTAR_TIEMPO=view.findViewById(R.id.dialog_update_tiempo_INCREMENTAR_TIEMPO);

        linearLayout_update_tiempo=view.findViewById(R.id.linearLayout_update_tiempo);
        linearlayout_progressbar=view.findViewById(R.id.linearlayout_progressbar);
        linearlayout_success=view.findViewById(R.id.linearlayout_success);

        cardView_cerrar=view.findViewById(R.id.cardView_cerrar);
        cardView_update_tiempo=view.findViewById(R.id.cardView_update_tiempo);

        ic_back=view.findViewById(R.id.ic_back);

        textView_tiempo=view.findViewById(R.id.textView_tiempo);


    }

    private void setDataWidget(){

        dialog_update_horario.setText(String.valueOf(tiempoDefault));

        //spinner_inicio.setOnItemSelectedListener(this);

       // listaTiempo();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,listaTiempo);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

      //  spinner_inicio.setAdapter(dataAdapter);


    }


    private void clickButton(){

        dialog_update_horario_DISMINUIR_TIEMPO.setOnClickListener( v->{

            if(tiempoDefault!=0){
                 tiempoDefault-=1;
                 dialog_update_horario.setText(String.valueOf(tiempoDefault));
            }

        });

        dialog_update_horario_INCREMENTAR_TIEMPO.setOnClickListener( v->{
            tiempoDefault+=1;
            dialog_update_horario.setText(String.valueOf(tiempoDefault));
        });
    }

/*
    private void sendData(String tiempo){
        listener.clickUpdateTiempo(tiempo);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (DialogUpdateTiempoFragment.DialogUpdateTiempo) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FormDialogListener");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        tiempo=listaTiempo.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public interface DialogUpdateTiempo{
        void clickUpdateTiempo(String tiempo);
    }


    private void listaTiempo() {

         listaTiempo= new ArrayList<>();
        listaTiempo.add("minutos");
        listaTiempo.add("dias");
        listaTiempo.add("semanas");

    }
*/

}
