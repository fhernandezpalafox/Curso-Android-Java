package felipehernandex.com.componentes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //declaracion al nivel de clase

    TextView lblResulado;
    EditText txtNum1;
    EditText txtNum2;
    Button btnOperacion;

    RadioButton rdSumar,rdRestar;

    CheckBox checkMultiplicar,checkDividir;

    Spinner spinnerOperaciones;

    Button btnIr;

    Button btnlistaConImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.Inicializar();
        this.Operaciones();
    }

    public void Inicializar()
    {
        lblResulado = (TextView) findViewById(R.id.lblResultado);
        txtNum1 = (EditText) findViewById(R.id.txtNum1);
        txtNum2 = (EditText) findViewById(R.id.txtNum2);
        btnOperacion = (Button)findViewById(R.id.btnOperacion);

        rdSumar = (RadioButton) findViewById(R.id.rdSuma);
        rdRestar = (RadioButton) findViewById(R.id.rdResta);

        checkMultiplicar = (CheckBox) findViewById(R.id.chekMutiplicar);
        checkDividir = (CheckBox) findViewById(R.id.chekDividir);

        spinnerOperaciones = (Spinner) findViewById(R.id.spineerOperaciones);
        String[] opciones = {"Sumar","Restar","Multiplicar","Dividir"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opciones);
        spinnerOperaciones.setAdapter(adapter);


        btnIr = (Button) findViewById(R.id.btnIr);

        btnlistaConImagen = (Button) findViewById(R.id.btnlistaConImagen);

    }

    public void Operaciones()
    {
        btnOperacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  resultadoTexto = "";

                int num1 =  Integer.parseInt(txtNum1.getText().toString());
                int num2 =  Integer.parseInt(txtNum2.getText().toString());
                int resultado=0;

                //RadioButton
                if (rdSumar.isChecked()){
                    resultado = num1 + num2;
                }else if (rdRestar.isChecked()){
                    resultado = num1 - num2;
                }
                //Checkbox
                if (checkMultiplicar.isChecked())
                {
                    resultadoTexto = "\nLa multiplicacion es: "+num1 * num2;
                }
                if(checkDividir.isChecked()){
                    resultadoTexto += "\nLa division es: "+num1 / num2;
                }

                //spinner
                String resul = spinnerOperaciones.getSelectedItem().toString();
                if (resul.equals("Sumar")){
                    resultadoTexto += "\nEsta seleccionado Suma";
                }

                lblResulado.setText("El resultado es:"+resultado+resultadoTexto);

            }
        });



        btnIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(i);
            }
        });

        btnlistaConImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Main3Activity.class);
                startActivity(i);
            }
        });
    }

}
