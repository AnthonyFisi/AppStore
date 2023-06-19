package com.mimiperla.empresayego.View.DrawerUI.dataEmpresa;

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

import com.mimiperla.empresayego.Proof.RxBus;
import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Empresa;
import com.mimiperla.empresayego.Repository.Modelo.EmpresaOficial;
import com.mimiperla.empresayego.ViewModel.EmpresaOficialViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DialogUpdateTelephoneFragment extends AppCompatDialogFragment {


    private EditText dialog_update_CELULAR;

  //  private DialogUpdateCelular listener;

    private EmpresaOficialViewModel viewModel;

    private Empresa mEmpresa;

    private String numeroCelular;

    private LinearLayout linearLayout_update_celular,linearlayout_progressbar,linearlayout_success;

    private CardView cardView_cerrar,cardView_update_celular;

    private ImageView ic_back;

    private TextView textView_numero_celular;

    private AlertDialog alertDialog;

    public static DialogUpdateTelephoneFragment newInstance(Empresa empresa){
        DialogUpdateTelephoneFragment fragment= new DialogUpdateTelephoneFragment();
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

        numeroCelular=mEmpresa.getTelefono_empresa();

        viewModel = ViewModelProviders.of(this).get(EmpresaOficialViewModel.class);
        viewModel.init();
        viewModel.getEmpresaOficialLiveData().observe(this, new Observer<EmpresaOficial>() {
            @Override
            public void onChanged(EmpresaOficial empresa) {
                linearlayout_progressbar.setVisibility(View.GONE);

                if(empresa !=null){
                    linearlayout_success.setVisibility(View.VISIBLE);
                    Empresa.sEmpresa.setTelefono_empresa(empresa.getTelefono_empresa());
                    textView_numero_celular.setText( Empresa.sEmpresa.getTelefono_empresa());
                    RxBus.getInstance().publishStep2Fragment(true);

                }else {
                    Toast.makeText(getContext(),"Presentamos problemas,volver a intentarlo !",Toast.LENGTH_LONG).show();
                    linearLayout_update_celular.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());


        View view=LayoutInflater.from(getContext()).inflate(R.layout.dialog_update_telefono,null);

        builder.setView(view);

        dialog_update_CELULAR=view.findViewById(R.id.dialog_update_CELULAR);

        dialog_update_CELULAR.setText(numeroCelular);

        declararWidgets(view);

        cardView_update_celular.setOnClickListener(v->{

            if(dialog_update_CELULAR.getText().toString().length()==7 ){

                viewModel.updateNumeroTelefono(mEmpresa.getIdempresa(),dialog_update_CELULAR.getText().toString());
                linearlayout_progressbar.setVisibility(View.VISIBLE);
                linearLayout_update_celular.setVisibility(View.GONE);
            }else {
                Toast.makeText(getContext(),"Ingresar numero correcto",Toast.LENGTH_LONG).show();
            }

        });

        ic_back.setOnClickListener(v->{
           // sendData();
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

        return builder.create();


    }


    private void declararWidgets(View view ){
        linearLayout_update_celular=view.findViewById(R.id.linearLayout_update_celular);
        linearlayout_progressbar=view.findViewById(R.id.linearlayout_progressbar);
        linearlayout_success=view.findViewById(R.id.linearlayout_success);

        cardView_cerrar=view.findViewById(R.id.cardView_cerrar);
        cardView_update_celular=view.findViewById(R.id.cardView_update_celular);

        ic_back=view.findViewById(R.id.ic_back);

        textView_numero_celular=view.findViewById(R.id.textView_numero_celular);


        dialog_update_CELULAR.setCursorVisible(true);
        dialog_update_CELULAR.setFocusable(true);

        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(dialog_update_CELULAR, InputMethodManager.SHOW_FORCED);
    }
/*
    private void sendData(){
        listener.clickUpdateCelular(dialog_update_CELULAR.getText().toString());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (DialogUpdateCelularFragment.DialogUpdateCelular) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FormDialogListener");
        }
    }

    public interface DialogUpdateCelular{
        void clickUpdateCelular(String numeroTelefono);
    }*/
}
