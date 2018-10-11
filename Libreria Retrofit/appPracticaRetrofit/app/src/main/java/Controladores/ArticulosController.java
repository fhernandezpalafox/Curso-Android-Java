package Controladores;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import Adaptadores.ArticulosAdapter;
import Entidades.Articulo;
import Interfaces.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.Arrays.stream;

public class ArticulosController {

    private List<Articulo> listaArticulos;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView recyclerViewArticulos2;

    private ProgressDialog progressDialog;

    private Context context;

    private String queryBusqueda = "";
    private List<Articulo> listaArticulosBusqueda;

    public void cargarArticulos(final RecyclerView recyclerViewArticulos, Context context, String queryBusqueda) {

        context = context;

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(true);
        progressDialog.show();

        recyclerViewArticulos2 = recyclerViewArticulos;

        listaArticulos = new ArrayList<>();

        listaArticulosBusqueda  =  new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.18.7.25:2403/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RestClient restClient = retrofit.create(RestClient.class);

        Call<List<Articulo>> call = restClient.cargarArticulos();

        call.enqueue(new Callback<List<Articulo>>() {
            @Override
            public void onResponse(Call<List<Articulo>> call, Response<List<Articulo>> response) {

                switch (response.code()) {
                    case 200:
                        listaArticulos = response.body();
                        //view.notifyDataSetChanged(data.getResults());
                        // especifique un adaptador con la lista para mostrar

                       // if (queryBusqueda.equals("")==true){

                          /*  List<String> contactNames =
                                    stream(listaArticulos)
                                            .select(c -> c.getName())
                                            .toList();*/

                      //  }


                        mAdapter = new ArticulosAdapter(listaArticulos);
                        recyclerViewArticulos.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();

                        if (progressDialog.isShowing())
                            progressDialog.dismiss();

                        break;
                    case 401:

                        break;
                    default:

                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Articulo>> call, Throwable t) {
                System.out.println(call);
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });

    }

}
