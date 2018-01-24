package felipesistemas.com.app15;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main3Activity extends AppCompatActivity {

    private ListView lstProductos;


    private String TAG = Main3Activity.class.getSimpleName();
    private ProgressDialog progressDialog;

    private static String Jsonurl = "http://www.v2msoft.com/clientes/lasalle/curs-android/productos_supermercado.json";


    private Producto producto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        lstProductos  =  findViewById(R.id.lstProductos);

        producto  =  new Producto();

        new GetProducts().execute();

    }



    private class GetProducts extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(Main3Activity.this);
            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(true);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler httpHandler = new HttpHandler();


            String jsonString = httpHandler.makeServiceCall(Jsonurl);
            Log.e(TAG, "Response from url: " + jsonString);


            if (jsonString != null) {

                try {

                  /*  JSONObject jsonObject = new JSONObject(jsonString);

                    JSONArray productos = jsonObject.getJSONArray("productos");

                    for (int i = 0; i < productos.length(); i++) {

                        JSONObject c = productos.getJSONObject(i);

                        String id = c.getString("id");
                        String fabricante = c.getString("fabricante");
                        String nombre = c.getString("nombre");
                        String precio = c.getString("precio");
                        String stock = c.getString("stock");


                        HashMap<String, String> product = new HashMap<>();

                        product.put("id", id);
                        product.put("fabricante", fabricante);
                        product.put("nombre", nombre);
                        product.put("precio", precio);
                        product.put("stock", stock);


                        ProductosSupermercado.add(product);

                    }
                  */

                  Gson gson = new Gson();
                  producto = gson.fromJson(jsonString,Producto.class);

                }catch (final Exception e){

                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Could not get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Could not get json from server.",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (progressDialog.isShowing())
                progressDialog.dismiss();


            ListAdapter adapter = new SimpleAdapter(
                    Main3Activity.this,
                    producto.listaProductos,
                    R.layout.lista_item,
                    new String[]{"fabricante", "nombre",  "precio"},
                    new int[]{R.id.Fabricante, R.id.Nombre, R.id.Precio});

            lstProductos.setAdapter(adapter);

        }


    }
}
