package ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.Modelos.Producto;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView
   (R.id.IngresarBusqueda) EditText elementoBuscado;
    //se comunica la parte logica con la grafica (se guarda en esta varibale)

    @BindView(R.id.banner)
    ImageView imagenBanner;

    @BindView(R.id.footer)
    ImageView footer;

    final String URL ="https://static.websguru.com.ar/var/m_4/48/484/15418/1751312-banner_mercado_libre.jpg";
    //URL de imagen



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Picasso.with(getApplicationContext()).load(URL).placeholder(R.drawable.progress_animation).into(imagenBanner);
        Picasso.with(getApplicationContext()).load(R.drawable.footer).placeholder(R.drawable.progress_animation).into(footer);
    }

    @OnClick(R.id.miBoton)
    public void Buscar(View v) {
        Intent i = new Intent(this, ListadoActivity.class);
        //creo un Intent pasando por parametro la clase actual (this) uy la clase a la que quiero ir

        i.putExtra("datoBuscado", elementoBuscado.getText().toString());
        //Al intent con el metodo putExtra para enviar datos
        //Por parametro le paso un nombre con el que lo voy a identificar en el otro Activity --> "datoBuscado"
        // Y le paso el dato que quiero enviar convertido a String --> elementoBuscado.getText().toString()

        startActivity(i);
        //Le damos inicio a la nueva activity, pasando por parametro el intent.
    }


}