package felipesistemas.com.app15;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Controladores.ProductosController;
import Entidades.Productos;


public class Main2Activity extends AppCompatActivity {

    private ListView lstProductos;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        lstProductos  =  findViewById(R.id.lstProductos);

        ProductosController productosController =  new ProductosController();
        productosController.start(this);



    }


    public void getProductos(List<Productos> listaProductos) throws Exception {

        progressDialog = new ProgressDialog(Main2Activity.this);
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(true);
        progressDialog.show();


        ArrayList<HashMap<String, String>> products  = new ArrayList<>();
        HashMap<String, String> n = new HashMap<>();

        for (Productos p : listaProductos) {

            n.put("id", String.valueOf(p.getId()));
            n.put("name",p.getName());
            n.put("description",p.getDescription());
            n.put("eanCode",p.getEanCode());

            products.add(n);
        }

        runOnUiThread(new Runnable() {
            @Override public void run() {

                ListAdapter adapter = new SimpleAdapter(
                        Main2Activity.this,
                        products,
                        R.layout.lista_item,
                        new String[]{"id", "name",  "description"},
                        new int[]{R.id.Fabricante, R.id.Nombre, R.id.Precio});

                lstProductos.setAdapter(adapter);

                //ProgressDialog se cierra
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });

    }

}
