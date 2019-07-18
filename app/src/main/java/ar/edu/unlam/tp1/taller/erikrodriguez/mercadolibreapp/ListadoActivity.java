package ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

    @BindView(R.id.banner)
    ImageView imagenBanner;

    final String URL ="https://static.websguru.com.ar/var/m_4/48/484/15418/1751312-banner_mercado_libre.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        ButterKnife.bind(this);
        Picasso.with(getApplicationContext()).load(URL).placeholder(R.drawable.progress_animation).into(imagenBanner);
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


    private void configurarRecyclerView(List<Producto> productos) {
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        adapter = new ProductosAdapter(productos);
        recyclerView.setAdapter(adapter);
    }


}