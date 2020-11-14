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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.empresayego.Login.Modelo.Empresa_bi;
import com.example.empresayego.Proof.RxBus;
import com.example.empresayego.R;
import com.example.empresayego.Repository.Modelo.Empresa;
import com.example.empresayego.Repository.Modelo.EmpresaOficial;
import com.example.empresayego.ViewModel.EmpresaOficialViewModel;
import com.example.empresayego.ViewModel.EmpresaViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class DialogUpdateDescripcionFragment extends AppCompatDialogFragment {

    private EditText dialog_update_DESCRIPCION;

   // private DialogUpdateDescripcion listener;

    private EmpresaOficialViewModel viewModel;

    private Empresa mEmpresaResponse;

    private Empresa mEmpresa;

    private String descripcionDefault;

    private AlertDialog alertDialog;

    private LinearLayout linearLayout_update_descripcion,linearlayout_progressbar,linearlayout_success;

    private CardView cardView_cerrar,cardView_update_descripcion;

    private ImageView ic_back;

    private TextView textView_descripcion;

    public static DialogUpdateDescripcionFragment newInstance(Empresa empresa){
        DialogUpdateDescripcionFragment fragment= new DialogUpdateDescripcionFragment();
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

        descripcionDefault=mEmpresa.getDescripcion_empresa();

        System.out.println(descripcionDefault + " DESCRIPCION DE LA EMPRESA");

        viewModel =new ViewModelProvider(this).get(EmpresaOficialViewModel.class);
        viewModel.init();
        viewModel.getEmpresaOficialLiveData().observe(this, new Observer<EmpresaOficial>() {
            @Override
            public void onChanged(EmpresaOficial empresa) {

                linearlayout_progressbar.setVisibility(View.GONE);

                if(empresa !=null){

                    linearlayout_success.setVisibility(View.VISIBLE);
                    Empresa.sEmpresa.setDescripcion_empresa(empresa.getDescripcion_empresa());
                    textView_descripcion.setText( Empresa.sEmpresa.getDescripcion_empresa());
                    RxBus.getInstance().publishStep2Fragment(true);

                }else {
                    Toast.makeText(getContext(),"Presentamos problemas,volver a intentarlo !",Toast.LENGTH_LONG).show();
                    linearLayout_update_descripcion.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());


        View view=LayoutInflater.from(getContext()).inflate(R.layout.dialog_update_descripcion,null);

        builder.setView(view);

        dialog_update_DESCRIPCION=view.findViewById(R.id.dialog_update_DESCRIPCION);

        dialog_update_DESCRIPCION.setText(descripcionDefault);



        declararWidgets(view);

        cardView_update_descripcion.setOnClickListener(v->{

            if(dialog_update_DESCRIPCION.getText().toString().length()>1){

                EmpresaOficial empresaOficial=new EmpresaOficial();
                empresaOficial.setDescripcion_empresa(dialog_update_DESCRIPCION.getText().toString());
                empresaOficial.setIdempresa(Empresa.sEmpresa.getIdempresa());
                viewModel.updateDescripcion(empresaOficial);
                linearlayout_progressbar.setVisibility(View.VISIBLE);
                linearLayout_update_descripcion.setVisibility(View.GONE);
            }else {
                Toast.makeText(getContext(),"Ingresar una breve descripcion",Toast.LENGTH_LONG).show();
            }

        });

        ic_back.setOnClickListener(v->{
           // sendData(dialog_update_DESCRIPCION.getText().toString());
            dismiss();
        });

        cardView_cerrar.setOnClickListener(v->{
            dismiss();
        });

        alertDialog = builder.create();
        //asegura que se muestre el teclado
        alertDialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return alertDialog;


    }


    private void declararWidgets(View view ){
        linearLayout_update_descripcion=view.findViewById(R.id.linearLayout_update_descripcion);
        linearlayout_progressbar=view.findViewById(R.id.linearlayout_progressbar);
        linearlayout_success=view.findViewById(R.id.linearlayout_success);

        cardView_cerrar=view.findViewById(R.id.cardView_cerrar);
        cardView_update_descripcion=view.findViewById(R.id.cardView_update_descripcion);

        ic_back=view.findViewById(R.id.ic_back);

        textView_descripcion=view.findViewById(R.id.textView_descripcion);

        dialog_update_DESCRIPCION.setCursorVisible(true);
        dialog_update_DESCRIPCION.setFocusable(true);

        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(dialog_update_DESCRIPCION, InputMethodManager.SHOW_FORCED);
    }



/*
    private void sendData(String descripcion){
        listener.clickUpdateDescripcion(descripcion);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (DialogUpdateDescripcionFragment.DialogUpdateDescripcion) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FormDialogListener");
        }
    }

    public interface DialogUpdateDescripcion{
        void clickUpdateDescripcion(String descripcion);
    }*/
}
