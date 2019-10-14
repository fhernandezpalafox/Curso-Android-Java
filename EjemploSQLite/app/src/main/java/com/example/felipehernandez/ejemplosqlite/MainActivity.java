package com.example.felipehernandez.ejemplosqlite;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import java.util.List;

import Entidades.EntidadEvento;
import modelo.EventosDatasource;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.LlenarInformacion();
    }

    public void LlenarInformacion()
    {

        EventosDatasource datasource  =  new EventosDatasource(this);


        //INSERTAR DATOS
        //datasource.guardarEvento("Clausura del festival","Martes 17 Nov.");

        final ListView listaeventos  = findViewById(R.id.listaeventos);
        List<EntidadEvento> registros = new ArrayList<>();

        // SE ESTA LLAMANDO AL METODO PARA TRAERNOS  TODA LA INFORMACION DE LA BD
        Cursor cursor  =  datasource.consultarEventos();

        while (cursor.moveToNext())
        {
            EntidadEvento  columnas  =  new EntidadEvento(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );

            registros.add(columnas);
        }

        //ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,registros);
        AdpatadorEventos adaptador  =  new AdpatadorEventos(this,registros);
        listaeventos.setAdapter(adaptador);

        listaeventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                EntidadEvento item = (EntidadEvento) parent.getItemAtPosition(position);

                Intent intent  = new Intent(MainActivity.this,DetalleEventos.class);
                intent.putExtra("id",item.getID_EVENTO());
                intent.putExtra("dia",item.getDIA_EVENTO());
                intent.putExtra("descripcion",item.getDESCRIPCION_EVENTO());
                startActivity(intent);

            }



        });
    }

    public void AgregarEventos(View view)
    {
        Intent intent  = new Intent(MainActivity.this,DetalleEventos.class);
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();

        this.LlenarInformacion();
    }


    @Override
    public void onResume() {
        super.onResume();

        this.LlenarInformacion();
    }



    class AdpatadorEventos  extends ArrayAdapter<EntidadEvento>
    {
        List<EntidadEvento> _datos;

        public AdpatadorEventos(Context context, List<EntidadEvento> datos) {
            super(context,R.layout.lista_eventos,datos);
            _datos  =  datos;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater =  LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.lista_eventos, null);

            TextView lbltitulo  = item.findViewById(R.id.LblTitulo);
            lbltitulo.setText(_datos.get(position).getDESCRIPCION_EVENTO());

            return(item);
        }
    }
}
