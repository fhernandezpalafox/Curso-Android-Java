package felipehernandez.com.practica1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.customtoast.chen.customtoast.CustomToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.dmoral.toasty.Toasty;


public class MainActivity extends AppCompatActivity {

    private Spinner cmbDatos;
    private List<ItemCustom> lista;
    private boolean cargaPrimeraVes = false;

    private TextInputEditText txtTitulo, txtSubTitulo;
    private Button btnAgregar, btnAceptar;

    private CustomArrayAdapterSpinner miAdaptador;

    private int imageAleatorias[] = { R.drawable.ic_imagen1,R.drawable.ic_imagen2, R.drawable.ic_imagen3 };

    private SharedPreferences preferencias;

    private boolean banderaToastCustumizable =  false;

    private CustomToast customToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Inicializar();
        Eventos();

        preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        cargarDatosSharedPreference();


        customToast = new CustomToast(this); //pass context as parameter
        customToast.setTextColor(getApplicationContext().getResources().getColor(R.color.white));
        customToast.setBackground(getApplicationContext().getResources().getColor(R.color.colorPrimary));
    }

    public void cargarDatosSharedPreference(){

        banderaToastCustumizable  = preferencias.getBoolean("habilitado",false);

    }

    public int getImagen(){

        Random rand = new Random();

        int randomNum = rand.nextInt((3 - 1) + 1) + 1;

        return randomNum; //0 , 1 , 2
    }


    public void  Inicializar(){

        cmbDatos = findViewById(R.id.cmbDatos);


        txtTitulo =  findViewById(R.id.txtTitulo);
        txtSubTitulo =  findViewById(R.id.txtSubtitulo);


        btnAceptar =  findViewById(R.id.btnAceptar);
        btnAgregar  =  findViewById(R.id.btnaddLista);

        ItemCustom item  =  new ItemCustom("Titulo 1","Subtitulo 1",getImagen());
        ItemCustom item2  =  new ItemCustom("Titulo 2","Subtitulo 2",getImagen());

        lista = new ArrayList<>();
        lista.add(item);
        lista.add(item2);

        miAdaptador =  new CustomArrayAdapterSpinner(getApplicationContext(),R.layout.layout_item_spinner,lista);
        cmbDatos.setAdapter(miAdaptador);


        cargaPrimeraVes =  true;

    }

    public void Eventos(){

        cmbDatos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (!cargaPrimeraVes){


                    if (banderaToastCustumizable){

                        Toast toast =  new Toast(getApplicationContext());

                        toast.setGravity(Gravity.BOTTOM, 0, 0); //Gravity.BOTTOM|Gravity.FILL_HORIZONTAL

                        LayoutInflater inflater = getLayoutInflater();

                        View layout =  inflater.inflate(R.layout.layout_toast,(ViewGroup) findViewById(R.id.toastLayout));

                        TextView lbltitulo =  layout.findViewById(R.id.lblTitulo);

                        TextView lblsubtitulo =  layout.findViewById(R.id.lblSubTitulo);

                        ImageView image  =  layout.findViewById(R.id.imgLogo);

                        lbltitulo.setText(lista.get(position).getTitulo());

                        lblsubtitulo.setText(lista.get(position).getSubTitulo());

                        image.setImageResource(imageAleatorias[lista.get(position).getImagen() -1]);

                        toast.setDuration(Toast.LENGTH_LONG);

                        toast.setView(layout);

                        toast.show();
                    }else {

                        Toast.makeText(getApplicationContext(),lista.get(position).getTitulo(), Toast.LENGTH_LONG).show();

                        Toasty.success(getApplicationContext(), "Success!", Toast.LENGTH_SHORT, true).show();

                        customToast.showSuccessToast("I have successfully registered my interest");
                    }

                }

                cargaPrimeraVes = false;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = txtTitulo.getText().toString();
                String subtitulo = txtSubTitulo.getText().toString();
                int img  = getImagen();

                ItemCustom itemCustom =  new ItemCustom(titulo,subtitulo,img);
                lista.add(itemCustom);
                miAdaptador.notifyDataSetChanged();
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder  =  new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("El toast personalizado")
                        .setTitle("Toast")
                        .setPositiveButton("Habilitar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences.Editor editor =preferencias.edit();
                                editor.putBoolean("habilitado", true);
                                banderaToastCustumizable  =  true;
                                editor.commit();
                            }
                        })
                        .setNegativeButton("Deshabilitar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences.Editor editor =preferencias.edit();
                                editor.putBoolean("habilitado", false);
                                banderaToastCustumizable  =  false;
                                editor.commit();
                                dialog.cancel();
                            }
                        });
                builder.create().show();


            }
        });

    }

}
