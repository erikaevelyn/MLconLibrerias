package ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.API.API;
import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.API.IMercadoLibre;
import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.Modelos.Producto;
import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.Modelos.Resultado;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultadoActivity extends AppCompatActivity {

    @BindView(R.id.resultado)
    TextView titulo;

    @BindView(R.id.condicion)
    TextView condicion;

    @BindView(R.id.cantVendidos)
    TextView cantidadVendidos;

    @BindView(R.id.precio)
    TextView precio;

    @BindView(R.id.banner)
    ImageView imagenBanner;

    final String URLbanner ="https://static.websguru.com.ar/var/m_4/48/484/15418/1751312-banner_mercado_libre.jpg";

    String URLimagen1;

    @BindView(R.id.foto)
    ImageView imagen1;

    @BindView(R.id.mercadoPago)
    ImageView mercadoPago;

    @BindView(R.id.envio)
    TextView envio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        ButterKnife.bind(this);
        Picasso.with(getApplicationContext()).load(URLbanner).placeholder(R.drawable.progress_animation).into(imagenBanner);
        mostrarResultado();

    }


    public void mostrarResultado(){
        String idProductoElegido = getIntent().getStringExtra("idProducto");
        API.getArticle(idProductoElegido, new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {

                if(response.isSuccessful()) {
                    Producto producto = response.body();
                    URLimagen1 = producto.getImagenes().get(0).getUrl();
                    Picasso.with(getApplicationContext()).load(URLimagen1).placeholder(R.drawable.progress_animation).into(imagen1);
                    titulo.setText(producto.getTitle());

                    if(producto.getCondicion().equals("new")) {
                        condicion.setText("Articulo Nuevo  |");
                    }else {
                        condicion.setText("Articulo Usado  |");
                    }

                    cantidadVendidos.setText(producto.getVendidos().toString() + " vendidos");

                    NumberFormat formatoImporte = NumberFormat.getCurrencyInstance();
                    formatoImporte = NumberFormat.getCurrencyInstance(new Locale("es","AR"));
                    precio.setText(formatoImporte.format(producto.getPrecio()));

                    if(producto.isMercadoPago()){
                        mercadoPago.setVisibility(View.VISIBLE);
                    }else{
                        mercadoPago.setVisibility(View.GONE);
                    }
                    Picasso.with(getApplicationContext()).load(R.drawable.mercadopago).placeholder(R.drawable.progress_animation).into(mercadoPago);

                    if(producto.getEnvio().isEnvioGratis()){
                        envio.setText("Envio Gratis");
                        envio.setTextColor(Color.parseColor("#00eb63"));
                        envio.setTextSize(25);
                    }else{
                        envio.setText("Envio por $179,50");
                    }
                }else{
                    Log.d("Error", String.valueOf(response.code()) + response.message());
                }

            }


                    @Override
            public void onFailure(Call<Producto> call, Throwable t) {

                Log.e("Error", t.getMessage());

            }
        });


    }
}
