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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {

    private ListView lstProductos;


    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog progressDialog;

    private static String Jsonurl = "http://www.v2msoft.com/clientes/lasalle/curs-android/productos_supermercado.json";

    ArrayList<HashMap<String, String>> ProductosSupermercado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lstProductos  =  findViewById(R.id.lstProductos);

        ProductosSupermercado = new ArrayList<>();

        new GetProducts().execute();

    }


    private class GetProducts extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(Main2Activity.this);
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

                    JSONObject jsonObject = new JSONObject(jsonString);

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



                }catch (final JSONException e){

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
                    Main2Activity.this,
                    ProductosSupermercado,
                     R.layout.lista_item,
                    new String[]{"fabricante", "nombre",  "precio"},
                    new int[]{R.id.Fabricante, R.id.Nombre, R.id.Precio});

            lstProductos.setAdapter(adapter);

        }


    }

}
