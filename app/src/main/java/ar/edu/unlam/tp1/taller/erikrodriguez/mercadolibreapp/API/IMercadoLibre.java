package ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.API;

import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.Modelos.Producto;
import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.Modelos.Resultado;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IMercadoLibre {

    @GET("items/{itemId}")
    Call<Producto> getArticle(@Path("itemId") String id);

    @GET("sites/MLA/search")
    Call<Resultado> search(@Query("q") String query);
}
