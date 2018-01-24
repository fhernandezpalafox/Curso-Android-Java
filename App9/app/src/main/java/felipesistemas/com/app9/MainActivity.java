package felipesistemas.com.app9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnMapa1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Liga
        btnMapa1  = findViewById(R.id.btnMapa1);

        //Eventos
        btnMapa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =  new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);

            }
        });


    }
}
