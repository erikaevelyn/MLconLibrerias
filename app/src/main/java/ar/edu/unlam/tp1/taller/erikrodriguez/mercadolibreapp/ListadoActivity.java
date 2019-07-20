package ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

    @BindView(R.id.loQueSeBusco)
    TextView loQueSeBusco;

    @BindView(R.id.errorBusqueda)
    ImageView errorBusqueda;

    @BindView(R.id.loading)
    ProgressBar loading;

    @BindView(R.id.productoerror)
    TextView productoerror;


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
        loQueSeBusco.setText("Resultados de la busqueda: " + dato);

        API.search(dato, new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {

                        if (response.isSuccessful()) {
                            productoerror.setVisibility(View.GONE);
                            loading.setVisibility(View.GONE);

                            Resultado resultados = response.body();
                            productos = resultados.getResultados();

                            if (productos.size() == 0) {
                                errorBusqueda.setVisibility(View.VISIBLE);
                            } else {
                                errorBusqueda.setVisibility(View.GONE);
                                configurarRecyclerView(productos);
                            }

                            Picasso.with(getApplicationContext()).load(R.drawable.errorbusqueda).placeholder(R.drawable.progress_animation).into(errorBusqueda);


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
            public void onFailure(Call<Resultado> call, Throwable t) {
                productoerror.setVisibility(View.VISIBLE);
                loading.setVisibility(View.VISIBLE);
                productoerror.setText("Asegúrate de que tu dispositivo está conectado a la red y de que la fecha y hora están configuradas correctamente.");
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