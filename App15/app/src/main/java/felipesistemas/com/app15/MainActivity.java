package felipesistemas.com.app15;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnConsumirJson1;
    private Button btnConsumirJson2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConsumirJson1  =  findViewById(R.id.btnConsumirJSON1);
        btnConsumirJson2  =  findViewById(R.id.btnConsumirJSON2);


        btnConsumirJson1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(intent);

            }
        });


        btnConsumirJson2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  = new Intent(getApplicationContext(),Main3Activity.class);
                startActivity(intent);

            }
        });

    }
}
