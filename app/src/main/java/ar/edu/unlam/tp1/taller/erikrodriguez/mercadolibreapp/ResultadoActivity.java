package ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultadoActivity extends AppCompatActivity {

    @BindView(R.id.resultado)
    TextView datoRecibido;

    ImageView imagenBanner;

    final String URL ="https://static.websguru.com.ar/var/m_4/48/484/15418/1751312-banner_mercado_libre.jpg";
    //URL de imagen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        ButterKnife.bind(this);
        imagenBanner = (ImageView)findViewById(R.id.banner);
        Picasso.with(getApplicationContext()).load(URL).placeholder(R.drawable.progress_animation).into(imagenBanner);

        String dato = getIntent().getStringExtra("datoBuscado");

        datoRecibido.setText(dato);
    }
}
