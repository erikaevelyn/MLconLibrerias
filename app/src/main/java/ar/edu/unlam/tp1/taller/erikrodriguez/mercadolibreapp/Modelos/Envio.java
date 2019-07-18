package ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.Modelos;

import com.google.gson.annotations.SerializedName;

public class Envio {

    @SerializedName("free_shipping")
    private boolean EnvioGratis;

    public boolean isEnvioGratis() {
        return EnvioGratis;
    }

    public void setEnvioGratis(boolean envioGratis) {
        EnvioGratis = envioGratis;
    }
}
