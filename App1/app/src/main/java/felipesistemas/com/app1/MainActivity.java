package felipesistemas.com.app1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //declaracion de los objetos
    private TextView lblInformacion,lblBienvenidos;
    private Button btnAceptar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //linking o ligar nuestros obetos de java con .xml
        lblInformacion = findViewById(R.id.lblInformacion);
        btnAceptar = findViewById(R.id.btnHolaMundo);
        lblBienvenidos = findViewById(R.id.lblBienvenidos);


        //Evento del boton aceptar.
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              lblInformacion.setText("Hola , bienvenido al curso de Andriod");
            }
        });


    }



}
