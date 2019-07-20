package ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.Modelos;

import com.google.gson.annotations.SerializedName;

public class Localidad {

    @SerializedName("name")
    private String nombreLocalidad;

    public String getNombreLocalidad() {
        return nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }
}
