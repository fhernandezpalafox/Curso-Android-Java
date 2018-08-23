package felipehernandez.com.appactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import modelo.Alumno;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);


        try {

            //Capturar parametros
            Bundle bundle = getIntent().getExtras();

            Alumno alumno =  bundle.getParcelable("obj");

            Toast.makeText(getApplicationContext(),alumno.nombre,Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),String.valueOf(alumno.edad),Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Log.i("Error en los parametros", "onCreate:  hubo un error: "+e.getMessage());
        }
    }
}
