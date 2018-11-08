package Fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import felipehernandez.com.apptablayoutfragment.R;


public class Fragmento2 extends Fragment {

    private TextInputEditText txtNombre;
    private Spinner listaEdoCivil;
    private Button btnAceptar;
    private TextView lblInformacion;


    public Fragmento2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragmento2, container, false);

        Inicializar(view);
        Eventos();

        return view;
    }

    public void Inicializar(View view){

        txtNombre =  view.findViewById(R.id.txtNombre);

        listaEdoCivil  =  view.findViewById(R.id.ListaEdoCivil);

        btnAceptar  =  view.findViewById(R.id.btnAceptar);

        lblInformacion  =  view.findViewById(R.id.lblInformacion);


        //Llenar Spinner
        String[] edocivil  =  {"Soltero","Casado","Divorciado"};
        ArrayAdapter<String> adapter  =  new ArrayAdapter<>(getContext(),
                R.layout.item_spinner, //android.R.layout.simple_spinner_item
                edocivil);
        listaEdoCivil.setAdapter(adapter);

    }


    public void Eventos(){

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String informacion ="";

                informacion  =  String.format("Tu nombre es %s y tu estado civil es %s",txtNombre.getText().toString(),listaEdoCivil.getSelectedItem().toString());

                lblInformacion.setText(informacion);

            }
        });


        listaEdoCivil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view,
                                       int position,
                                       long id) {
                String informacion ="";

                informacion  =  String.format("Tu nombre es %s y tu estado civil es %s",txtNombre.getText().toString(),
                        parent.getItemAtPosition(position));

                lblInformacion.setText(informacion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                lblInformacion.setText("No selecciona nada el usuario");
            }
        });
    }

}
