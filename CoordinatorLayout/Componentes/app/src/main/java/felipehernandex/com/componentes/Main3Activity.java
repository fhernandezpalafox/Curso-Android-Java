package felipehernandex.com.componentes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class Main3Activity extends AppCompatActivity {


    private ListView Lista;
    private ArrayList<Persona> listaPersonas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Inicializar();
        Eventos();

    }


    public void Inicializar(){
        Lista = (ListView) findViewById(R.id.Lista);

        listaPersonas =  new ArrayList<Persona>();
        listaPersonas.add(new Persona("Bill gates","Microsoft", R.drawable.c_bill_gates,R.drawable.g_bill_gates));
        listaPersonas.add(new Persona("Larry page","Google",R.drawable.c_larry_page,R.drawable.g_larry_page));
        listaPersonas.add(new Persona("Sergey brin","Google",R.drawable.c_sergey_brin,R.drawable.g_sergey_brin));

        AdaptadorPersonas adaptadorPersonas =  new AdaptadorPersonas(this);
        Lista.setAdapter(adaptadorPersonas);

    }

    public void Eventos(){

        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(),Main4Activity.class);
                i.putExtra("nombre",listaPersonas.get(position).getNombre());
                i.putExtra("imagen",listaPersonas.get(position).getImagenGrande());
                startActivity(i);
            }
        });

    }


    class AdaptadorPersonas extends ArrayAdapter<Persona> {

        AppCompatActivity appCompatActivity;

        public AdaptadorPersonas(AppCompatActivity context){
            super(context,R.layout.layout_persona,listaPersonas);
            appCompatActivity = context;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.layout_persona,null);

            TextView textNombre = (TextView) item.findViewById(R.id.txtNombrePersona);
            textNombre.setText(listaPersonas.get(position).getNombre());

            CircleImageView imagen2 = (CircleImageView) item.findViewById(R.id.ImgPersona2);
            imagen2.setImageResource(listaPersonas.get(position).getImagen());

           // ImageView imagen = (ImageView) item.findViewById(R.id.ImgPersona);
           // imagen.setImageResource(listaPersonas.get(position).getImagen());

            TextView textEmpresa = (TextView) item.findViewById(R.id.txtEmpresaPersona);
            textEmpresa.setText(listaPersonas.get(position).getEmpresa());


            return item;
        }
    }

}


