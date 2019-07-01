package ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.API.API;
import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.Modelos.Producto;
import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.Modelos.Resultado;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListadoActivity extends AppCompatActivity {

    @BindView(R.id.idDelElemento)
    TextView idPrimerProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        ButterKnife.bind(this);
        enviarId();
        Intent i = new Intent(this, ResultadoActivity.class);
        i.putExtra("idProducto", idPrimerProducto.getText().toString());
        startActivity(i);

    }

    public void enviarId() {
        String dato = getIntent().getStringExtra("datoBuscado");

        API.search(dato, new Callback<Resultado>() {


            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if (response.isSuccessful()) {
                    Resultado resultados = response.body();
                    idPrimerProducto.setText(resultados.getResultados().get(0).getId());

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

}