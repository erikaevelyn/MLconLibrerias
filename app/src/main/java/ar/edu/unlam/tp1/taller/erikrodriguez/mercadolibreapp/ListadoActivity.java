package ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.API.API;
import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.Modelos.Producto;
import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.Modelos.Resultado;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListadoActivity extends AppCompatActivity {

    TextView idPrimerProducto;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private ProductosAdapter adapter;

    List<Producto> productos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        ButterKnife.bind(this);
      /*  enviarId();*/
        listarProductos();
    }

    public void listarProductos() {
        String dato = getIntent().getStringExtra("datoBuscado");

        API.search(dato, new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if (response.isSuccessful()) {
                    Resultado resultados = response.body();
                    productos = resultados.getResultados();
                    configurarRecyclerView(productos);
                } else {
                    Log.d("Error", String.valueOf(response.code()) + response.message());
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }



    public void enviarId() {
        String dato = getIntent().getStringExtra("datoBuscado");

        API.search(dato, new Callback<Resultado>() {


            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if (response.isSuccessful()) {
                    Resultado resultados = response.body();
                    idPrimerProducto.setText(resultados.getResultados().get(0).getId());
                    Intent i = new Intent(ListadoActivity.this, ResultadoActivity.class);
                    i.putExtra("idProducto", idPrimerProducto.getText().toString());
                    startActivity(i);

                } else {
                    Log.d("Error", String.valueOf(response.code()) + response.message());
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }


        });

    }

    private void configurarRecyclerView(List<Producto> productos) {
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        adapter = new ProductosAdapter(productos);
        recyclerView.setAdapter(adapter);
    }


}