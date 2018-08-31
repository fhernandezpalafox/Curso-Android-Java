package felipesistemas.com.app5;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnalerta1, btnalerta2, btnalerta3;
    private TextView lblInformacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Inicializar();

        Eventos();
    }


    public void  Inicializar(){


        btnalerta1 =  findViewById(R.id.btnAlerta1);
        btnalerta2 =  findViewById(R.id.btnAlerta2);
        btnalerta3 =  findViewById(R.id.btnAlerta3);

        lblInformacion  =  findViewById(R.id.lblinformacion);

    }

    public void Eventos(){

        btnalerta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             AlertDialog.Builder builder  =  new AlertDialog.Builder(MainActivity.this);
             builder.setMessage("Mensaje de prueba")
                     .setTitle("Mensaje")
                     .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                            lblInformacion.setText("Respuesta de nuestra alerta");
                         }
                     });
             builder.create().show();
            }
        });


        btnalerta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder  =  new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Mensaje de prueba")
                        .setTitle("Mensaje")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                lblInformacion.setText("Respuesta positiva");
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                lblInformacion.setText("respuesta Negativa");
                                dialog.cancel();
                            }
                        });
                builder.create().show();

            }
        });


        btnalerta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] datos = {"Felipe","Oscar","Juan"};

                AlertDialog.Builder builder =  new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Seleciona un nombre")
                        .setItems(datos, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {
                               lblInformacion.setText(String.format("Tu nombre es %s",datos[item]));
                            }
                        });
                builder.create().show();



                //Alerta #3
                AlertDialog.Builder builder2 =  new AlertDialog.Builder(MainActivity.this);
                builder2.setTitle("Selecciona")
                        .setSingleChoiceItems(datos, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {
                                lblInformacion.setText(String.format("Tu nombre es %s",datos[item]));
                            }
                        });
                builder2.create().show();


                //Alerta #4
                AlertDialog.Builder builder3 =  new AlertDialog.Builder(MainActivity.this);
                builder3.setTitle("Selecciona")
                         .setMultiChoiceItems(datos, null, new DialogInterface.OnMultiChoiceClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int items, boolean isChecked) {

                                 if (isChecked)
                                 {
                                     String informacion  =  lblInformacion.getText().toString();
                                    lblInformacion.setText(informacion + String.format("%s",datos[items])+",");
                                 }

                             }
                         });

                builder3.create().show();
            }
        });


    }
}
