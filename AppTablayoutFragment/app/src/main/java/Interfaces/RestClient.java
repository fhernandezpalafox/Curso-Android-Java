package Interfaces;

import java.util.List;

import Entidades.Articulo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestClient {

    @GET("articulos")
    Call<List<Articulo>> cargarArticulos();

    @POST("articulos")
    Call<Articulo> insertarArticulo(@Body Articulo articulo);

    @PUT("articulos/{id}")
    Call<Articulo> modificarArticulo(@Body Articulo articulo, @Path("id") String id);

    @DELETE("articulos/{id}")
    Call<String> eliminarArticulo( @Path("id") String id);
}