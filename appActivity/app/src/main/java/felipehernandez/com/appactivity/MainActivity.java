package felipehernandez.com.appactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import modelo.Alumno;
import modelo.Maestro;
import modelo.Persona;

public class MainActivity extends AppCompatActivity {

    private Button btnActivity1, btnActivity2, btnActivity3, btnActivity4, btnActivity5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Inicializar();
        Eventos();
    }



    public void Inicializar(){

        btnActivity1  =  findViewById(R.id.btnActivity1);
        btnActivity2  =  findViewById(R.id.btnActivity2);
        btnActivity3  =  findViewById(R.id.btnActivity3);
        btnActivity4  =  findViewById(R.id.btnActivity4);

        btnActivity5  =  findViewById(R.id.btnActivity5);


    }


    public void Eventos(){


        btnActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(getApplicationContext(),
                                             Main2Activity.class);
                startActivity(intent);
            }
        });


        btnActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplicationContext(),
                        Main3Activity.class);

                intent.putExtra("nombre","Felipe");
                intent.putExtra("edad",29);

                startActivity(intent);

            }
        });


        btnActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Persona persona =  new Persona();
                persona.nombre = "felipe";
                persona.edad = 29;
                persona.domicilio = "Betania #519, Col. San felipe de jesus";

                Intent intent =  new Intent(getApplicationContext(), Main4Activity.class);
                intent.putExtra("obj",persona);
                startActivity(intent);

            }
        });


        btnActivity4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Alumno alumno =  new Alumno();
                alumno.nombre = "felipe";
                alumno.edad = 29;
                alumno.domicilio = "Betania #519, Col. San felipe de jesus";

                Intent intent =  new Intent(getApplicationContext(), Main5Activity.class);
                intent.putExtra("obj",alumno);
                startActivity(intent);

            }
        });



        btnActivity5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Maestro maestro =  new Maestro("Felipe",29,"Betania #519, Col. San felipe de jesus");

                Gson gson =  new Gson();


                String obj =  gson.toJson(maestro);

                Intent intent =  new Intent(getApplicationContext(), Main6Activity.class);
                intent.putExtra("obj",obj);
                startActivity(intent);

            }
        });

    }
}
