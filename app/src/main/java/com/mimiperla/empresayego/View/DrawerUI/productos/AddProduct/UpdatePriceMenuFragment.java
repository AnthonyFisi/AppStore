package com.mimiperla.empresayego.View.DrawerUI.productos.AddProduct;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Empresa;
import com.mimiperla.empresayego.ViewModel.EmpresaViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdatePriceMenuFragment extends BottomSheetDialogFragment {

    public static final String TAG = "tag";
    private OnFragmentInteractionListener mListener;

    private EmpresaViewModel viewModel;

    private TextView new_price,price_VALUE,price1_VALUE,text_update_price;

    private CardView cerrar_increment_price,cardview_update_price;

    private ImageButton price_menu_INCREMENT,price_menu_DISMISS,price1_menu_INCREMENT,price1_menu_DISMISS;

    private int price,price1;

    private ProgressBar progres_update_price;

    private float precioUpdate;

    private LinearLayout linearLayout_INCREMENT_PRICE,linearlayout_ACTUALIZAR_OK;

    private ImageView imageView;

    public UpdatePriceMenuFragment() {
        // Required empty public constructor
    }

    public static UpdatePriceMenuFragment newInstance(){
        return new UpdatePriceMenuFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel=new ViewModelProvider(this).get(EmpresaViewModel.class);
        viewModel.init();
        viewModel.getEmpresaLiveData().observe(this, new Observer<Empresa>() {
            @Override
            public void onChanged(Empresa empresa) {

                progres_update_price.setVisibility(View.GONE);

                if(empresa!=null){
                    linearLayout_INCREMENT_PRICE.setVisibility(View.GONE);
                    linearlayout_ACTUALIZAR_OK.setVisibility(View.VISIBLE);
                    new_price.setText(String.valueOf(precioUpdate));
                    mListener.stateUpdatePrice(true,precioUpdate);
                }else {
                    Toast.makeText(getContext(), "Vuelva a intentar actualizar precio", Toast.LENGTH_SHORT).show();
                    text_update_price.setVisibility(View.VISIBLE);

                    mListener.stateUpdatePrice(false,precioUpdate);

                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_price_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        declararWidget(view);
        setDataWidget();
        incrementPrice();
        clickUpdatePrice();
        closeBottomSheetFragment();

        imageView.setOnClickListener(v->{
            dismiss();
        });

    }

    private void declararWidget(View view){
        new_price=view.findViewById(R.id.new_price);
        cerrar_increment_price=view.findViewById(R.id.cerrar_increment_price);

        price_menu_INCREMENT=view.findViewById(R.id.price_menu_INCREMENT);
        price_menu_DISMISS=view.findViewById(R.id.price_menu_DISMISS);
        price1_menu_INCREMENT=view.findViewById(R.id.price1_menu_INCREMENT);
        price1_menu_DISMISS=view.findViewById(R.id.price1_menu_DISMISS);

        price_VALUE=view.findViewById(R.id.price_VALUE);
        price1_VALUE=view.findViewById(R.id.price1_VALUE);

        text_update_price=view.findViewById(R.id.text_update_price);

        progres_update_price=view.findViewById(R.id.progres_update_price);

        cardview_update_price=view.findViewById(R.id.cardview_update_price);

        linearlayout_ACTUALIZAR_OK=view.findViewById(R.id.linearlayout_ACTUALIZAR_OK);

        linearLayout_INCREMENT_PRICE=view.findViewById(R.id.linearLayout_INCREMENT_PRICE);

        imageView=view.findViewById(R.id.imageView);
    }
    private void setDataWidget(){

        price= 0;
        price1= 0;
        price_VALUE.setText(String.valueOf(price));

        price1_VALUE.setText(String.valueOf(price1));

    }

    private void incrementPrice(){

        price_menu_INCREMENT.setOnClickListener(v->{
            price++;
            price_VALUE.setText(String.valueOf(price));

        });

        price_menu_DISMISS.setOnClickListener(v->{
            if(price<=0){
                Toast.makeText(getContext(), "El limite fue alcanzado", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(), "El limite fue alcanzado", Toast.LENGTH_SHORT).show();
            }

        });

        price1_menu_DISMISS.setOnClickListener(v->{
            if(10 <= price1) {

                price1 -= 10;
                price1_VALUE.setText(String.valueOf(price1));

            }else{
                Toast.makeText(getContext(), "El limite fue alcanzado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void clickUpdatePrice(){
        cardview_update_price.setOnClickListener(v->{

            String fisrt_part="0."+price1_VALUE.getText().toString();

            String second_part=price_VALUE.getText().toString();

            precioUpdate=Float.valueOf(second_part)+Float.valueOf(fisrt_part);


            Toast.makeText(getContext(), "price"+precioUpdate, Toast.LENGTH_SHORT).show();

            viewModel.updatePrecioMenu(Empresa.sEmpresa.getIdempresa(),precioUpdate);

            text_update_price.setVisibility(View.GONE);

            progres_update_price.setVisibility(View.VISIBLE);


        });
    }

    private void closeBottomSheetFragment(){
        cerrar_increment_price.setOnClickListener(v->{
            dismiss();
        });
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void stateUpdatePrice(boolean respuesta,float newPrice);
    }

}
