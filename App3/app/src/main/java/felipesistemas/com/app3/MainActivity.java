package felipesistemas.com.app3;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnAceptar;
    private TextView lblInformacion;
    private TextInputEditText txtCampo1, txtCampo2;

    private RadioButton rd1,rd2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Inicializar();
        Eventos();
    }


    public void Inicializar(){

        btnAceptar =  findViewById(R.id.btnAceptar);

        lblInformacion =  findViewById(R.id.lblInformacion);

        txtCampo1  = findViewById(R.id.txtCampo1);
        txtCampo2  = findViewById(R.id.txtCampo2);

        rd1 = findViewById(R.id.radioSoltero);
        rd2 = findViewById(R.id.radioCasado);

    }

    public void Eventos(){

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String informacion;
                String edocivil ="";

                if (rd1.isChecked()){
                    edocivil = "Soltero";
                }else if (rd2.isChecked()){
                    edocivil = "Casado";
                }

                informacion  =  String.format(" Tu nombre es %s  y tu apellido es %s y tu estado civil es %s",
                                       txtCampo1.getText().toString(),
                                        txtCampo2.getText().toString(),
                                         edocivil);

                lblInformacion.setText(informacion);
            }
        });

    }
}
