package felipehernandex.com.componentes;

import android.animation.Animator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    //declaracion de nuestras variables
    ListView lista;

    TextView textSeleccionado;

    FloatingActionButton fab;


    //declaracion de arreglos para nuestra lista
     private  String[] nombres = {"Jorge", "Pablo","Luis","Monica","Pedro"};
     private String[] conocimientos = {"Java","C#","SQL Server","Oracle","Android"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Inicializar();
        Eventos();
    }


    public  void Inicializar() {

         lista = (ListView) findViewById(R.id.lista);
         textSeleccionado = (TextView) findViewById(R.id.textSeleccionado);


         //adaptador para nuestra ListView
        ArrayAdapter<String> adapter  =  new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
        lista.setAdapter(adapter);


        fab =  (FloatingActionButton) findViewById(R.id.floatingActionButton);


        //animaciones
        fab.setScaleX(0);
        fab.setScaleY(0);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            final Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                    android.R.interpolator.fast_out_slow_in);

            fab.animate()
                    .scaleX(1)
                    .scaleY(1)
                    .setInterpolator(interpolador)
                    .setDuration(600)
                    .setStartDelay(1000)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                           /* fab.animate()
                                    .scaleY(0)
                                    .scaleX(0)
                                    .setInterpolator(interpolador)
                                    .setDuration(600)
                                    .start(); */
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {


                        }
                    });
        }
    }

    public void Eventos(){

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textSeleccionado.setText("El conocimiento que tiene "+lista.getItemAtPosition(position)+" es "+conocimientos[position]);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Se presiono el boton",Snackbar.LENGTH_LONG).setAction("Action",null).show();
            }
        });

    }
}
