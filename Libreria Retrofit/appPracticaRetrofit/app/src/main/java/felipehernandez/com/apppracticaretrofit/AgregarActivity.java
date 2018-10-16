package felipehernandez.com.apppracticaretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.gson.Gson;

import Controladores.ArticulosController;
import Entidades.Articulo;

public class AgregarActivity extends AppCompatActivity {

    private EditText txtNombre, txtDescripcion, txtCantidad;

    private boolean isModified = false;

    private String idEdit;

    private Articulo objArtEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        ToolbarInicializador();

        Inicializar();

        Eventos();


        //putExtra

        Intent intent =  getIntent();

        isModified = intent.getBooleanExtra("ismodified",false);
        if (isModified)
        {
            objArtEdit = new Articulo();
            Gson objGson = new Gson();
            objArtEdit  =  objGson.fromJson(intent.getStringExtra("clase"),Articulo.class);

            txtNombre.setText(objArtEdit.getNombre());
            txtCantidad.setText(String.valueOf(objArtEdit.getCantidad()));
            txtDescripcion.setText(objArtEdit.getDescripcion());
            idEdit = objArtEdit.getId();
        }


    }



    public void  Inicializar(){

        txtNombre  =  findViewById(R.id.txtNombre);
        txtCantidad  =  findViewById(R.id.txtCantidad);
        txtDescripcion =  findViewById(R.id.txtDescripcion);

    }

    public void Eventos(){

    }

    public void  ToolbarInicializador(){

        Toolbar toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Agregar Articulo");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }else if(item.getItemId() == R.id.mnuGuardar){

            //Logica con Articulos Controller

            ArticulosController articulosController =  new ArticulosController();

            Articulo articulo =  new Articulo();
            articulo.setNombre(txtNombre.getText().toString());
            articulo.setDescripcion(txtDescripcion.getText().toString());
            articulo.setCantidad(Integer.parseInt(txtCantidad.getText().toString()));


            if(isModified){
                objArtEdit.setCantidad(Integer.parseInt(txtCantidad.getText().toString()));
                objArtEdit.setDescripcion(txtDescripcion.getText().toString());
                objArtEdit.setNombre(txtNombre.getText().toString());
                articulosController.modificarArticulo(objArtEdit,this);
            }else {
                articulosController.agregarArticulo(articulo, this);
            }


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agregar, menu);
        return true;
    }
}
