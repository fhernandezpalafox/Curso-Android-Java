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

        btnIr = (Button) findViewById(R.id.btnIr);

        btnlistaConImagen = (Button) findViewById(R.id.btnlistaConImagen);

    }

    public void Operaciones()
    {

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
