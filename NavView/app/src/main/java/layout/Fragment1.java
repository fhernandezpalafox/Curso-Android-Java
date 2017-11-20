package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.felipehernandez.navview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {


    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_fragment1, container, false);


        Button btnprecionar  =  (Button) view.findViewById(R.id.btnprecionar);
        final TextView lblinformacion  = (TextView) view.findViewById(R.id.lblInformacion);

        btnprecionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 lblinformacion.setText("Hola como estas?");
            }
        });

        return  view;
    }

}
