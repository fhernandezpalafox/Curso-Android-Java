package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import felipehernandez.com.apptablayoutfragment.R;


public class Fragmento3 extends Fragment {

    Fragment fragment = null;

    public Fragmento3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmento3, container, false);


        fragment  =  FragmentLista.newInstance(null);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_principal, fragment)
                .commit();


        return  view;
    }



}
