package felipesistemas.com.app13_butterknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.txtNombre) EditText nombre; // findViewById(R.id.txtNombre)
    @BindView(R.id.txtCorreo) EditText correo;
    @BindView(R.id.switchEstudiante) Switch SwitchEstudiante;
    @BindView(R.id.lblInformacion) TextView lblInformacion;


   // @BindViews({R.id.txtNombre, R.id.txtCorreo}) listaEditext
   // List<EditText> lista;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        //lista.get(0).setText();

    }


    @OnClick(R.id.btnAceptar)
    public void onAceptar(View v){

        String EresEstudiante;

        EresEstudiante  = (SwitchEstudiante.isChecked())?" Si eres Estudiante":" No eres estudiante";

        lblInformacion.setText(String.format("Tu nombre es: %s y tu correo es: %s, %s ",
                nombre.getText().toString(),
                correo.getText().toString(),EresEstudiante));

    }
}
