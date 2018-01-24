package felipesistemas.com.controlesmaterialdesignfloatext;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton boton1;
    private TextInputEditText txtNombre, txtCarrera, txtEdad;
    private String CadenaTexto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Inicializar();

        Eventos();

    }


    public void Inicializar(){

        boton1  =  findViewById(R.id.floactionbutton1);

        txtNombre  =  findViewById(R.id.txtNombre);
        txtCarrera  =  findViewById(R.id.txtCarrera);
        txtEdad  =  findViewById(R.id.txtEdad);

    }

    public boolean validarInformacion(){
        boolean valida  =  true;

        if (TextUtils.isEmpty(txtNombre.getText().toString().trim()) || TextUtils.isEmpty(txtEdad.getText().toString().trim())){
            txtNombre.setError("No debe estar vacio");
            txtEdad.setError("No debe estar vacio");

            valida =  false;
        }
        return valida;
    }

    public void  Eventos(){

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isValidaCampos;
                isValidaCampos = validarInformacion();

                if (isValidaCampos)
                {
                    CadenaTexto  += String.format("Este es tu nombre %s, tu edad es %s y la carrera que estas cursando es: %s",txtNombre.getText().toString(),txtEdad.getText().toString(),txtCarrera.getText().toString());

                    Toast toast  =  Toast.makeText(getApplicationContext(),CadenaTexto,Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });

    }

}
