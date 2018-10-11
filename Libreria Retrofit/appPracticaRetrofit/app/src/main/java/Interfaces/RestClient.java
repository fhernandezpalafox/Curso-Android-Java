package Interfaces;

import java.util.List;

import Entidades.Articulo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestClient {

    @GET("articulos")
    Call<List<Articulo>> cargarArticulos();

}
