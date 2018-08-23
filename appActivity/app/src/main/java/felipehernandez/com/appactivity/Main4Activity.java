package felipehernandez.com.appactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import modelo.Alumno;
import modelo.Persona;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        try {

            //Capturar parametros
            Bundle bundle = getIntent().getExtras();

            Persona persona  = (Persona) bundle.getSerializable("obj");

            Toast.makeText(getApplicationContext(),persona.nombre,Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),String.valueOf(persona.edad),Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Log.i("Error en los parametros", "onCreate:  hubo un error: "+e.getMessage());
        }

    }
}
