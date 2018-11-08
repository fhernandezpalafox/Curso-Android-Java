package Fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import felipehernandez.com.apptablayoutfragment.R;


public class Fragmento1 extends Fragment {

    private Button btnAceptar;
    private TextView lblInformacion;
    private TextInputEditText txtCampo1, txtCampo2;

    private RadioButton rd1,rd2;

    public Fragmento1() {
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
        View view = inflater.inflate(R.layout.fragment_fragmento1, container, false);

        Inicializar(view);
        Eventos();

        return view;
    }

    public void Inicializar(View view){

        btnAceptar =  view.findViewById(R.id.btnAceptar);

        lblInformacion =  view.findViewById(R.id.lblInformacion);

        txtCampo1  = view.findViewById(R.id.txtCampo1);
        txtCampo2  = view.findViewById(R.id.txtCampo2);

        rd1 = view.findViewById(R.id.radioSoltero);
        rd2 = view.findViewById(R.id.radioCasado);

    }


    public void Eventos(){

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String informacion;
                String edocivil ="";

                if (rd1.isChecked()){
                    edocivil = "Soltero";
                }else if (rd2.isChecked()){
                    edocivil = "Casado";
                }

                informacion  =
                        String.format(" Tu nombre es %s  y tu apellido es %s y tu estado civil es %s",
                                txtCampo1.getText().toString(),
                                txtCampo2.getText().toString(),
                                edocivil);

                lblInformacion.setText(informacion);
            }
        });

    }


}
