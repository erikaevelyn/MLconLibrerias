package ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultadoActivity extends AppCompatActivity {

    @BindView(R.id.resultado)
    TextView datoRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        ButterKnife.bind(this);

        String dato = getIntent().getStringExtra("datoBuscado");

        datoRecibido.setText(dato);
    }
}
