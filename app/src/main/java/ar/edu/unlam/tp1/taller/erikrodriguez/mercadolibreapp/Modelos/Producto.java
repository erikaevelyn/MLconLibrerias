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
    private List<Pictures> imagenes;

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

    public List<Pictures> getImagenes() {
        return imagenes;
    }

    public void setDisponibles(Integer disponibles) {
        this.disponibles = disponibles;
    }

    public void setImagenes(List<Pictures> imagenes) {
        this.imagenes = imagenes;
    }
}
