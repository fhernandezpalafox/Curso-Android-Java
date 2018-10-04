package felipehernandex.com.componentes;


import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;

public class Main4Activity extends AppCompatActivity {

    public int Imagen;
    public String nombre;

    public int getImagen() {
        return Imagen;
    }

    public void setImagen(int imagen) {
        Imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        //funcionamiento Toolbar
        setToolbar(); //añadir action bar
        if (getSupportActionBar() != null){ //habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Hacer traslucido la parte superior
        setStatusBarTranslucent(true);


        if(getIntent().getExtras() != null){

            setNombre(getIntent().getExtras().getString("nombre"));
            setImagen(getIntent().getExtras().getInt("imagen"));
        }

        CollapsingToolbarLayout ctlLayout =  (CollapsingToolbarLayout) findViewById(R.id.ctlLayout);
        ctlLayout.setTitle(getNombre());

        ImageView imgToolbar =  (ImageView) findViewById(R.id.imgToolbar);
        imgToolbar.setImageResource(getImagen());

        //BARRA DE NAVEGACION TRASLUCIDA
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void setToolbar(){
        Toolbar toolbar =  (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    private void showSnackBar(String msg) {
        Snackbar
                .make(findViewById(R.id.coordinatorLayout), msg, Snackbar.LENGTH_LONG)
                .show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.btnAtras:
                showSnackBar("Se presiono el boton de acerca");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
