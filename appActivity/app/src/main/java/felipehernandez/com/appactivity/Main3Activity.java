package felipehernandez.com.appactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        try {

            //Capturar parametros
            Bundle bundle = getIntent().getExtras();

            String nombre  =  bundle.getString("nombre");
            int edad = bundle.getInt("edad");


            Toast.makeText(getApplicationContext(),nombre,Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),String.valueOf(edad),Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Log.i("Error en los parametros", "onCreate:  hubo un error: "+e.getMessage());
        }




        Button btnSalir =  findViewById(R.id.btnSalir);


        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
