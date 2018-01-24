package felipesistemas.com.app15;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;



public class Main2Activity extends AppCompatActivity {

    private ListView lstProductos;

    private ProgressDialog progressDialog;

    private static String Jsonurl =  "http://www.v2msoft.com/clientes/lasalle/curs-android/productos_supermercado.json";

    private Producto producto;

    private RequestQueue queue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        lstProductos  =  findViewById(R.id.lstProductos);

        producto  =  new Producto();

        queue = Volley.newRequestQueue(this);

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



        StringRequest stringRequest = new StringRequest(Request.Method.GET, Jsonurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String responseJson  = response;

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
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });


        queue.add(stringRequest);


    }

}
