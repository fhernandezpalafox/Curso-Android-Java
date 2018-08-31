package felipesistemas.com.app4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre;
    private CheckBox chkPrimaria, chkSecundaria,chkPreparatoria;
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

        txtNombre  =  findViewById(R.id.txtNombre);

        chkPrimaria  =  findViewById(R.id.chkPrimaria);
        chkSecundaria =  findViewById(R.id.chkSecundaria);
        chkPreparatoria  =  findViewById(R.id.chkPreparatoria);

        btnAceptar =  findViewById(R.id.btnAceptar);

        lblInformacion =  findViewById(R.id.lblInformacion);

    }

    public void Eventos(){

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String informacion ="";
                String escolaridades  ="";

                if (chkPrimaria.isChecked()){
                    escolaridades += "Primaria ";
                }
                if(chkSecundaria.isChecked()){
                    escolaridades += "Secundaria ";
                }
                if (chkPreparatoria.isChecked()){
                    escolaridades += "Preparatoria";
                }

                informacion  =  String.format("Tu nombre es %s las escolaridades selecionadas fueron las siguientes %s",
                        txtNombre.getText().toString(),
                        escolaridades);

                lblInformacion.setText(informacion);
            }
        });
    }
}
