package com.example.felipehernandez.ejemplosqlite;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import modelo.EventosDatasource;

public class DetalleEventos extends AppCompatActivity {

    private TextView _txtdia;
    private TextView _txtdescripcion;
    private EventosDatasource datasource;

    private int id  = 0;
    private String dia  = "";
    private String descripcion  = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_eventos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


         datasource  =  new EventosDatasource(this);

        _txtdia  = (TextView) findViewById(R.id.txtdia);
        _txtdescripcion  =  (TextView) findViewById(R.id.txtDescripcion);


        //PARAMETROS DE OTRA ACTIVIDAD
        Bundle b  =  this.getIntent().getExtras();


        if (b != null)
        {
            id  =  b.getInt("id");
            dia  =  b.getString("dia");
            descripcion  =  b.getString("descripcion");

            _txtdia.setText(dia.toString());
            _txtdescripcion.setText(descripcion.toString());

        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void eliminar(View view)
    {
        if(datasource.eliminarEvento(id))
        {
            Toast toast  =  Toast.makeText(getApplicationContext(),"Se realizo correctamente",Toast.LENGTH_SHORT);
            toast.show();

            _txtdia.setText("");
            _txtdescripcion.setText("");
        }

    }

    public void GuardarEvento(View view)
    {
        if (id!=0)
        {
            //editar
            datasource.modificarEvento(_txtdescripcion.getText().toString(),_txtdia.getText().toString(),id);
            Toast toast  =  Toast.makeText(getApplicationContext(),"Se editó correctamente",Toast.LENGTH_SHORT);
            toast.show();

        }else {
            datasource.guardarEvento(_txtdescripcion.getText().toString(), _txtdia.getText().toString());

            Toast toast  =  Toast.makeText(getApplicationContext(),"Se guardo correctamente",Toast.LENGTH_SHORT);
            toast.show();
        }
    }


}
