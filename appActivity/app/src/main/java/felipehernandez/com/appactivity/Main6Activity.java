package felipehernandez.com.appactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import modelo.Maestro;

public class Main6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);


        try {

            //Capturar parametros
            Bundle bundle = getIntent().getExtras();

            String obj  =  bundle.getString("obj");

            Gson gson = new Gson();

            Maestro maestro =  gson.fromJson(obj,Maestro.class);


            Toast.makeText(getApplicationContext(),maestro.nombre,Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),String.valueOf(maestro.edad),Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Log.i("Error en los parametros", "onCreate:  hubo un error: "+e.getMessage());
        }
    }
}
