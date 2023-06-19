package com.mimiperla.empresayego.View.DrawerUI.historial;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mimiperla.empresayego.R;
import com.mimiperla.empresayego.Repository.Modelo.Empresa;
import com.mimiperla.empresayego.Repository.Modelo.Empresa_historial;
import com.mimiperla.empresayego.Repository.Modelo.Gson.Empresa_historialGson;
import com.mimiperla.empresayego.View.DrawerUI.historial.DetalleHistorial.OrderReadyHistorialActivity;
import com.mimiperla.empresayego.ViewModel.Empresa_historialViewModel;

import java.sql.Timestamp;
import java.util.List;

public class HistorialFragment extends Fragment implements Historial_PedidoResultsAdapter.ClickPedidoReciente {

    private Empresa_historialViewModel viewModel;
    private Historial_PedidoResultsAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayout linearlayout_EMPTY_HISTORIAL,linearLayout_RESULT_HISTORIAL;
    private TextView venta_hoy, venta_total;
    private ProgressBar progres_historial;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new Historial_PedidoResultsAdapter();
        viewModel = new ViewModelProvider(this).get(Empresa_historialViewModel.class);
        viewModel.init();
        viewModel.getEmpresa_historialGsonLiveData().observe(this, new Observer<Empresa_historialGson>() {
            @Override
            public void onChanged(Empresa_historialGson empresa_historialGson) {
                progres_historial.setVisibility(View.GONE);
                if (empresa_historialGson != null) {

                    adapter.setResults(empresa_historialGson.getListaEmpresaHistorial(), HistorialFragment.this);
                    linearLayout_RESULT_HISTORIAL.setVisibility(View.VISIBLE);

                    linearlayout_EMPTY_HISTORIAL.setVisibility(View.GONE);

                    calculateVentaTotal(empresa_historialGson.getListaEmpresaHistorial());
                } else {
                    linearLayout_RESULT_HISTORIAL.setVisibility(View.GONE);
                    linearlayout_EMPTY_HISTORIAL.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_historial, container, false);

        searchOrder(view);

        venta_hoy = view.findViewById(R.id.venta_hoy);
        venta_total = view.findViewById(R.id.venta_total);
        linearlayout_EMPTY_HISTORIAL = view.findViewById(R.id.linearlayout_EMPTY_HISTORIAL);
        //imagen.setVisibility(View.GONE);
        progres_historial=view.findViewById(R.id.progres_historial);
        progres_historial.setVisibility(View.VISIBLE);
        recyclerView = view.findViewById(R.id.fragment_historial_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        linearLayout_RESULT_HISTORIAL=view.findViewById(R.id.linearLayout_RESULT_HISTORIAL);
        linearLayout_RESULT_HISTORIAL.setVisibility(View.GONE);
        linearlayout_EMPTY_HISTORIAL.setVisibility(View.GONE);
        viewModel.searchEmpresaHistorialById(Empresa.sEmpresa.getIdempresa());

        return view;
    }

    @Override
    public void clickPedido(Empresa_historial restaurante_pedido, int posisiton) {

        Intent intent = OrderReadyHistorialActivity.newIntentOrderReadyHistorial(getContext(), restaurante_pedido);
        startActivity(intent);

    }

    private void searchOrder(View view) {

        // Associate searchable configuration with the SearchView
        //SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = view.findViewById(R.id.fragment_historial_searchview);
        // .getActionView();
        // searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapter.getFilter().filter(query);
                return false;
            }
        });
    }

    private void calculateVentaTotal(List<Empresa_historial> list) {

        float total_hoy = 0, total_general = 0;

        for (Empresa_historial pedido : list) {

            Timestamp time = new Timestamp(System.currentTimeMillis());

            Timestamp timeYesterday = new Timestamp(time.getTime() - (60000) * 60 * 24);

            Timestamp timeTomorrow = new Timestamp(time.getTime() + (60000) * 60 * 24);

            if (pedido.getFechahistorial().after(timeYesterday) && pedido.getFechahistorial().before(timeTomorrow)) {
                total_hoy += pedido.getVenta_costototal();
            }

            total_general += pedido.getVenta_costototal();

        }

        String total = "S/ " + total_general;

        String hoy = "S/ " + total_hoy;

        venta_total.setText(total);

        venta_hoy.setText(hoy);
    }

}