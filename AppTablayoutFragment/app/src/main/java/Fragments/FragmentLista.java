package Fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import java.util.List;
import Controladores.ArticulosController;
import Entidades.Articulo;
import Utilidades.SwipeHelper;
import felipehernandez.com.apptablayoutfragment.R;


public class FragmentLista extends Fragment {

    private RecyclerView reyclerViewArticulo;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fbButton;
    private ArticulosController articulosController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);


    }

    public FragmentLista() {
        // Required empty public constructor
    }

    public static FragmentLista newInstance(Bundle arguments) {
        FragmentLista fragment = new FragmentLista();
        if (arguments != null) {
            fragment.setArguments(arguments);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_lista, container, false);

        Inicializar(view);

        ObtenerDatos();

        EventoSwipe();

        SwipeRefresh();

        //ToolbarInicializador();

        EventoBoton();

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Principal");

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        return view;
    }

    public void EventoBoton(){

        fbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentAgregar fragment  =  FragmentAgregar.newInstance(null);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_principal, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public void Inicializar(View view){

        reyclerViewArticulo =  view.findViewById(R.id.listaArticulos);

        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);

        fbButton =  view.findViewById(R.id.fbAgregar);

    }

    public void ObtenerDatos(){

        // usa esta configuración para mejorar el rendimiento si sabes que cambia
        // en el contenido no cambia el tamaño del diseño de RecyclerView
        //reyclerViewArticulo.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        reyclerViewArticulo.setLayoutManager(llm);
        reyclerViewArticulo.setHasFixedSize(true);

        // use a linear layout manager
        // reyclerViewArticulo.setLayoutManager(new LinearLayoutManager(this));

       /* DividerItemDecoration divider = new DividerItemDecoration(reyclerViewArticulo.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        reyclerViewArticulo.addItemDecoration(divider); */

        articulosController =  new ArticulosController();
        articulosController.cargarArticulos(reyclerViewArticulo, getContext(), null);

    }

    public void SwipeRefresh(){

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        ObtenerDatos();
                        swipeRefreshLayout.setRefreshing(false);
                    }

                });
    }

    public void EventoSwipe(){

        SwipeHelper swipeHelper = new SwipeHelper(getContext(), reyclerViewArticulo) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Eliminar",
                        0,
                        Color.parseColor("#FF3C30"),
                        new SwipeHelper.UnderlayButton.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: onDelete

                                Articulo objart =  new Articulo();
                                objart = articulosController.listaArticulos.get(pos);
                                Gson objGson = new Gson();

                                articulosController.eliminarArticulo(objart.getId(),getContext());//
                                articulosController.listaArticulos.remove(pos);
                                articulosController.mAdapter.notifyDataSetChanged();
                            }
                        }
                ));

                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Editar",
                        0,
                        Color.parseColor("#C7C7CB"),
                        new SwipeHelper.UnderlayButton.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: OnUnshare

                                Intent i = new Intent(getContext(), FragmentAgregar.class);
                                Articulo objart =  new Articulo();
                                objart = articulosController.listaArticulos.get(pos);
                                Gson objGson = new Gson();

                                String artString  = objGson.toJson(objart);

                                i.putExtra("clase",artString);
                                i.putExtra("ismodified",true);

                                startActivity(i);
                            }
                        }
                ));
            }
        };
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        getActivity().getMenuInflater().inflate(R.menu.menutoolbar1, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
