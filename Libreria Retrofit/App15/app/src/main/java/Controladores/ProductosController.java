package Controladores;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import Entidades.Productos;
import Interfaces.GetAPI;
import felipesistemas.com.app15.Main2Activity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



/**
 * Created by FelipeHernandez on 29/12/17.
 */

public class ProductosController
        implements Callback<List<Productos>> {

    static final String BASE_URL = "http://frosty-thunder-508.getsandbox.com/rest/1_0/";

    private Main2Activity main2Activity;



    public void start(Main2Activity main2Activityobj) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GetAPI getAPI = retrofit.create(GetAPI.class);

        Call<List<Productos>> call = getAPI.cargarProductos();
        call.enqueue(this);

        main2Activity = main2Activityobj;


    }

    @Override
    public void onResponse(Call<List<Productos>> call, Response<List<Productos>> response) {

        if(response.isSuccessful()) {
            List<Productos> listaProductos = response.body();


            try {
                main2Activity.getProductos(listaProductos);
            } catch (Exception e) {
                e.printStackTrace();
            }
           /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                listaProductos.forEach(change -> System.out.println(change.getName()));
            }else
            {
                for (Productos p : listaProductos) {

                }
            }*/
        } else {
            System.out.println(response.errorBody());
        }

    }

    @Override
    public void onFailure(Call<List<Productos>> call, Throwable t) {
        t.printStackTrace();
    }

}
