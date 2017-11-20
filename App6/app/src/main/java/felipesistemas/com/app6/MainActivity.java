package felipesistemas.com.app6;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText txtNombre;
    private Spinner listaEdoCivil;
    private Button btnAceptar;
    private TextView lblInformacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Inicializar();
        Eventos();
    }



    public void Inicializar(){

        txtNombre =  findViewById(R.id.txtNombre);

        listaEdoCivil  =  findViewById(R.id.ListaEdoCivil);

        btnAceptar  =  findViewById(R.id.btnAceptar);

        lblInformacion  =  findViewById(R.id.lblInformacion);


        //Llenar Spinner
        String[] edocivil  =  {"Soltero","Casado","Divorciado"};
        ArrayAdapter<String> adapter  =  new ArrayAdapter<>(this,
                                                                   R.layout.ejemplo_spinner, //android.R.layout.simple_spinner_item
                                                                    edocivil);
        listaEdoCivil.setAdapter(adapter);

    }

    public void Eventos(){

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String informacion ="";

              informacion  =  String.format("Tu nombre es %s y tu estado civil es %s",txtNombre.getText().toString(),listaEdoCivil.getSelectedItem().toString());

              lblInformacion.setText(informacion);

            }
        });


        listaEdoCivil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                        View view,
                                       int position,
                                       long id) {
                String informacion ="";

                informacion  =  String.format("Tu nombre es %s y tu estado civil es %s",txtNombre.getText().toString(),
                                                                                           parent.getItemAtPosition(position));

                lblInformacion.setText(informacion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                lblInformacion.setText("No selecciona nada el usuario");
            }
        });
    }
}
