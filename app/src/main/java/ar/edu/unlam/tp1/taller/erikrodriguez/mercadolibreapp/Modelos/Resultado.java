package ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.Modelos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Resultado {

    @SerializedName("results")
    private List<Producto> resultados;

    public List<Producto> getResultados() {
        return resultados;
    }

    public void setResultados(List<Producto> resultados) {
        this.resultados = resultados;
    }
}
