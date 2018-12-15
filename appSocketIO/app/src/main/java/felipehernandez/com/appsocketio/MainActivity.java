package felipehernandez.com.appsocketio;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button btnEntrar;
    private TextInputEditText txtUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Inicializar();

        Eventos();
    }


    public void  Inicializar(){

        btnEntrar  =  findViewById(R.id.btnEntrar);
        txtUsuario  =  findViewById(R.id.txtUsuario);
    }


    public void Eventos(){
        btnEntrar.setOnClickListener(v -> {
            Intent i = new Intent(this, ChatActivity.class);
            i.putExtra("Usuario",txtUsuario.getText().toString());
            startActivity(i);
        });


        /*
          btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
         */

    }


}
