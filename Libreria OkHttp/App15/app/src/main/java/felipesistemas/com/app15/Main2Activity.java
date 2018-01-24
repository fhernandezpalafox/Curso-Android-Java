package felipesistemas.com.app15;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Main2Activity extends AppCompatActivity {

    private ListView lstProductos;

    private ProgressDialog progressDialog;

    private static String Jsonurl =  "http://www.v2msoft.com/clientes/lasalle/curs-android/productos_supermercado.json";

    private Producto producto;

    private OkHttpClient client = new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        lstProductos  =  findViewById(R.id.lstProductos);

        producto  =  new Producto();


        try {
            getProductos();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }


    public void getProductos() throws Exception {

        progressDialog = new ProgressDialog(Main2Activity.this);
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(true);
        progressDialog.show();


        Request request = new Request.Builder()
                .url(Jsonurl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    String responseJson  = responseBody.string();

                    Gson gson = new Gson();

                    producto = gson.fromJson(responseJson,Producto.class);


                    runOnUiThread(new Runnable() {
                        @Override public void run() {

                            ListAdapter adapter = new SimpleAdapter(
                                    Main2Activity.this,
                                    producto.listaProductos,
                                    R.layout.lista_item,
                                    new String[]{"fabricante", "nombre",  "precio"},
                                    new int[]{R.id.Fabricante, R.id.Nombre, R.id.Precio});

                            lstProductos.setAdapter(adapter);

                            //ProgressDialog se cierra
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    });


                }
            }
        });
    }

}
