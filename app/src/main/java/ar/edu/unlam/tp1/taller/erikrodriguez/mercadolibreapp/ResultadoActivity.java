package ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.API.API;
import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.API.IMercadoLibre;
import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.Modelos.Localidad;
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

    @BindView(R.id.logoUbicacion)
    ImageView logoUbicacion;

    @BindView(R.id.envio)
    TextView envio;

    @BindView(R.id.ubicacion)
    TextView ubicacion;

    @BindView(R.id.cantidadFotos)
    TextView cantidadFotos;

    @BindView(R.id.loading)
    ProgressBar loading;

    @BindView(R.id.productoerror)
    TextView productoerror;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        ButterKnife.bind(this);
        Picasso.with(getApplicationContext()).load(URLbanner).placeholder(R.drawable.progress_animation).into(imagenBanner);
        Picasso.with(getApplicationContext()).load(R.drawable.logoubicacion).placeholder(R.drawable.progress_animation).into(logoUbicacion);
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

                    ubicacion.setText(producto.getDireccionVenta().getLocalidad().getNombreLocalidad() + " - " + producto.getDireccionVenta().getPartido().getNombrePartido());
                    cantidadFotos.setText(String.valueOf(producto.getImagenes().size()) + " Fotos");
                } else {
                    productoerror.setVisibility(View.VISIBLE);
                    loading.setVisibility(View.VISIBLE);
                    switch (response.code()){
                        case 401:
                            productoerror.setText("Unauthorized4: La autentificación es posible pero ha fallado o aún no ha sido provista.");
                            break;
                        case 404:
                            productoerror.setText("404 Not Found: Recurso no encontrado.");
                            break;
                        case 405:
                            productoerror.setText("405 Method Not Allowed:Una petición fue hecha a una URI utilizando un método de solicitud no soportado por dicha URI.");
                            break;
                        case 500:
                            productoerror.setText("500 Internal Server Error.");
                            break;
                        case 501:
                            productoerror.setText("501 Not Implemented: El servidor no soporta una funcionalidad necesaria para responder a la solicitud del navegador.");
                            break;
                        case 503:
                            productoerror.setText("503 Service Unavailable: El servidor no puede responder a la petición del navegador porque está congestionado o está realizando tareas de mantenimiento.");
                            break;
                        case 504:
                            productoerror.setText("504 Gateway Timeout: El servidor está actuando de proxy o gateway y no ha recibido a tiempo una respuesta del otro servidor, por lo que no puede responder adecuadamente a la petición del navegador.");
                            break;
                        case 505:
                            productoerror.setText("505 HTTP Version Not Supported: El servidor no soporta la versión del protocolo HTTP utilizada en la petición del navegador.");
                            break;
                        default:
                            productoerror.setText("Se produjo un error.");
                            break;
                    }
                }


            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                productoerror.setVisibility(View.VISIBLE);
                loading.setVisibility(View.VISIBLE);
                productoerror.setText("Asegúrate de que tu dispositivo está conectado a la red y de que la fecha y hora están configuradas correctamente.");
            }
        });


    }
}
