package ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.Modelos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Producto {

    private String id;

    private String title;


    @SerializedName("price")
    private Double precio;

    @SerializedName("sold_quantity")
    private Integer vendidos;

    @SerializedName("available_quantity")
    private Integer disponibles;

    @SerializedName("condition")
    private String condicion;

    @SerializedName("pictures")
    private List<Imagen> imagenes;

    @SerializedName("thumbnail")
    private String UrlImagenPortada;

    @SerializedName("accepts_mercadopago")
    private boolean mercadoPago;

    @SerializedName("shipping")
    private  Envio envio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Double getPrecio() {
        return precio;
    }

    public Integer getVendidos() {
        return vendidos;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setVendidos(Integer vendidos) {
        this.vendidos = vendidos;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Integer getDisponibles() {
        return disponibles;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setDisponibles(Integer disponibles) {
        this.disponibles = disponibles;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public boolean isMercadoPago() {
        return mercadoPago;
    }

    public void setMercadoPago(boolean mercadoPago) {
        this.mercadoPago = mercadoPago;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public String getUrlImagenPortada() {
        return UrlImagenPortada;
    }

    public void setUrlImagenPortada(String urlImagenPortada) {
        UrlImagenPortada = urlImagenPortada;
    }
}
