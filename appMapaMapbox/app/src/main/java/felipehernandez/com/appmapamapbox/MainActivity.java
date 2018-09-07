package felipehernandez.com.appmapamapbox;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    private Button btnMapa1, btnMapa2, btnMapa3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Inicializar();
        Eventos();
    }

    public void Inicializar(){

        btnMapa1  =  findViewById(R.id.btnMapa1);
        btnMapa2  =  findViewById(R.id.btnMapa2);
        btnMapa3  =  findViewById(R.id.btnMapa3);

    }

    public void Eventos(){
        btnMapa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(getApplicationContext(),Mapa1.class);
                startActivity(i);
            }
        });


        btnMapa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(getApplicationContext(),Mapa2.class);
                startActivity(i);
            }
        });

        btnMapa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(getApplicationContext(),Mapa3.class);
                startActivity(i);
            }
        });

    }

}
