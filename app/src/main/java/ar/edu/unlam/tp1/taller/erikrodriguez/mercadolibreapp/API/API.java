package ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.API;

import com.google.gson.Gson;

import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.Modelos.Producto;
import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.Modelos.Resultado;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    private static IMercadoLibre getAPI() {
        Retrofit retrofit = new Retrofit.Builder()

                .addConverterFactory(GsonConverterFactory.create( new Gson() ))
                .baseUrl("https://api.mercadolibre.com/")
                .build();

        IMercadoLibre service = retrofit.create(IMercadoLibre.class);
        return service;
    }
    public static void getArticle(String id, Callback<Producto> callback) {

        getAPI().getArticle(id).enqueue(callback);
    }

    public static void search(String dato, Callback<Resultado> callback) {

        getAPI().search(dato).enqueue(callback);
    }
}
