package ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.API.API;
import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.API.IMercadoLibre;
import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.Modelos.Resultado;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultadoActivity extends AppCompatActivity {

    @BindView(R.id.resultado)
    TextView datoRecibido;

    @BindView(R.id.condicion)
    TextView condicion;

    @BindView(R.id.cantVendidos)
    TextView cantidadVendidos;

    @BindView(R.id.precio)
    TextView precio;

    ImageView imagenBanner;

    final String URL ="https://static.websguru.com.ar/var/m_4/48/484/15418/1751312-banner_mercado_libre.jpg";
    //URL de imagen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        ButterKnife.bind(this);
        imagenBanner = (ImageView) findViewById(R.id.banner);
        Picasso.with(getApplicationContext()).load(URL).placeholder(R.drawable.progress_animation).into(imagenBanner);
        mostrarResultado();

    }
        public void mostrarResultado(){
        String dato = getIntent().getStringExtra("datoBuscado");
        API.search(dato, new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {

                if(response.isSuccessful()) {
                    Resultado resultados = response.body();
                    datoRecibido.setText(resultados.getResultados().get(0).getTitle());
                    condicion.setText(resultados.getResultados().get(0).getCondicion());
                    cantidadVendidos.setText(resultados.getResultados().get(0).getVendidos().toString());
                    precio.setText(resultados.getResultados().get(0).getPrecio().toString());
                }else{
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
