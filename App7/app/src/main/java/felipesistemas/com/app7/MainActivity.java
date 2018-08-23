package felipesistemas.com.app7;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn1toast, btn2toast , btn3toast, btnsnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn1toast  =  findViewById(R.id.btnToast1);
        btn2toast  =  findViewById(R.id.btnToast2);

        btn3toast  =  findViewById(R.id.btnToast3);

        btnsnackbar = findViewById(R.id.btnsnackbar);

        btn1toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1  = Toast.makeText(getApplicationContext(),
                        "Este es un toast",Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        btn2toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1  = Toast.makeText(getApplicationContext(),
                        "Este es un toast",Toast.LENGTH_LONG);

                toast1.setGravity(Gravity.CENTER|Gravity.TOP,0,0);

                toast1.show();
            }
        });


        btn3toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast3 =  new Toast(getApplicationContext());

                LayoutInflater inflater = getLayoutInflater();

                View layout =
                        inflater.inflate(R.layout.layout_toast,
                                (ViewGroup) findViewById(R.id.layoutToast));

                TextView txtMensaje =  layout.findViewById(R.id.Mensaje);

                txtMensaje.setText("esta es un mensaje de un toast dinamico");

                toast3.setDuration(Toast.LENGTH_LONG);

                toast3.setView(layout);

                toast3.show();

            }
        });


        btnsnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Snackbar.make(v,"Este es un mensaje de prueba",Snackbar.LENGTH_SHORT).show();

                Snackbar.make(v,"Este es un mensaje de prueba",Snackbar.LENGTH_SHORT)
                        .setActionTextColor(getResources().getColor(R.color.snackbar_action))
                        .setAction("Aceptar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast toast1  = Toast.makeText(getApplicationContext(),
                                        "Este es un toast",Toast.LENGTH_SHORT);
                                toast1.show();
                            }
                        })
                        .show();

            }
        });
    }
}
