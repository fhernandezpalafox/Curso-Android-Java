package Interfaces;

import java.util.List;

import Entidades.Productos;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by FelipeHernandez on 29/12/17.
 */

public interface GetAPI {

    @GET("products")
    Call<List<Productos>> cargarProductos();//@Query("q") String status

}
