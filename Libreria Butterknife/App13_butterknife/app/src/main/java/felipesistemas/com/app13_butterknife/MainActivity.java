package felipesistemas.com.app13_butterknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.txtNombre)
    EditText nombre;
    @BindView(R.id.txtCorreo)
    EditText correo;
    @BindView(R.id.switchEstudiante)
    Switch SwitchEstudiante;
    @BindView(R.id.lblInformacion)
    TextView lblInformacion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }


    @OnClick(R.id.btnAceptar)
    public void onAceptar(View v){

        String EresEstudiante;

        EresEstudiante  = (SwitchEstudiante.isChecked())?" Si eres Estudiante":" No eres estudiante";

        lblInformacion.setText(String.format("Tu nombre es: %s y tu correo es: %s, %s ",nombre.getText().toString(),correo.getText().toString(),EresEstudiante));

    }
}
