package felipesistemas.com.app2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtCampo1,txtCampo2, txtCampo3;
    private Button btnAceptar;
    private TextView lblInformacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Inicializar();
        Eventos();
    }


    public void  Inicializar(){

        txtCampo1 = findViewById(R.id.txtCampo1);
        txtCampo2 = findViewById(R.id.txtCampo2);
        txtCampo3 = findViewById(R.id.txtCampo3);

        btnAceptar = findViewById(R.id.btnAceptar);

        lblInformacion = findViewById(R.id.lblInformacion);
    }

    public void Eventos(){

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String informacion;

                informacion  =  String.format("El campo 1 fue capturado %s  y despues el campo de password fue capturado %s  y el tercer campo fue capturado %s",
                        txtCampo1.getText().toString(),
                        txtCampo2.getText().toString(),
                        txtCampo3.getText().toString());

                lblInformacion.setText(informacion);
            }
        });
    }
}
