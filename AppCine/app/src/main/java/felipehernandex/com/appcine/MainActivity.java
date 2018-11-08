package felipehernandex.com.appcine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnListView,btnRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Inicializa();
        Eventos();

    }


    public  void  Inicializa(){
        btnListView = (Button) findViewById(R.id.btnListView);
        btnRecyclerView = (Button) findViewById(R.id.btnRecyclerView);
    }


    public void Eventos(){

        btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ListaPeliculas.class);
                startActivity(i);
            }
        });

        btnRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ListaRecyclerView.class);
                startActivity(i);
            }
        });

    }

}
